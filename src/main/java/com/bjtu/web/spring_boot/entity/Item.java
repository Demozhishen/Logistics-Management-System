package com.bjtu.web.spring_boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("物品实体类")
@TableName("items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private int id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "数量")
    private int quantity;

    @ApiModelProperty(value = "重量")
    private double weight;

    @ApiModelProperty(value = "收件人")
    private String recipient;

    @ApiModelProperty(value = "收件人电话")
    private String recipientPhone;

    @ApiModelProperty(value = "收件人地址")
    private String recipientAddress;

    @ApiModelProperty(value = "日期")
    private LocalDateTime date;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "寄件人")
    private String sender;

}
