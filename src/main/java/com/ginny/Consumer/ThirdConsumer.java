package com.ginny.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "THIRD_QUEUE", containerFactory="rabbitListenerContainerFactory")
public class ThirdConsumer {

    @RabbitHandler
    public void process(@Payload String msg){
        System.out.println("Third Queue received msg : " + msg);
    }

}
