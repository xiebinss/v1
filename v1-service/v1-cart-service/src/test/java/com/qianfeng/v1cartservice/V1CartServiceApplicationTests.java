package com.qianfeng.v1cartservice;

import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.api.ICarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V1CartServiceApplicationTests {
    @Autowired
    private ICarService carService;
    @Test
    public void contextLoads() {
        ResultBean add = carService.add("bfb7ea15-cb06-4d71-b2a0-e58a38d1bf31", 1L, 1000);
        System.out.println(add.getDate());
    }
    @Test
    public void delTest(){
        ResultBean del = carService.del("123456", 1L);
        System.out.println(del.getDate());
    }
}
