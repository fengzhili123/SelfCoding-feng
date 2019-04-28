package com.qfedu.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 冯志立
 * @Date: 2019/4/1 16:51
 */
@SpringBootApplication
@MapperScan("com.qfedu.dao")
public class MqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class,args);
    }
}
