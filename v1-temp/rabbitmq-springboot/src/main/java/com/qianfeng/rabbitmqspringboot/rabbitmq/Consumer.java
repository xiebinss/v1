package com.qianfeng.rabbitmqspringboot.rabbitmq;

import com.qianfeng.rabbitmqspringboot.common.CommonConstant;
import com.qianfeng.rabbitmqspringboot.entity.ProductDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = CommonConstant.QUEUE_NAME)
public class Consumer {
    @RabbitHandler
    public void process(String message){
        System.out.println("消息为"+message);
    }

    @RabbitHandler
    public void process(ProductDTO productDTO){System.out.println(productDTO.getId()+","+productDTO.getName());}
}
