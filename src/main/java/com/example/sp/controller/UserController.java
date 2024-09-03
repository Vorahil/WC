package com.example.sp.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.sp.pojo.entity.User;
import com.example.sp.pojo.response.ResultData;
import com.example.sp.sevice.UserService;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResultData<Object> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }
}
