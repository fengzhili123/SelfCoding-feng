package com.qfedu.selfcoding.pay.provider;

import com.qfedu.common.util.ResultUtil;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.dao.pay.PayinfoMapper;
import com.qfedu.dao.pay.PayorderMapper;
import com.qfedu.entity.pay.Payinfo;
import com.qfedu.entity.pay.Payorder;
import com.qfedu.selfcoding.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *@Author feri
 *@Date Created in 2019/4/2 14:38
 */
@Service
public class PayProvider implements PayService {
    @Autowired
    private PayinfoMapper payinfoMapper;
    @Autowired
    private PayorderMapper payorderMapper;

    @Override
    public ResultVo saveOrder(Payorder payorder) {
        payorder.setCreatetime(new Date());
        payorderMapper.insert(payorder);
        return ResultUtil.exec(true,"OK",null);
    }

    @Override
    public ResultVo savePrePay(Payinfo payinfo) {
        payinfo.setCreatetime(new Date());
        payinfoMapper.insert(payinfo);
        return null;
    }

    @Override
    public void updateFlag(String id, int flag) {
        payinfoMapper.updateFlag(Integer.parseInt(id),flag);
        payorderMapper.updateFlag(Integer.parseInt(id),flag);
    }
}
