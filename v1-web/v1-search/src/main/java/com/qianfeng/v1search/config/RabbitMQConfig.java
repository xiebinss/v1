package com.qianfeng.v1search.config;

import com.qianfeng.common.constant.RabbitMQCofig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue initQueue(){
        return new Queue(RabbitMQCofig.ADDORUOPATE_SEARCH_QUEUE,false,false,false);
    }
    @Bean
    public TopicExchange initExchange(){
        return new TopicExchange(RabbitMQCofig.BACKGROUND_PRODUCT_EXCHANGE,true,false);
    }

    @Bean
    public Binding bindingExchange(Queue initQueue, TopicExchange initExchange){
        return  BindingBuilder.bind(initQueue).to(initExchange).with("product.add");

    }
}
