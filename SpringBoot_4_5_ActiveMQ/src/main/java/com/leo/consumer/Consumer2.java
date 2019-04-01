package com.leo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {

    private Logger logger = LoggerFactory.getLogger(Consumer2.class);


    @JmsListener(destination = "leo.queue", containerFactory = "queueListenerFactory")
    public void receiveQueue(String text) {
        System.out.println("Consumer2 queue msg : " + text);
    }

    @JmsListener(destination = "leo.topic", containerFactory = "topicListenerFactory")
    public void receiveTopic(String text) {
        System.out.println("Consumer2 topic msg : " + text);
    }
}