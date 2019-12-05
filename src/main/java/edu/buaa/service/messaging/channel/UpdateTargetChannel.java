package edu.buaa.service.messaging.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UpdateTargetChannel {

    String CHANNEL = "UpdateTargetChannel";

    @Output(CHANNEL)
    MessageChannel messageChannel();
}
