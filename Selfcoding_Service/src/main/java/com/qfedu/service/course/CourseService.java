package com.qfedu.service.course;

import com.qfedu.common.vo.PageVo;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.course.Course;

public interface CourseService {
    ResultVo save(Course course);
    ResultVo queryById(int id);
    PageVo<Course> queryList(int page, int limit);
    //我的课程
    ResultVo queryByUid(String token);
    //查询所有作者
    ResultVo queryAuthor();
    //查询课程类型
    ResultVo queryCType();
    //查询课程对应的知识点
    ResultVo queryPoint(int id);
    //购买课程
    ResultVo saveBuy(String token, int cid, int cions);
}
