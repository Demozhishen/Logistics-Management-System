package com.bjtu.web.spring_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.web.spring_boot.entity.Order;
import com.bjtu.web.spring_boot.entity.Sign;
import org.springframework.stereotype.Repository;

@Repository
public interface SignMapper extends BaseMapper<Sign> {
}
