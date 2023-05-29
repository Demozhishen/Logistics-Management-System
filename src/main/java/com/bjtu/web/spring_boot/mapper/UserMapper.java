package com.bjtu.web.spring_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.web.spring_boot.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Repository
public interface UserMapper extends BaseMapper<User> {

   /* @Select("select * from user where nickName like '%${search}%'")
    public List<User> findAll(String search);

    @Insert("insert into user(username, nickName, age, sex, address) values (#{username},#{nickName},#{age},#{sex},#{address})")
    public void save(User user);

    @Update("update user set username=#{username},nickName=#{username},age=#{age},sex=#{sex},address=#{address} where id=#{id}")
    public void MyUpdateById(User user);

    @Delete("delete from user where id=#{i}")
    public void  delete(int i);


    @Select(" select count(*) from user where username=#{username} and password=#{password}")
    int checkUser(String username,String password);


    @Select("select count(*) from user where username=#{username}")
    int checkUsername(String username);*/
}
