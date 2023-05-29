package com.bjtu.web.spring_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.web.spring_boot.entity.Staff;

import org.springframework.stereotype.Repository;

@Repository
public interface StaffMapper extends BaseMapper<Staff> {
}
