package com.bjtu.web.spring_boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@ApiModel("订单实体类")
@TableName("orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private int id;

    @JsonProperty("date")
    @ApiModelProperty(value = "取件时间")
    private LocalDateTime pickUpTime;

    @ApiModelProperty(value = "订单时间")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "物品名称")
    private String name;

    @ApiModelProperty(value = "数量")
    private int quantity;

    @ApiModelProperty(value = "重量")
    private double weight;

    @JsonProperty("recipient")
    @ApiModelProperty(value = "收件人姓名")
    private String recipientName;

    @ApiModelProperty(value = "收件人电话")
    private String recipientPhone;

    @ApiModelProperty(value = "收件人地址")
    private String recipientAddress;

    @ApiModelProperty(value = "寄件人姓名")
    private String senderName;

    @ApiModelProperty(value = "寄件人电话")
    private String senderPhone;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "快递员姓名")
    private String courierName;

    @ApiModelProperty(value = "快递员电话")
    private String courierPhone;

    @TableField(exist = false)
    @ApiModelProperty(value = "员工ID")
    private long employeeId;

    @TableField(exist = false)
    @ApiModelProperty(value = "寄件人")
    private String sender;

}
