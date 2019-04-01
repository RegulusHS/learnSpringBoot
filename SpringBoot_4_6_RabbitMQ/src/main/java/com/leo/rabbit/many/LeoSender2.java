package com.leo.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeoSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "Spring boot leo queue" + " ****** " + i;
        System.out.println("Sender2 : " + context);
        rabbitTemplate.convertAndSend("leo", context);
    }
}
