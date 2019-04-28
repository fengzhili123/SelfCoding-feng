package com.qfedu.cloudapi.service;

import com.qfedu.common.vo.PageVo;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.course.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fengcourseprovider") //提供者名称
public interface CourseService {
    @PostMapping("course/couseadd.do")
    public ResultVo save(@RequestBody Course course);

    @GetMapping("course/cousedetail.do")
    public ResultVo getDetail(@RequestParam("id") int id);

    @GetMapping("course/coursepage.do")
    public PageVo<Course> getPage(@RequestParam("page") int page,@RequestParam("limit") int limit);
}
