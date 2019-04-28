package com.qfedu.provider.video.controller;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.video.Video;
import com.qfedu.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 冯志立
 * @Date: 2019/4/6 15:06
 */
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    //新增
    @PostMapping("videosave.do")
    public ResultVo list(@RequestBody Video video){
        return videoService.save(video);
    }
    //详情
    @PostMapping(value = "videodetail.do",params = {"id"})
    public ResultVo detail(int id ){
        return videoService.queryById(id);
    }
    //课程对应的视频
    @PostMapping(value = "videocid.do", params = {"cid"})
    public ResultVo list(int cid){
        return videoService.queryList(cid);
    }
}
