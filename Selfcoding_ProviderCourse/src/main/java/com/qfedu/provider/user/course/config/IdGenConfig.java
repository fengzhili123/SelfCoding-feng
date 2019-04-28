package com.qfedu.provider.user.course.config;

import com.qfedu.common.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 冯志立
 * @Date: 2019/4/2 20:27
 */
@Configuration
public class IdGenConfig {
    @Bean
    public IdGenerator createId(){
        return new IdGenerator();
    }
}
