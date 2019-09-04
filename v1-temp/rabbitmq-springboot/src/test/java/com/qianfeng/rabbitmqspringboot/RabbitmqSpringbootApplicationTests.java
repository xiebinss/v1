package com.qianfeng.rabbitmqspringboot;

import com.qianfeng.rabbitmqspringboot.entity.ProductDTO;
import com.qianfeng.rabbitmqspringboot.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqSpringbootApplicationTests {
    @Autowired
    private Sender sender;
    @Test
    public void contextLoads() {
        sender.send("shishi");
    }

    @Test
    public void sendProduct(){
        ProductDTO productDTO=new ProductDTO(1,"xiaming");
        sender.send(productDTO);
    }

}
