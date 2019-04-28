package com.qfedu.elasticsearch.service;

import com.qfedu.common.vo.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: 冯志立
 * @Date: 2019/4/1 19:39
 */
public interface EsService {
    //新增
    ResultVo save(String index, String type, String id, String data);
    //批量新增
    ResultVo saveBatch(String index, String type, Map<String, String> map);
    //修改
    ResultVo update(String index, String type, String id, String data);
    //删除
    ResultVo delete(String index, String type, String id);
    //批量删除
    ResultVo deleteBatch(String index, String type, List<String> ids);
    //查询单个
    ResultVo querySingle(String index, String type, String id);
    //模糊查询  分页
    ResultVo queryLike(String index, String type, String field, String value, int page, int limit);
}
