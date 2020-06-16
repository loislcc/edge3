package edu.buaa.service;

import edu.buaa.service.messaging.ShareNotiProducer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(ShareNotiProducer.class)
public class Constant {
    public String Edgename = "edge3";
    public int term = 0;
    public String leader = this.Edgename;
}
