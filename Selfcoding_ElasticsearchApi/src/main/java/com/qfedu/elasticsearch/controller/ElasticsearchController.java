package com.qfedu.elasticsearch.controller;

import com.qfedu.common.vo.ResultVo;
import com.qfedu.elasticsearch.service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 冯志立
 * @Date: 2019/4/1 20:08
 */
@RestController
@Api(value = "实现Elasticksearch的相关操作")
public class ElasticsearchController {
    @Autowired
    private EsService esService;

    @ApiOperation("新增数据")
    @PostMapping("essave.do")
    public ResultVo save(String indexName, String type, String id, String data){
        return esService.save(indexName, type, id, data);
    }

    @ApiOperation("查询单个数据")
    @GetMapping("essingle.do")
    public ResultVo single(String indexName, String type, String id){
        return esService.querySingle(indexName, type, id);
    }

    @ApiOperation("模糊查询")
    @PostMapping("eslike.do")
    public ResultVo like(String indexName, String type, String field, String value, int page , int limit){
        return esService.queryLike(indexName, type, field, value, page, limit);
    }

}
