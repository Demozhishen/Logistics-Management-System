package com.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.springcloud.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {

}
