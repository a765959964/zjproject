package com.example.demo.core.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: 张帆
 * @create: 2018-11-28 14:41
 * @Description: Rabbits 消息队列
 **/
public class RabbitUtils {
    public static Connection getConnection(){
        Connection connection = null;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.217.128");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin123");
        factory.setVirtualHost("/zf");
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
