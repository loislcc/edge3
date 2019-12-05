package edu.buaa.service.messaging;

import edu.buaa.domain.messaging.TargetNotification;
import edu.buaa.service.messaging.channel.UpdateTargetChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(UpdateTargetChannel.class)
public class UpdateTargetNotificationProducer {

    private final Logger log = LoggerFactory.getLogger(UpdateTargetNotificationProducer.class);

    @Autowired
    private UpdateTargetChannel updateTargetChannel;

    public void sendMsgToGateway(TargetNotification targetNotification){
        log.debug("send msg to gateway ");
        try {
            log.debug("send result: {}",
                updateTargetChannel.messageChannel().send(MessageBuilder.withPayload(targetNotification).build()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
