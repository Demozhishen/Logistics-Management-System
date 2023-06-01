package com.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.springcloud.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper extends BaseMapper<Item> {
}
