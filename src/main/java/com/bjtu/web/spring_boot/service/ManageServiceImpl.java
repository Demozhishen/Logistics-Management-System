package com.bjtu.web.spring_boot.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bjtu.web.spring_boot.entity.Order;
import com.bjtu.web.spring_boot.entity.Sign;
import com.bjtu.web.spring_boot.entity.Staff;
import com.bjtu.web.spring_boot.entity.User;
import com.bjtu.web.spring_boot.mapper.ItemMapper;
import com.bjtu.web.spring_boot.mapper.OrderMapper;
import com.bjtu.web.spring_boot.mapper.StaffMapper;
import com.bjtu.web.spring_boot.mapper.UserMapper;
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
