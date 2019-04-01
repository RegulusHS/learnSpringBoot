package com.leo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;

@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("set error: key {}, value {}", key, value, e);
        }
        return result;
    }

    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if(keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    public Object get(String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
}
