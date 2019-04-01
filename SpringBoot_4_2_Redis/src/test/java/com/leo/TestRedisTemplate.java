package com.leo;

import com.leo.model.User;
import com.leo.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("leo", "yellomountain");
        Assert.assertEquals("yellomountain", redisTemplate.opsForValue().get("leo"));
    }

    @Test
    public void testObj() {
        User user = new User("ityouknow@126.com", "smile", "youknow", "know", "2020");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.leo", user);
        User u = operations.get("com.leo");
        System.out.println("user: " + u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException {
        User user = new User("ityouknow@126.com", "expire", "youknow", "expire", "2020");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("expire", user, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("expire");
        if(exists) {
            System.out.println("exists is true");
        }else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete() {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("deleteKey", "ityouknow");
        redisTemplate.delete("deleteKey");
        boolean exists = redisTemplate.hasKey("deleteKey");
        if(exists) {
            System.out.println("exists is true");
        }else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash", "you", "you");
        hash.put("hash","i", "i");
        String valueY = (String) hash.get("hash", "you");
        String valueI = (String) hash.get("hash", "i");
        System.out.println("hash valueY : " + valueY + ", hash valueI : " + valueI);
    }

    @Test
    public void testList() {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush("list", "it");
        list.leftPush("list", "you");
        list.leftPush("list", "know");
        String value = (String)list.leftPop("list");
        System.out.println("list value :" + value.toString());

        List<String> values = list.range("list", 0, 2);
        //超出范围会重新读取
        for (String v : values) {
            System.out.println("list range :" + v);
        }
    }

    @Test
    public void testSet() {
        String key = "set";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key, "it");
        set.add(key, "you");
        set.add(key, "you");
        set.add(key, "know");
        Set<String> values = set.members(key);
        for (String v : values) {
            //输出结果是无序的
            System.out.println("set value :" + v);
        }
    }

    /**
     * 求差集
     */
    @Test
    public void testDifference() {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key1 = "setMore1";
        String key2 = "setMore2";
        set.add(key1, "it");
        set.add(key1, "you");
        set.add(key1, "you");
        set.add(key1, "know");
        set.add(key2, "xx");
        set.add(key2, "know");
        Set<String> diffs = set.difference(key1,key2);
        for (String v : diffs) {
            System.out.println("diffs set value :" + v);
        }
    }

    /**
     * 求并集
     */
    @Test
    public void testUnions() {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key3 = "setMore3";
        String key4 = "setMore4";
        set.add(key3, "it");
        set.add(key3, "you");
        set.add(key3, "xx");
        set.add(key4, "aa");
        set.add(key4, "bb");
        set.add(key4, "know");
        Set<String> unions = set.union(key3, key4);
        for (String v : unions) {
            System.out.println("unions value :" + v);
        }
    }

    @Test
    public void testZset() {
        String key = "zset";
        redisTemplate.delete(key);
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key, "it", 1);
        zset.add(key, "you", 6);
        zset.add(key, "know", 4);
        zset.add(key, "leo", 3);

        Set<String> zsets = zset.range(key, 0, 3);
        for (String v : zsets) {
            System.out.println("zset value :" + v);
        }

        Set<String> zsetB = zset.rangeByScore(key, 0, 3);
        for (String v : zsetB) {
            System.out.println("zsetB value :" + v);
        }
    }

    @Test
    public void testStringService() {
        redisService.set("leo", "ityouknow");
        Assert.assertEquals("ityouknow", redisService.get("leo"));
    }
}