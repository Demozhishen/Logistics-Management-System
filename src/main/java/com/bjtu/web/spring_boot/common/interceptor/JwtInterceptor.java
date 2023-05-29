package com.bjtu.web.spring_boot.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;


import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.bjtu.web.spring_boot.common.Result;
import com.bjtu.web.spring_boot.entity.Staff;
import com.bjtu.web.spring_boot.entity.User;
import com.bjtu.web.spring_boot.exception.ServiceException;
import com.bjtu.web.spring_boot.mapper.StaffMapper;
import com.bjtu.web.spring_boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StaffMapper staffMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "无token，请重新登录");
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "token验证失败，请重新登录");
        }
        // 根据token中的userid查询数据库
        User user = userMapper.selectById(userId);
        Staff staff=staffMapper.selectById(userId);
        if (user == null&&staff==null) {
            throw new ServiceException("401", "用户不存在，请重新登录");
        }


        // 用户密码加签验证 token
        if(staff==null)
        {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                jwtVerifier.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException("401", "token验证失败，请重新登录");
            }
            return true;
        }
        else {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(staff.getEmployeePassword())).build();
            try {
                jwtVerifier.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException("401", "token验证失败，请重新登录");
            }
            return true;
        }

    }
}
