package com.leo;

import com.leo.producer.Producer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleActiveMqTests {
    @Autowired
    private Producer producer;
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void sendSimpleQueueMessage() throws InterruptedException {
        producer.sendQueue("Test queue message");
        Thread.sleep(1000L);
        Assert.assertThat(outputCapture.toString(),containsString("Test queue"));
    }

    @Test
    public void send100QueueMessage() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            producer.sendQueue("Test queue message" + i);
        }
        Thread.sleep(1000L);
    }

    @Test
    public void sendSimpleTopicMessage() throws InterruptedException {
        producer.sendTopic("Test topic message");
        Thread.sleep(1000L);
    }
}