package com.leo.rabbit;

import com.leo.rabbit.many.LeoSender;
import com.leo.rabbit.many.LeoSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
    @Autowired
    private LeoSender leoSender;

    @Autowired
    private LeoSender2 leoSender2;

    @Test
    public void oneToMany() throws InterruptedException {
        for (int i=0; i<100; i++) {
            leoSender.send(i);
        }
        Thread.sleep(10000L);
    }

    @Test
    public void manyToMany() throws InterruptedException {
        for (int i=0; i<100; i++) {
            leoSender.send(i);
            leoSender2.send(i);
        }
        Thread.sleep(5000);
    }

}
