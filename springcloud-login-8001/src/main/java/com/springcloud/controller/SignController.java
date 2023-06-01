package com.springcloud.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.entity.Mapping;
import com.springcloud.common.Result;
import com.springcloud.entity.Sign;
import com.springcloud.mapper.SignMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
@Api(tags = "已完成订单管理")
@RestController

@RequestMapping("/sign")
public class SignController {
    @Resource
    SignMapper signMapper;

    @ApiOperation("保存已完成订单信息")
    @PostMapping
    public Result<?> save(@RequestBody Sign sign){
        sign.setStatus("运输中");

        signMapper.insert(sign);
        return Result.success();
    }
    @ApiOperation("分页查询已完成订单信息")
    @PostMapping("/get")
    public Result<?> findPage(@RequestBody Mapping mapping,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search){
       if(mapping.getRole()==1)
       {
           Page<Sign> signPage = signMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Sign>lambdaQuery().like(Sign::getName, search).eq(Sign::getSenderPhone,mapping.getPhoneNumber()));
           return Result.success(signPage);
       }
       else if(mapping.getRole()==2)
       {
           Page<Sign> signPage = signMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Sign>lambdaQuery().like(Sign::getName, search).eq(Sign::getDispatchNumber,mapping.getEmployeeId()));
           return Result.success(signPage);
       }else{
           Page<Sign> signPage = signMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Sign>lambdaQuery().like(Sign::getName, search));
           return Result.success(signPage);
       }

    }



    @ApiOperation("修改已完成订单信息")
     @PutMapping
    public Result<?> update(@RequestBody Sign sign){
        signMapper.updateById(sign);
        return Result.success();
     }
    @ApiOperation("删除已完成订单信息")
     @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable int id){
        signMapper.deleteById(id);
        return Result.success();
     }

    @ApiOperation("导出已完成订单信息")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Sign> list = signMapper.selectList(null);
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名


        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("已完成订单信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }
}
