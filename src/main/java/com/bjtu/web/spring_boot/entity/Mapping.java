package com.bjtu.web.spring_boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("员工和用户映射实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mapping {
    @ApiModelProperty(value = "用户ID")
    private int userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "全名")
    private String fullName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "角色")
    private int role;

    @ApiModelProperty(value = "员工ID")
    private long employeeId;

    @ApiModelProperty(value = "员工密码")
    private String employeePassword;

    @ApiModelProperty(value = "员工姓名")
    private String employeeName;

    @ApiModelProperty(value = "员工身份证号码")
    private String employeeIdCard;

    @ApiModelProperty(value = "员工电话号码")
    private String employeePhone;

    @ApiModelProperty(value = "员工地址")
    private String employeeAddress;

    @ApiModelProperty(value = "访问令牌")
    private String token;

    @ApiModelProperty(value = "菜单")
    private List<Menu> menu;



    public Mapping(User user1) {
     userId=user1.getUserId();
        username=user1.getUsername();
        password=user1.getPassword();
        fullName=user1.getFullName();
        sex=user1.getSex();
        phoneNumber=user1.getPhoneNumber();
        address=user1.getAddress();
        email=user1.getEmail();
        role=user1.getRole();
    }
    public Mapping(Staff staff){
        employeePassword=staff.getEmployeePassword();
        employeeId=staff.getEmployeeId();
        employeeName=staff.getEmployeeName();
        employeeIdCard=staff.getEmployeeIdCard();
        employeePhone=staff.getEmployeePhone();
        employeeAddress=staff.getEmployeeAddress();
        role=staff.getRole();
    }

}
