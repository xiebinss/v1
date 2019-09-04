package com.qianfeng.v1search.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.api.ISearchService;
import com.qianfeng.common.constant.RabbitMQCofig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    @Reference
    private ISearchService searchService;
    @RabbitListener(queues = RabbitMQCofig.ADDORUOPATE_SEARCH_QUEUE)
    @RabbitHandler
    public void processAdd(Long id){
            searchService.updateById(id);
    }
}
