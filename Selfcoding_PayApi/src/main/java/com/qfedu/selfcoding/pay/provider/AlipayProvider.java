package com.qfedu.selfcoding.pay.provider;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.qfedu.common.util.Base64Util;
import com.qfedu.common.util.ResultUtil;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.pay.Payorder;
import com.qfedu.selfcoding.pay.entity.AlipayOrder;
import com.qfedu.selfcoding.pay.service.AlipayService;
import com.qfedu.selfcoding.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@Author feri
 *@Date Created in 2019/3/26 15:08
 */
@Service
public class AlipayProvider implements AlipayService {
    @Autowired
    private AlipayClient client;
    @Autowired
    private PayService payService;
    @Override
    public ResultVo savePay(AlipayOrder order) {
        AlipayTradePrecreateRequest request=new AlipayTradePrecreateRequest();
        request.setBizContent(JSON.toJSONString(order));
        request.setNotifyUrl("http://localhost:16888/pay/alipaynotify.do");
        try {
            AlipayTradePrecreateResponse response=client.execute(request);
            if(response.isSuccess()){
                String msg=response.getQrCode();
                Payorder payorder=new Payorder();
                payorder.setFlag(1);
                payorder.setInfo(order.getBody());
                payorder.setMoney((int)(order.getTotal_amount()*100));
                payorder.setPreurl(msg);
                payorder.setType(1);
                payorder.setUid(0);
                payorder.setUvid(0);
                payService.saveOrder(payorder);
                String passmsg=Base64Util.base64Enc(msg.getBytes());
                return ResultUtil.exec(true,"OK","http://localhost:16888/qrcodepay.do/"+passmsg);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultVo queryPay(String tradeno) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":\""+tradeno+"\"");
        AlipayTradeQueryResponse response = null;
        try {
            response = client.execute(request);
            if(response.isSuccess()){
                return ResultUtil.exec(true,response.getTradeStatus(),response);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return ResultUtil.exec(false,"暂无该订单",null);
    }

    @Override
    public ResultVo queryBill(String date) {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent("{\"bill_type\":\"trade\",\"bill_date\":\""+date+"\"}");
        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
        try {
            response = client.execute(request);
            if(response.isSuccess()){
               return ResultUtil.exec(true,"对账单下载地址",response.getBillDownloadUrl());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return ResultUtil.exec(false,"暂无对账单",null);
    }
}
