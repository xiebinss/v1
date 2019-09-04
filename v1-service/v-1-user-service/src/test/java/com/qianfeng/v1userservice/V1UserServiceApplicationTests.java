package com.qianfeng.v1userservice;

import com.qianfeng.api.IUserService;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.entity.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V1UserServiceApplicationTests {
    @Autowired
    private IUserService userService;
    @Test
    public void contextLoads() {
        TUser user=new TUser();
        user.setUsername("java1904");
        user.setPassword("123456");
        ResultBean resultBean=userService.checkLogin(user);
        System.out.println(resultBean.getDate());
    }
    @Test
    public void isLogin(){
        ResultBean resultBean=userService.checkIsLogin("b41a94b8-a9f6-4179-81eb-6a29be8db55d");
        System.out.println(resultBean.getStatusCode());
        System.out.println(resultBean.getDate() );
    }

}
