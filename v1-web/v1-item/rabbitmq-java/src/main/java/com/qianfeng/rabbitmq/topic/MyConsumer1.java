package com.qianfeng.rabbitmq.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer1 {
    private static String EXCHANGE_NAME="topic-exchange1";
    private static String QUEUE_NAME="topic-exchange-queue1";
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
        //申明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //将队列绑定到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"product.#");

       //消费者处理消息
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               String message=new String(body,"utf-8");
                System.out.println("消费者2接收的消息为"+message);

            }
        };
//消费者监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
