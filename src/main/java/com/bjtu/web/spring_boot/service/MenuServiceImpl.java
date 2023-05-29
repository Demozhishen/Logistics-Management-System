package com.bjtu.web.spring_boot.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bjtu.web.spring_boot.entity.Menu;
import com.bjtu.web.spring_boot.entity.Staff;
import com.bjtu.web.spring_boot.entity.User;
import com.bjtu.web.spring_boot.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenu(Integer role) {
        List<Menu> menus = menuMapper.selectList(Wrappers.<Menu>lambdaQuery().eq(Menu::getRole, role));
       //找出一级菜单
        List<Menu> parentNodes = menus.stream().filter(menu -> menu.getPid() == null).toList();
        //找出一级菜单的子菜单
        for (Menu parentNode : parentNodes) {
            parentNode.setChildren( menus.stream().filter(menu -> parentNode.getId().equals(menu.getPid())).collect(Collectors.toList()));
        }
        return parentNodes;
    }

}
