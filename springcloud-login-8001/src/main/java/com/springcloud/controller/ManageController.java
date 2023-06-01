package com.springcloud.controller;


import com.springcloud.common.Result;
import com.springcloud.entity.Order;
import com.springcloud.entity.Sign;
import com.springcloud.mapper.ItemMapper;
import com.springcloud.mapper.OrderMapper;
import com.springcloud.mapper.SignMapper;
import com.springcloud.service.ManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "揽收和签收管理")
@RestController

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
