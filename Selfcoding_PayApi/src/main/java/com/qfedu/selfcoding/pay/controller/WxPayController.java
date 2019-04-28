package com.qfedu.selfcoding.pay.controller;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.pay.Payinfo;
import com.qfedu.selfcoding.pay.service.PayService;
import com.qfedu.selfcoding.pay.service.WxPayService;

import com.qfedu.selfcoding.pay.wechatpay.WXPayConstants;
import com.qfedu.selfcoding.pay.wechatpay.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2019/3/28 11:00
 */
@Api(value = "微信支付")
@RestController
public class WxPayController {
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private PayService payService;
    //生成预支付信息
    @ApiOperation(value = "创建预支付信息，生成二维码")
    @PostMapping("pay/wxpayorder.do")
    public ResultVo createOrder(String orderid, double money, String body, HttpServletRequest request) {
        Map<String,String> map=new HashMap<>();
        map.put("body",body);
        map.put("out_trade_no",orderid);
        map.put("total_fee",money+"");
        map.put("spbill_create_ip",request.getRemoteAddr());
        map.put("notify_url",WXPayConstants.EWMURL+"/pay/wxpaynotify.do");
        map.put("trade_type","NATIVE");

        ResultVo resultVo= wxPayService.createPre(map);
        Payinfo payinfo=new Payinfo();
        payinfo.setErcodeurl(resultVo.getData().toString());
        payinfo.setFlag(1);
        payinfo.setIp(request.getRemoteAddr());
        payinfo.setMoney((int)(money*100));
        payinfo.setType(1);
        payinfo.setUvid(0);
        payService.savePrePay(payinfo);
        return resultVo;
    }
    //回调通知 只有支付宝才可以调用
    @GetMapping("pay/wxpaynotify.do")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String xml=IOUtils.toString(request.getInputStream(),"UTF-8");
        System.out.println("支付回调："+xml);
        Map<String,String> map=WXPayUtil.xmlToMap(xml);
        if(map.containsKey("result_code")){
            int f=3;//支付失败
            if(map.get("result_code").equals("SUCCESS")){
                f=2;//支付成功
            }
            payService.updateFlag(map.get("out_trade_no"),f);
        }
        response.getWriter().print("<xml> <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>");

    }
    //查询订单
    @GetMapping("pay/wxpayquery.do")
    public ResultVo searchOrder(String orderid){
        return wxPayService.searchOrder(orderid);
    }
    //下载对账单
    @GetMapping("pay/wxpaybill.do")
    public ResultVo searchBill(String billDate){
        return wxPayService.secrhBill(billDate);
    }

}
