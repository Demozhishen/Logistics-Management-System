package com.springcloud.service;

import com.springcloud.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> selectMenu(Integer role);

}
