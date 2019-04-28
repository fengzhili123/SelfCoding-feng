package com.qfedu.service.user;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.user.User;

/**
 * @Author: 冯志立
 * @Date: 2019/4/2 21:10
 */
public interface UserService {
    //新增用户
    ResultVo save(User user, String ip);
    //每日签到
    ResultVo saveSign(String token);
    //检查手机号是否重复
    ResultVo checkPhone(String phone);
    //查询用户的签到数据  当月签到数据 历史签到数据 type=1 本月数据 type=2 本年度数据 type=3 全部数据
    ResultVo querySign(String token, int type);
    //查询用户的签到数据 指定月份的签到数据
    ResultVo querySign(String token, String month);
    //查询账户
    ResultVo queryMoney(String token);
    //我的播放记录
    ResultVo queryPlayLog(String token);
    //我的收藏
    ResultVo queryCollect(String token);
    //收藏  type类型 1收藏课程 2收藏视频 3收藏教程
    ResultVo saveCollect(String token, int type, int id);
    //购买课程
    ResultVo saveBuy(String token, int cid, int cions);
}
