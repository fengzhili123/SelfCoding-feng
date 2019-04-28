package com.qfedu.provider.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: 冯志立
 * @Date: 2019/4/6 14:59
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qfedu.dao.video")
public class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}
