package com.bjtu.web.spring_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.web.spring_boot.entity.Item;
import com.bjtu.web.spring_boot.entity.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
}
