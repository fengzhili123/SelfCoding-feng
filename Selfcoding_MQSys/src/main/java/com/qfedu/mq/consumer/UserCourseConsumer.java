package com.qfedu.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.qfedu.common.vo.MqMessageVo;
import com.qfedu.dao.pay.TradelogMapper;
import com.qfedu.dao.user.UserlogMapper;
import com.qfedu.entity.pay.Tradelog;
import com.qfedu.entity.user.Usercourse;
import com.qfedu.entity.user.Userlog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: 冯志立
 * @Date: 2019/4/2 20:03
 */
@Service
@RabbitListener(queues = "sc_buycourse")
public class UserCourseConsumer {
    @Autowired
    private UserlogMapper userlogMapper;
    @Autowired
    private TradelogMapper tradelogMapper;
    @RabbitHandler
    public void process(String json){
        MqMessageVo mqMessageVo = JSON.parseObject(json, MqMessageVo.class);
        Usercourse usercourse = (Usercourse) mqMessageVo.getData();
        Userlog userlog = new Userlog();
        userlog.setIp("");
        userlog.setUid(usercourse.getUid());
        userlog.setCreatetime(new Date());
        userlog.setContent(usercourse.getUid() + "购买了：" + usercourse.getCid() + "花了：" + usercourse.getCoins());
        userlogMapper.insert(userlog);  //添加用户日志
        Tradelog tradelog = new Tradelog();
        tradelog.setCoins(usercourse.getCoins());
        tradelog.setCreatetime(new Date());
        tradelog.setUcid(usercourse.getId());
        tradelog.setUid(usercourse.getUid());
        tradelogMapper.insert(tradelog);  //添加交易日志
    }
}
