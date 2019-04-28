package com.qfedu.cloudapi.service;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.video.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fengvideoprovider")
public interface VideoService {
    @PostMapping("video/videosave.do")
    public ResultVo save(@RequestBody Video video);

    @PostMapping(value = "video/videodetail.do")
    public ResultVo queryById(@RequestParam("id") int id);

    @PostMapping(value = "video/videocid.do")
    public ResultVo queryByCid(@RequestParam("cid") int cid);
}
