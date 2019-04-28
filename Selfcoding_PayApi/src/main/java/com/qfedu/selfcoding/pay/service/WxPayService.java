package com.qfedu.selfcoding.pay.service;

import com.qfedu.common.vo.ResultVo;

import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2019/3/28 10:31
 */
public interface WxPayService {
    //生成预支付信息
    ResultVo createPre(Map<String, String> map);
    //查询订单
    ResultVo searchOrder(String orderid);
    //查询对账单
    ResultVo secrhBill(String billdate);
}
