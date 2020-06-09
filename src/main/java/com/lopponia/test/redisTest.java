//package com.lopponia.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.concurrent.TimeUnit;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
////@WebAppConfiguration
//public class redisTest {
//    @Autowired
//    RedisTemplate<String, String> redisTemplate;
//
//    @Test
//    public void testExpire() {
//        redisTemplate.expire("name", 30, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void setString() {
//        // 从源码发现boundValueOps最终也调用了opsForValue(),所有类型均能以此种方式进行操作，不再赘述.
//        // redisTemplate.boundValueOps("name").set("pass");
//        redisTemplate.opsForValue().set("name", "pass");
//    }
//
//    @Test
//    public void getValue() {
//        System.out.println(redisTemplate.opsForValue().get("name"));
//    }
//
//    @Test
//    public void deleteKey() {
//        if (redisTemplate.delete("name")) {
//            System.out.println("删除成功");
//        }
//    }
//}
