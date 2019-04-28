package com.qfedu.mq.config;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author: 冯志立
 * @Date: 2019/4/1 16:45
 */
@Service
@RabbitListener(queues = "orders")
public class MgConsumer {
    @RabbitHandler
    public void recive(String msg){
        System.out.println("消费者：" + msg);
    }
}
