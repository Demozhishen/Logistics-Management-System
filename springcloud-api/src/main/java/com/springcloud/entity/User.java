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


@ApiModel("用户实体类")
@TableName("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @ApiModelProperty("用户id")
    @TableId(value = "user_id",type = IdType.AUTO)
    private int userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户姓名")
    private String fullName;
    @ApiModelProperty("用户性别")
    private String sex;
    @ApiModelProperty("用户电话")
    private String phoneNumber;
    @ApiModelProperty("用户地址")
    private String address;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户角色")
    private int role;
    @ApiModelProperty("token")
    @TableField(exist = false)
    private String token;

}
