package edu.buaa.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.buaa.domain.messaging.TargetNotification;
import edu.buaa.service.messaging.UpdateTargetNotificationProducer;
import edu.buaa.service.messaging.channel.UpdateTargetChannel;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class ApiController {


    private UpdateTargetNotificationProducer updateTargetNotificationProducer;

    public ApiController(UpdateTargetNotificationProducer updateTargetNotificationProducer) {
        this.updateTargetNotificationProducer = updateTargetNotificationProducer;
    }
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public
    @ResponseBody
    String map(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        JSONObject res = new JSONObject();
       res.put("x","116.41995287496285");
       res.put("y","39.917274760176798");
        return res.toJSONString();

    }

    @GetMapping("/greetings/{count}")
    public void produce(@PathVariable int count) {
        if(count > 0) {
            TargetNotification targetNotification=new TargetNotification();
            targetNotification.setCategory("cat");
            targetNotification.setLongitude(13.1123+count);
            updateTargetNotificationProducer.sendMsgToGateway(targetNotification);
        }
    }
}


