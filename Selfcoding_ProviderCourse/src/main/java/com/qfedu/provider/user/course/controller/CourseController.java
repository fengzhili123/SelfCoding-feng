package com.qfedu.provider.user.course.controller;

import com.qfedu.common.vo.PageVo;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.course.Course;
import com.qfedu.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    //新增课程
    @PostMapping("course/couseadd.do")
    public ResultVo save(Course course){
        return courseService.save(course);
    }
    //查询详情
    @GetMapping("course/cousedetail.do")
    public ResultVo detail(int id){
        return courseService.queryById(id);
    }
    //分页查询
    @GetMapping("course/coursepage.do")
    public PageVo<Course> page(int page, int limit){
        return courseService.queryList(page, limit);
    }
    //我的课程
    @GetMapping("course/courseuid.do")
    public ResultVo queryUid(String token){
        return courseService.queryByUid(token);
    }
    //查询所有作者
    @GetMapping("author/authorall.do")
    public ResultVo queryAuthor(){
        return courseService.queryAuthor();
    }
    //课程类型
    @GetMapping("course/coursetype.do")
    public ResultVo queryType(){
        return courseService.queryCType();
    }
    //课程知识点
    @GetMapping("course/coursepoint.do")
    public ResultVo queryPoint(int id){
        return courseService.queryById(id);
    }
}
