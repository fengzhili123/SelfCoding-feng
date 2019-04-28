package com.qfedu.selfcoding.pay.controller;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.pay.Payinfo;
import com.qfedu.selfcoding.pay.entity.AlipayOrder;
import com.qfedu.selfcoding.pay.service.AlipayService;
import com.qfedu.selfcoding.pay.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@Author feri
 *@Date Created in 2019/3/26 14:50
 * 实现支付宝支付
 */
@Api(value = "支付宝支付")
@RestController
public class AlipayController {

    @Autowired
    private PayService payService;
    @Autowired
    private AlipayService alipayService;

    //下单 生成预支付信息
    @ApiOperation(value = "创建支付二维码")
    @PostMapping("pay/alipayorder.do")
    public ResultVo save(AlipayOrder order, HttpServletRequest request){
        ResultVo resultVo= alipayService.savePay(order);
        Payinfo payinfo=new Payinfo();
        payinfo.setErcodeurl(resultVo.getData().toString());
        payinfo.setFlag(1);
        payinfo.setIp(request.getRemoteAddr());
        payinfo.setMoney((int)(order.getTotal_amount()*100));
        payinfo.setType(1);
        payinfo.setUvid(0);
        payService.savePrePay(payinfo);
        return resultVo;
    }
    //回调通知 只有支付宝才可以调用
    @GetMapping("pay/alipaynotify.do")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("out_trade_no");
        String status=request.getParameter("trade_status");
        if(id!=null && status!=null){
            int f=3;
            if(status.equals("TRADE_SUCCESS")){
                f=2;
            }
            payService.updateFlag(id,f);
        }
        response.getWriter().print("success");
    }
    //查询订单交易状态
    @ApiOperation(value = "查询订单状态")
    @GetMapping("pay/alipayquery.do")
    public ResultVo query(String tradeno){
        return alipayService.queryPay(tradeno);
    }
    //下载对账单  按照天
    @ApiOperation(value = "查询对账单，按天查询")
    @GetMapping("pay/alipaybillday")
    public ResultVo down(String date){
        return alipayService.queryBill(date);
    }
    //下载对账单 按照月份
    @ApiOperation(value = "查询对账单，按月查询")
    @GetMapping("pay/alipaybillmonth")
    public ResultVo downmonth(String month){
        return alipayService.queryBill(month);
    }
}