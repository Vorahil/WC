package com.example.sp.sevice.impl;

import com.example.sp.utils.JWTUtil;
import jakarta.annotation.Resource;
import com.example.sp.mapper.UserMapper;
import com.example.sp.pojo.entity.User;
import com.example.sp.pojo.response.ResultData;
import com.example.sp.sevice.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper mapper;
    @Override
    public ResultData<Object> login(String username, String password) {
        User user = mapper.getUser(username, password);
        String token= JWTUtil.generateToken(user);
        return ResultData.success(token);
    }
}
