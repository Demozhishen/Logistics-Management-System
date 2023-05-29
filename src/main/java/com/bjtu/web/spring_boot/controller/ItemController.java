package com.bjtu.web.spring_boot.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.web.spring_boot.common.Result;
import com.bjtu.web.spring_boot.entity.Item;
import com.bjtu.web.spring_boot.entity.Item;
import com.bjtu.web.spring_boot.entity.Mapping;
import com.bjtu.web.spring_boot.mapper.ItemMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
@Api(tags = "货物管理")
@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {
    @Resource
   ItemMapper itemMapper;

    @ApiOperation("保存货物信息")
    @PostMapping
    public Result<?> save(@RequestBody Item item){
        item.setStatus("待发货");

        itemMapper.insert(item);
        return Result.success();
    }
    @ApiOperation("分页查询货物信息")
    @PostMapping("/get")
    public Result<?> findPage(@RequestBody Mapping mapping,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search){
        if(mapping.getRole()==1)
        {
            Page<Item> itemPage = itemMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Item>lambdaQuery().like(Item::getName, search).eq(Item::getSender,mapping.getUserId()));

            return Result.success(itemPage);
        }
        else {
            Page<Item> itemPage = itemMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Item>lambdaQuery().like(Item::getName, search));

            return Result.success(itemPage);
        }

    }


    @ApiOperation("修改货物信息")
     @PutMapping
    public Result<?> update(@RequestBody Item item){
        itemMapper.updateById(item);
        return Result.success();
     }
    @ApiOperation("删除货物信息")
     @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable int id){
        itemMapper.deleteById(id);
        return Result.success();
     }

    @ApiOperation("导出货物信息")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Item> list = itemMapper.selectList(null);
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名


        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("商品信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }
}
