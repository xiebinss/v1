package com.qianfeng.v1webbackground.config;

import com.qianfeng.common.constant.RabbitMQCofig;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConfig {

    @Bean
    public TopicExchange initExchange(){
        return new TopicExchange(RabbitMQCofig.BACKGROUND_PRODUCT_EXCHANGE,true,false);
    }

}
