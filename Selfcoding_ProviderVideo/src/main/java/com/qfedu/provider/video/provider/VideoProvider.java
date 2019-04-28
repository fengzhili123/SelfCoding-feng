package com.qfedu.provider.video.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfedu.common.util.ResultUtil;
import com.qfedu.common.vo.ResultVo;
import com.qfedu.dao.video.VideoMapper;
import com.qfedu.entity.video.Video;
import com.qfedu.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 冯志立
 * @Date: 2019/4/6 15:01
 */
@Service
public class VideoProvider implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public ResultVo save(Video video) {
        video.setCreatetime(new Date());
        video.setFlag(1);
        return ResultUtil.exec(true, "OK", videoMapper.insert(video));
    }

    @Override
    public ResultVo queryById(int id) {
        return ResultUtil.exec(true, "OK", videoMapper.selectById(id));
    }

    @Override
    public ResultVo queryList(int cid) {
        return ResultUtil.exec(true, "OK",
                videoMapper.selectList(new QueryWrapper<Video>().eq("cid", cid))
        );
    }
}
