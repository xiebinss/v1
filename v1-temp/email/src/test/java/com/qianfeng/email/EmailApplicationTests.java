package com.qianfeng.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplicationTests {
    @Autowired
    private JavaMailSender javaMailSender;
    @Test
    public void contextLoads() {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("17820195321@163.com");
        message.setTo("823895470@qq.com");
        message.setSubject("与网易邮件建立联系");
        message.setText("第一次联系");
        javaMailSender.send(message);
    }

}
