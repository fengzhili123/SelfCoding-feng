package com.qfedu.mq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 冯志立
 * @Date: 2019/4/1 16:52
 */
@RestController
public class MqController {
    @Autowired
    private AmqpTemplate amqpTemplate;
    //发送消息
    @PostMapping("mq/sendmsg.do")
    public String sendMsg(String msg){
        amqpTemplate.convertAndSend("orders",msg);
        return "消息发送成功";
    }
}
