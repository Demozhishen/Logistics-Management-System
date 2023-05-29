package com.bjtu.web.spring_boot.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.web.spring_boot.common.Result;
import com.bjtu.web.spring_boot.entity.Item;
import com.bjtu.web.spring_boot.entity.Order;
import com.bjtu.web.spring_boot.entity.Sign;
import com.bjtu.web.spring_boot.mapper.ItemMapper;
import com.bjtu.web.spring_boot.mapper.OrderMapper;
import com.bjtu.web.spring_boot.mapper.SignMapper;
import com.bjtu.web.spring_boot.service.ManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "揽收和签收管理")
@RestController
@CrossOrigin
@RequestMapping("/manage")
public class ManageController {
    @Resource
   ItemMapper itemMapper;


    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ManageService manageService;

    @Autowired
    SignMapper signMapper;

    @ApiOperation("揽收货物")
    @PostMapping("/collect")
    public Result<?> collect(@RequestBody Order order){
        Order order1 = manageService.CollectItem(order);
        orderMapper.insert(order1);

        return Result.success();
    }

    @ApiOperation("签收订单")
    @PostMapping("/sign")
    public Result<?> sign(@RequestBody Sign sign){
        System.out.println("-----------------------------------------------------");
        System.out.println(sign);
        Sign sign1 = manageService.SignItem(sign);
        orderMapper.deleteById(sign.getId());
        signMapper.insert(sign1);


        return Result.success();
    }





}
