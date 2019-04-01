package com.leo.mapper;

import com.leo.enums.UserSexEnum;
import com.leo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testUser() {
        //增加
        userMapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
        //删除
        int count = userMapper.delete(2L);
        User user = userMapper.getOne(3L);
        user.setNickName("smile");
        //修改
        userMapper.update(user);
        //查询
        List<User> users = userMapper.getAll();
    }
}
