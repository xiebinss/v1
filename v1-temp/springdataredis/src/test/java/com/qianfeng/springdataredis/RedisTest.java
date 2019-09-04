package com.qianfeng.springdataredis;


import com.qianfeng.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest {
    @Autowired
    //所有操作都基于模板对象
    private RedisTemplate redisTemplate;
    @Test
    public void sttingTest(){
      /*  redisTemplate.opsForValue().set("smallTrage","100");
        Object trage=redisTemplate.opsForValue().get("smallTrage");
        System.out.println(trage);*/

        Student student=new Student(1L,"xiaoming");
        redisTemplate.opsForValue().set("student",student);
        Object student1= redisTemplate.opsForValue().get("student");
        System.out.println(student1);
    }


}
