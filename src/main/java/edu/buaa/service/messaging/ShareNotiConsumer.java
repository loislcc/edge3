package edu.buaa.service.messaging;

import com.alibaba.fastjson.JSONObject;
import edu.buaa.domain.Device;
import edu.buaa.domain.Notification;
import edu.buaa.service.Constant;
import edu.buaa.service.SendTask;
import edu.buaa.service.messaging.channel.ShareChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class ShareNotiConsumer {
    private final Logger log = LoggerFactory.getLogger(ShareNotiConsumer.class);
    private Constant constant;

    private SendTask sendTask;
    public ShareNotiConsumer(Constant constant, SendTask sendTask) {
        this.constant = constant;
        this.sendTask = sendTask;
    }

    @StreamListener(ShareChannel.CHANNELIN)
    public void listen(Notification msg) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if(!msg.getOwner().equals("edge3")){
            log.debug("listen Notification from edge device*: {}", msg.toString());
            if (msg.getType().equals("heart")) {  // 心跳信息，用于记录邻居
                boolean flag = false;
                if(constant.neigbour == null || constant.neigbour.size() == 0) {
                    Device device = new Device();
                    device.setId(msg.getOwnerId());
                    device.setName(msg.getOwner());
                    device.setLastime(df.format(new Date()));
                    constant.neigbour.add(device);
                } else {
                    for (Device device : constant.neigbour) {
                        if (device.getId() == msg.getOwnerId()) {
                            device.setLastime(df.format(new Date()));
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        Device device = new Device();
                        device.setId(msg.getOwnerId());
                        device.setName(msg.getOwner());
                        device.setLastime(df.format(new Date()));
                        constant.neigbour.add(device);
                    }
                }

            }

            if(msg.getType().equals("request")){      // 接收到请求投票的消息
                constant.lock = true;
                if(!constant.leader.equals("")){  // 目前已经有leader
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("vote","inTerm");
                    jsonObject.put("term", constant.term);
                    jsonObject.put("leader", constant.leader);
                    sendTask.sendRaft(msg.getOwnerId(),jsonObject);
                } else {
                    JSONObject jsonObject = new JSONObject();
                    if(constant.ticket!=0){
                        jsonObject.put("vote","Right");
                        jsonObject.put("ticket", 1);
                        constant.ticket--;
                        System.err.println("投票给: " + msg.getOwner());

                    } else {
                        jsonObject.put("vote","noRight");
                        jsonObject.put("ticket", 0);
                    }
                    sendTask.sendRaft(msg.getOwnerId(),jsonObject);
                }
            }

            if(msg.getTargetId() == 3 && msg.getType().equals("back")) {
                System.err.println("%%%%%%%%%: " );
                JSONObject JO = (JSONObject) JSONObject.parse(msg.getBody());
                if(JO.getString("vote").equals("inTerm")){
                    if(constant.leader.equals("")){
                        constant.term = JO.getInteger("term");
                        constant.leader = JO.getString("leader");
                    }
                } else if(JO.getString("vote").equals("Right")){
                    constant.term ++;
                    constant.leader = constant.Edgename;
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("vote","inTerm");
                    jsonObject.put("term", constant.term);
                    jsonObject.put("leader", constant.leader);
                    System.err.println("%%%%%%%%%:term "+constant.term );
                    System.err.println("%%%%%%%%%:leader "+constant.leader );

                    sendTask.sendRaft(msg.getOwnerId(),jsonObject);
                } else {
                    sendTask.sendRequest();
                }
            }
        }
    }
}
