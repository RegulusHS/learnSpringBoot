package com.leo.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "leo")
public class LeoReceiver1 {

    @RabbitHandler
    public void process(String leo) {
        System.out.println("Receiver 1: " + leo);
    }
}
