package com.example.demo;

import com.example.demo.core.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 发送消息
 * @author: 张帆
 * @create: 2018-11-28 14:46
 * @Description:
 **/
public class Sender {

    private static String queue = "zf_queue";

    public static void main(String[] args) throws Exception {
        //创建物理连接
        Connection connection = RabbitUtils.getConnection();
        //创建虚拟链接，信道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(queue,false,false,false,null);
       //发送消息
        String msg = "Hello Word ! RabbitMQ";
        channel.basicPublish("",queue,null,msg.getBytes());
        channel.close();
        connection.close();
    }

}
