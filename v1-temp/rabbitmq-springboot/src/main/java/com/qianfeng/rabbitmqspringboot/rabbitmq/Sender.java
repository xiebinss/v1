package com.qianfeng.rabbitmqspringboot.rabbitmq;

import com.qianfeng.rabbitmqspringboot.common.CommonConstant;
import com.qianfeng.rabbitmqspringboot.entity.ProductDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(String message){
        rabbitTemplate.convertAndSend(CommonConstant.QUEUE_NAME,message);
    }
    public void send(ProductDTO productDTO){
        rabbitTemplate.convertAndSend(CommonConstant.QUEUE_NAME,productDTO);
    }
}
