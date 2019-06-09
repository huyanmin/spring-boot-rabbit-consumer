package com.ginny.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "FOURTH_QUEUE", containerFactory="rabbitListenerContainerFactory")
public class FourthConsumer {

    @RabbitHandler
    public void process(@Payload String msg){
        System.out.println("Fourth Queue received msg : " + msg);
    }

}
