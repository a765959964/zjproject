package com.example.demo;

import com.example.demo.core.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 接受消息
 * @author: 张帆
 * @create: 2018-11-28 14:46
 * @Description:
 **/
public class Recver {

    private static String queue = "zf_queue";

    public static void main(String[] args) throws Exception {
        //创建物理连接
        Connection connection = RabbitUtils.getConnection();
        //创建虚拟链接，信道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(queue,false,false,false,null);
        //创建消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recver message :" + new String(body));
                super.handleDelivery(consumerTag, envelope, properties, body);
            }
        };
        //接受消息
        channel.basicConsume(queue,true,consumer);
        //消费者不能关闭
        /*
        channel.close();
        connection.close();*/
    }

}
