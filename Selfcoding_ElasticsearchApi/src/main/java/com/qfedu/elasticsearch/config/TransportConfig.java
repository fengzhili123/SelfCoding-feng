package com.qfedu.elasticsearch.config;

import com.qfedu.elasticsearch.transport.TransportBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 冯志立
 * @Date: 2019/4/1 17:14
 */
@Configuration
public class TransportConfig {
    @Bean
    public TransportBean creatTB() {
        return new TransportBean("qfjava","39.105.189.141",9300);
    }
}
