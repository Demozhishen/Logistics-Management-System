package com.springcloud.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.springcloud.common.Result;
import com.springcloud.entity.Mapping;
import com.springcloud.entity.Staff;
import com.springcloud.entity.User;
import com.springcloud.mapper.StaffMapper;
import com.springcloud.mapper.UserMapper;
import com.springcloud.service.MenuService;
import com.springcloud.utils.JwtUtil;
import com.springcloud.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(tags = "登陆注册")
@RestController

public class LoginController {

    @Autowired
    private UserMapper userMapper; //注入UserService
    @Autowired
    private StaffMapper staffMapper;


    @Autowired
    private MenuService menuService;
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<?> findUser(@RequestBody User user){



        if(user.getUsername().length()<11)
        {
            User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
            if(user1==null){
                return Result.error("200","未查询到用户信息");
            }
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), user1.getUsername(), null);

            Mapping mapping = new Mapping(user1);
            mapping.setToken(token);
            mapping.setMenu(menuService.selectMenu(user1.getRole()));
            return Result.success(mapping);
        }
        else {
            Staff staff = staffMapper.selectOne(Wrappers.<Staff>lambdaQuery().eq(Staff::getEmployeeId, user.getUsername()).eq(Staff::getEmployeePassword, user.getPassword()));
            if(staff==null){
                return Result.error("200","未查询到用户信息");
            }
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), staff.getEmployeeName(), null);
            staff.setToken(token);
            Mapping mapping = new Mapping(staff);
            mapping.setUsername(staff.getEmployeeName());
            mapping.setToken(token);
            mapping.setMenu(menuService.selectMenu(staff.getRole()));
            return Result.success(mapping);
        }




    }


}
