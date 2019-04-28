package com.qfedu.cloudapi.controller;

import com.qfedu.cloudapi.service.CourseService;
import com.qfedu.common.vo.PageVo;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.course.Course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "课程相关操作")
@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("course/coursesave.do")
    public ResultVo save(Course course){
        return courseService.save(course);
    }

    @ApiOperation(value = "课程详情")
    @GetMapping("course/coursedetail.do")
    public ResultVo getDetail(int id){
        return courseService.getDetail(id);
    }

    @ApiOperation(value = "课程分页查询")
    @GetMapping("course/coursepage.do")
    public PageVo<Course> page(int page,int limit){
        return courseService.getPage(page, limit);
    }
}
