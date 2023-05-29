package com.bjtu.web.spring_boot.controller;



import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bjtu.web.spring_boot.common.Result;
import com.bjtu.web.spring_boot.entity.Mapping;
import com.bjtu.web.spring_boot.entity.Staff;
import com.bjtu.web.spring_boot.entity.User;
import com.bjtu.web.spring_boot.mapper.MenuMapper;
import com.bjtu.web.spring_boot.mapper.StaffMapper;
import com.bjtu.web.spring_boot.mapper.UserMapper;
import com.bjtu.web.spring_boot.service.ManageService;
import com.bjtu.web.spring_boot.service.MenuService;
import com.bjtu.web.spring_boot.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "登陆注册")
@RestController
@CrossOrigin
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
            String token = TokenUtils.genToken(String.valueOf(user1.getUserId()), user1.getPassword());
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
            String token = TokenUtils.genToken(String.valueOf(staff.getEmployeeId()), staff.getEmployeePassword());
            staff.setToken(token);
            Mapping mapping = new Mapping(staff);
            mapping.setUsername(staff.getEmployeeName());
            mapping.setToken(token);
            mapping.setMenu(menuService.selectMenu(staff.getRole()));
            return Result.success(mapping);
        }




    }


}
