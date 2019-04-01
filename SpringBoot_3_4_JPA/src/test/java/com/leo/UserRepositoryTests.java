package com.leo;

import com.leo.model.User;
import com.leo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa", "aa@126.com", "aa", "aa123456", formattedDate));
        userRepository.save(new User("bb", "bb@126.com", "bb", "bb123456", formattedDate));
        userRepository.save(new User("cc", "cc@126.com", "cc", "cc123456", formattedDate));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb123456", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa"));
    }
}
