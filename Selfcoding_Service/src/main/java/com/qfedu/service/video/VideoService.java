package com.qfedu.service.video;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.entity.course.Course;
import com.qfedu.entity.video.Video;

public interface VideoService {
    ResultVo save(Video video);
    ResultVo queryById(int id);
    ResultVo queryList(int cid);
}
