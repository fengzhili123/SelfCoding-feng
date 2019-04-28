package com.qfedu.cloudapi.controller;

import com.qfedu.cloudapi.service.VideoService;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    public VideoService videoService;

    @PostMapping("videosave.do")
    public ResultVo save(Video video){
        return videoService.save(video);
    }

    @PostMapping("videodetail.do")
    public ResultVo detail(int id){
        return videoService.queryById(id);
    }

    @PostMapping("videocid.do")
    public ResultVo videoCid(int cid){
        return videoService.queryByCid(cid);
    }

}
