package com.qianfeng.rabbitmq.simple;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static String QUEUE_NAME="simple-queue";
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
        //3. 声明一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //4.发送消息
        String message = "人如果没有梦想，跟咸鱼有什么区别";
        channel.basicPublish("","simple-queue",null,message.getBytes());
        System.out.println("发送消息成功！");
    }
}
