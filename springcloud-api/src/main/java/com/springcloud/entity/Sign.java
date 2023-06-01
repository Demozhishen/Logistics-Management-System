package com.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("签收订单实体类")
@TableName("sign")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sign {
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private int id;

    @JsonProperty("date")
    @ApiModelProperty(value = "取件时间")
    private LocalDateTime pickUpTime;

    @JsonProperty("orderDate")
    @ApiModelProperty(value = "运输时间")
    private LocalDateTime transportTime;

    @ApiModelProperty(value = "签收时间")
    private LocalDateTime signTime;

    @ApiModelProperty(value = "物品名称")
    private String name;

    @ApiModelProperty(value = "数量")
    private int quantity;

    @ApiModelProperty(value = "重量")
    private double weight;

    @ApiModelProperty(value = "收件人姓名")
    @JsonProperty("recipient")
    private String recipientName;

    @ApiModelProperty(value = "收件人电话")
    private String recipientPhone;

    @ApiModelProperty(value = "收件人地址")
    private String recipientAddress;

    @ApiModelProperty(value = "寄件人姓名")
    private String senderName;

    @ApiModelProperty(value = "寄件人电话")
    private String senderPhone;

    @ApiModelProperty(value = "派送单号")
    private long dispatchNumber;

    @ApiModelProperty(value = "快递员姓名")
    private String courierName;

    @ApiModelProperty(value = "快递员电话")
    private String courierPhone;

    @ApiModelProperty(value = "状态")
    private String status;

}
