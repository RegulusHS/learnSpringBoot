package com.leo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.leo.mapper")
public class MXApplication {

    public static void main(String[] args) {
        SpringApplication.run(MXApplication.class, args);
    }

}
