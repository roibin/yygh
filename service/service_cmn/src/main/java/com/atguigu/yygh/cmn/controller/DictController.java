package com.atguigu.yygh.cmn.controller;

import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author
 * @date 2022/5/1 11:40
 */
@RestController
@RequestMapping("/admin/cmn/dict")
@Api(value = "数据字典接口")
//@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    //导入数据字典接口
    @PostMapping("importData")
    public Result importDict(MultipartFile file){
        dictService.importDictData(file);
        return Result.ok();
    }

    //导出数据字典接口
    @GetMapping("exportData")
    public void exportDict(HttpServletResponse response) throws IOException {
        dictService.exportDictData(response);
    }

    @ApiOperation("根据数据id查询子节点数据")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    //根据dictcode和value查询
    @GetMapping("getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,
                          @PathVariable String value) {
        String dictName = dictService.getDictName(dictCode,value);
        return dictName;

    }

    //根据value查询
    @GetMapping("getName/{value}")
    public String getName(@PathVariable String value) {
        String dictName = dictService.getDictName(null,value);
        return dictName;
    }

    //根据dictCode获取下级节点
    @ApiOperation("根据dictCode获取下级节点")
    @GetMapping("findByDictCode/{dictCode}")
    public Result findByDictCode(@PathVariable String dictCode){
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }

}
