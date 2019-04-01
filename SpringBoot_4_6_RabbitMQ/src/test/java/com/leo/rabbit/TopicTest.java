package com.leo.rabbit;

import com.leo.rabbit.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicSender sender;

    @Test
    public void topic1() throws InterruptedException {
        sender.send1();
        Thread.sleep(1000);
    }

    @Test
    public void topic2() throws InterruptedException {
        sender.send2();
        Thread.sleep(1000);
    }
}
