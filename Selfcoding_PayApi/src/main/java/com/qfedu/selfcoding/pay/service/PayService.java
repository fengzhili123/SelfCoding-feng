package com.qfedu.selfcoding.pay.service;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.pay.Payinfo;
import com.qfedu.entity.pay.Payorder;

/**
 * @Author feri
 * @Date Created in 2019/4/2 14:36
 */
public interface PayService {
    ResultVo saveOrder(Payorder payorder);

    ResultVo savePrePay(Payinfo payinfo);

    void updateFlag(String id, int flag);
}
