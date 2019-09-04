package com.qianfeng.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void StrigTest(){
        Jedis jedis =new Jedis("120.27.240.138",6379);
        jedis.auth("123456");
        jedis.set("k","jedis");


      /*  Student student=new Student();*/
    }
}
