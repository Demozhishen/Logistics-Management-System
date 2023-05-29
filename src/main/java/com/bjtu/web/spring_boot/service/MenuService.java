package com.bjtu.web.spring_boot.service;

import com.bjtu.web.spring_boot.entity.Menu;
import com.bjtu.web.spring_boot.entity.Staff;
import com.bjtu.web.spring_boot.entity.User;

import java.util.List;

public interface MenuService {
    public List<Menu> selectMenu(Integer role);

}
