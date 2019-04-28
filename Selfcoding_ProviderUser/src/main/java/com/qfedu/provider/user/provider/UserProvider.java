package com.qfedu.provider.user.provider;

import com.netflix.discovery.converters.Auto;
import com.qfedu.common.sysconst.SystemCon;
import com.qfedu.common.util.ResultUtil;
import com.qfedu.common.util.TokenUtil;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.dao.user.*;
import com.qfedu.entity.user.*;
import com.qfedu.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Author: 冯志立
 * @Date: 2019/4/2 21:10
 */
@Service("userprovider")
public class UserProvider implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserlogMapper userlogMapper;
    @Autowired
    private UsersignMapper usersignMapper;
    @Autowired
    private UserwalletMapper userwalletMapper;
    @Autowired
    private UserstreamMapper userstreamMapper;
    @Autowired
    private UserplayMapper userplayMapper;
    @Autowired
    private UsercollectMapper usercollectMapper;
    @Autowired
    private AwardlogMapper awardlogMapper;

    @Override
    public ResultVo save(User user, String ip) {
        userMapper.insert(user);
        Userlog userlog = new Userlog();
        userlog.setUid(user.getId());
        userlog.setCreatetime(new Date());
        userlog.setIp(ip);
        userlog.setContent("注册用户");
        userlogMapper.insert(userlog);
        return ResultUtil.exec(true,"OK", null);
    }

    @Override
    public ResultVo saveSign(String token) {
        //1、解析token
        int uid = TokenUtil.parseToken(token);
        //2、查询最后一次的签到时间
        Usersign usersign = usersignMapper.queryLastSign(uid);
        int days = 1;
        Random random = new Random();
        //随机生成一个1-5的数字
        int cions = random.nextInt(5) + 1;
        if (usersign != null){
            //3、获取日期差
            int d = getDays(System.currentTimeMillis()) - getDays(usersign.getCreatetime().getTime());
            if (d == 0){
                //已经签到过
                cions = -1;
            } else if (d == 1){
                //昨天签过到 连续签到
                days = usersign.getDays() + 1;
                if (days % 365 == 0){
                    //10倍
                    cions = cions * 10;
                } else if (days % 30 == 0){
                    //3-5倍
                    cions = cions * (random.nextInt(3) + 3);
                } else if (days %  5 == 0){
                    //1-3倍
                    cions = cions * (random.nextInt(3) + 1);
                } else {
                    //断签
                    cions = 1;
                }
            }
        }
        if (cions > 0){
            //生成签到数据
            Usersign us = new Usersign();
            us.setCoins(cions);
            us.setCreatetime(Calendar.getInstance().getTime());
            us.setDays(days);
            us.setUid(uid);
            usersignMapper.insert(us);
            //更新钱包数据
            userwalletMapper.updateByCions(cions, uid);
            //生成奖励金流水数据
            Awardlog awardlog = new Awardlog();
            awardlog.setCoins(cions);
            awardlog.setType(SystemCon.JLJQD);
            awardlog.setUwid(uid);
            awardlog.setValidity(-1);
            awardlog.setCreatetime(new Date());
            awardlog.setEnddate(new Date());
            awardlog.setStartdate(new Date());
            awardlogMapper.insert(awardlog);
            //记录操作日志
            Userstream userstream = new Userstream();
            userstream.setContent(uid + ":用户连续签到：" + days + ":本次获取奖励金:" + cions);
            userstream.setUid(uid);
            userstream.setType(SystemCon.STREAMLOGSIGN);
            userstream.setCreatetime(new Date());
            userstreamMapper.insert(userstream);
            return ResultUtil.exec(true, "签到成功，连续签到"+ days +"天，本次获得奖励金"+ cions +"文",null );
        }
        return null;
    }

    @Override
    public ResultVo checkPhone(String phone) {
        return null;
    }

    @Override
    public ResultVo querySign(String token, int type) {
        return null;
    }

    @Override
    public ResultVo querySign(String token, String month) {
        return null;
    }

    @Override
    public ResultVo queryMoney(String token) {
        return null;
    }

    @Override
    public ResultVo queryPlayLog(String token) {
        return null;
    }

    @Override
    public ResultVo queryCollect(String token) {
        return null;
    }

    @Override
    public ResultVo saveCollect(String token, int type, int id) {
        return null;
    }

    @Override
    public ResultVo saveBuy(String token, int cid, int cions) {
        return null;
    }

    private int getDays(long times){return (int)(times/1000/60/60/24);}
}
