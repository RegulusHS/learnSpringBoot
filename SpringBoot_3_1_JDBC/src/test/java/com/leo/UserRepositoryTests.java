package com.leo;

import com.leo.model.User;
import com.leo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User("leo", "123456", 30);
        userRepository.save(user);
    }

    @Test
    public void testUpdate() {
        User user = new User("leo", "123456", 18);
        user.setId(1L);
        userRepository.update(user);
    }

    @Test
    public void testDetele() {
        userRepository.delete(1L);
    }

    @Test
    public void testQueryOne()  {
        User user=userRepository.findById(1L);
        System.out.println("user == "+user.toString());
    }
}
