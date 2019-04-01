package com.leo.rabbit;

import com.leo.model.User;
import com.leo.rabbit.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTest {

    @Autowired
    private ObjectSender sender;

    @Test
    public void sendObject() throws InterruptedException {
        User user = new User();
        user.setName("leo");
        user.setPass("123456");
        sender.send(user);
        Thread.sleep(1000);
    }
}
