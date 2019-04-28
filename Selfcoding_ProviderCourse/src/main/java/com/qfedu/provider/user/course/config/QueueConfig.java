package com.qfedu.provider.user.course.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @Author: 冯志立
 * @Date: 2019/4/2 20:28
 */
@Configuration
public class QueueConfig {
    @Bean
    public Queue createQueue(){
        return new Queue("sc_buycourse");
    }
}
