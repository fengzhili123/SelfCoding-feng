package com.qfedu.provider.user.course.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfedu.common.util.IdGenerator;
import com.qfedu.common.util.ResultUtil;
import com.qfedu.common.util.TokenUtil;
import com.qfedu.common.vo.MqMessageVo;
import com.qfedu.common.vo.PageVo;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.dao.course.AuthorMapper;
import com.qfedu.dao.course.CourseMapper;
import com.qfedu.dao.course.CoursepointMapper;
import com.qfedu.dao.course.CoursetypeMapper;
import com.qfedu.dao.user.UsercourseMapper;
import com.qfedu.dao.user.UserwalletMapper;
import com.qfedu.entity.course.Author;
import com.qfedu.entity.course.Course;
import com.qfedu.entity.course.Coursepoint;
import com.qfedu.entity.course.Coursetype;
import com.qfedu.entity.user.Usercourse;
import com.qfedu.entity.user.Userwallet;
import com.qfedu.service.course.CourseService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CourseProvider implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UsercourseMapper usercourseMapper;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private CoursetypeMapper coursetypeMapper;
    @Autowired
    private CoursepointMapper coursepointMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private UserwalletMapper userwalletMapper;

    @Override
    public ResultVo save(Course course) {
        course.setCreatetime(new Date());
        course.setFlag(1);
        courseMapper.insert(course);

        return ResultUtil.exec(true, "OK", "新增课程成功");
    }

    @Override
    public ResultVo queryById(int id) {
        return ResultUtil.exec(true, "OK", courseMapper.selectById(id));
    }

    @Override
    public PageVo<Course> queryList(int page, int limit) {
        Page<Course> page1 = new Page<>(page, limit);
        return ResultUtil.exec(
                page, limit,
                page1.getTotal(),
                courseMapper.selectPage(page1, null).getRecords());
    }

    @Override
    public ResultVo queryByUid(String token) {
        QueryWrapper<Usercourse> usercourseQueryWrapper = new QueryWrapper<>();
        usercourseQueryWrapper.eq("uid", TokenUtil.parseToken(token));
        usercourseQueryWrapper.orderByDesc("createtime");
        return ResultUtil.exec(true, "OK", usercourseMapper.selectList(usercourseQueryWrapper));
    }

    @Override
    public ResultVo queryAuthor() {
        return ResultUtil.exec(true, "ok", authorMapper.selectList(new QueryWrapper<Author>().orderByAsc("id")));
    }

    @Override
    public ResultVo queryCType() {
        return ResultUtil.exec(true, "OK", coursetypeMapper.selectList(new QueryWrapper<Coursetype>().orderByAsc("id")));
    }

    @Override
    public ResultVo queryPoint(int id) {
        return ResultUtil.exec(true, "OK", coursepointMapper.selectList(new QueryWrapper<Coursepoint>().eq("tid", id)));
    }

    @Override
    @Transactional  //设置事务
    public ResultVo saveBuy(String token, int cid, int cions) {
        //1、校验钱包
        int uid = TokenUtil.parseToken(token);
        Userwallet userwallet = userwalletMapper.selectById(uid);
        if (userwallet.getCoins() >= cions) {
            //2、钱够  减少金额
            userwalletMapper.updateByCions(uid, userwallet.getCoins() - cions);
            //3、增加课程到用户
            Usercourse usercourse = new Usercourse();
            usercourse.setCid(cid);
            usercourse.setCoins(cions);
            usercourse.setCreatetime(new Date());
            usercourse.setUid(uid);
            usercourseMapper.insert(usercourse);
            //4、发送消息
            MqMessageVo mqMessageVo = new MqMessageVo();
            mqMessageVo.setId(idGenerator.nextIdLock());
            mqMessageVo.setType(1);
            mqMessageVo.setData(usercourse);
            amqpTemplate.convertAndSend("sc_buycourse", mqMessageVo);
            return ResultUtil.exec(true, "下单成功", null);
        }
        return ResultUtil.exec(false, "余额不足", null);
    }
}
