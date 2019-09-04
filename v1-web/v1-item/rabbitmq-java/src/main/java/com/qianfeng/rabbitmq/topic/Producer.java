package com.qianfeng.rabbitmq.topic;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static String EXCHANGE_NAME="topic-exchange1";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.36.8.8");
        connectionFactory.setVirtualHost("/xb");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("xb");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();
        //2. 基于连接对象创建channel通道
        Channel channel = connection.createChannel();
        //3. 声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //4.发送消息到队列

            String message1= "新增商品";
            String message2="修改商品";

            //key为标识
            channel.basicPublish(EXCHANGE_NAME, "product.add", null, message1.getBytes());
            channel.basicPublish(EXCHANGE_NAME, "product.a", null, message2.getBytes());

        System.out.println("发送消息成功！");
    }
}
