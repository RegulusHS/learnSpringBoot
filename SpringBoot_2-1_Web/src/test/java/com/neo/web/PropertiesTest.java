package com.neo.web;

import com.neo.web.model.NeoProperties;
import com.neo.web.model.OtherProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Resource
    private NeoProperties properties;
    @Resource
    private OtherProperties otherProperties;

    @Value("${neo.title}")
    private String title;

    @Test
    public void testSingle() {
        Assert.assertEquals(title, "纯洁的微笑");
    }

    @Test
    public void testMore() {
        System.out.println("title:" + properties.getTitle());
        System.out.println("description:" + properties.getDescription());
    }

    @Test
    public void testOther() {
        System.out.println("title:" + otherProperties.getTitle());
        System.out.println("blog:" + otherProperties.getBlog());
    }
}
