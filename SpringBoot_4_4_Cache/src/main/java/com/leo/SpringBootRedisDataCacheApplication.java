package com.leo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootRedisDataCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisDataCacheApplication.class, args);
    }

}
