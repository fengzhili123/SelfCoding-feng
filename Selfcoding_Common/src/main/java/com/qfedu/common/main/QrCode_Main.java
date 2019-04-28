package com.qfedu.common.main;

import com.qfedu.common.util.ZxingUtil;

/**
 *@Author feri
 *@Date Created in 2019/3/26 09:51
 */
public class QrCode_Main {
    public static void main(String[] args) {
        ZxingUtil.encode("千锋郑州Java1807","JPEG",500,500,"qfzz1807.jpeg");

    }
}
