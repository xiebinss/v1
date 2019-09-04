package com.qianfeng.springbootredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {
    @Resource(name = "stringRedisTemplate1")
    private RedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
            redisTemplate.opsForValue().set("trage1","trage1");
        Object trage1 = redisTemplate.opsForValue().get("trage1");
        System.out.println(trage1);
    }

}
