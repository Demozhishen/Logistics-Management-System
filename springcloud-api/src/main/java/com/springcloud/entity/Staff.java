package com.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("员工实体类")
@TableName("employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @TableId(value = "employee_id",type = IdType.INPUT)
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

    @ApiModelProperty(value = "角色")
    private int role;

    @ApiModelProperty(value = "访问令牌")
    @TableField(exist = false)
    private String token;
}
