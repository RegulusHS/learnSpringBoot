package com.leo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    @JmsListener(destination = "leo.queue", containerFactory = "queueListenerFactory")
    public void receiveQueue(String text) {
        System.out.println("Consumer queue msg : " + text);
    }

    @JmsListener(destination = "leo.topic", containerFactory = "topicListenerFactory")
    public void receiveTopic(String text) {
        logger.info("Consumer topic msg : " + text);
        System.out.println("Consumer topic msg : " + text);
    }
}