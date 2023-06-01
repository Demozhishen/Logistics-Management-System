package com.springcloud.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.springcloud.entity.Order;
import com.springcloud.entity.Sign;
import com.springcloud.entity.Staff;
import com.springcloud.entity.User;
import com.springcloud.mapper.ItemMapper;
import com.springcloud.mapper.StaffMapper;
import com.springcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ManageServiceImpl implements ManageService{

    @Autowired
    StaffMapper staffMapper;

    @Autowired
    UserMapper userMapper;


    @Autowired
    ItemMapper itemMapper;


    @Override
    public Order CollectItem(Order order) {
        itemMapper.deleteById(order.getId());
        order.setId(generateOrderNumber());
        order.setPickUpTime(order.getPickUpTime());
        Staff staff = staffMapper.selectById(order.getEmployeeId());
        order.setCourierName(staff.getEmployeeName());
        order.setCourierPhone(staff.getEmployeePhone());
        User user = userMapper.selectById(order.getSender());
        order.setSenderName(user.getFullName());
        order.setSenderPhone(user.getPhoneNumber());
        order.setStatus("运输中");
        return order;
    }

    @Override
    public Sign SignItem(Sign sign) {
        sign.setStatus("已签收");
        Staff staff = staffMapper.selectOne(Wrappers.<Staff>lambdaQuery().eq(Staff::getEmployeeName, sign.getCourierName()).eq(Staff::getEmployeePhone, sign.getCourierPhone()));
        sign.setDispatchNumber(staff.getEmployeeId());
        sign.setPickUpTime(sign.getPickUpTime());
        sign.setSignTime(sign.getSignTime());
        return sign;
    }

    private static int generateOrderNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;
        return randomNumber;
    }
}
