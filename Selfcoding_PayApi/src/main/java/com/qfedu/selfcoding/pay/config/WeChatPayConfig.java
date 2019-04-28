package com.qfedu.selfcoding.pay.config;

import com.qfedu.selfcoding.pay.util.WXPayConstants;
import com.qfedu.selfcoding.pay.util.WxChatPayUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 冯志立
 * @Date: 2019/4/6 16:09
 */
public class WeChatPayConfig {
    @Configuration
    public class WxPayConfig {
        //    @Bean
//    public WXPay wxPay() throws Exception {
//        return new WXPay(new WXConfig());
//    }
        @Bean
        public WxChatPayUtil createPayUtil(){
            return new WxChatPayUtil(WXPayConstants.APP_ID,WXPayConstants.MCH_ID,WXPayConstants.API_KEY,WXPayConstants.HMACSHA256);
        }
    }

}
