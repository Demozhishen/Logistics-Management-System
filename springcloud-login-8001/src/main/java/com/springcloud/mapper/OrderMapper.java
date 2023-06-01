package com.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.springcloud.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
