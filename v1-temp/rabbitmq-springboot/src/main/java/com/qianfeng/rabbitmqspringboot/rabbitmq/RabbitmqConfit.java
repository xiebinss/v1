package com.qianfeng.rabbitmqspringboot.rabbitmq;


import com.qianfeng.rabbitmqspringboot.common.CommonConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfit {
    @Bean
    public Queue initQueue(){
        return new Queue(CommonConstant.QUEUE_NAME,false,false,false);
    }
}
