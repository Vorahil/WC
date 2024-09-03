package com.example.sp.sevice;

import com.example.sp.pojo.response.ResultData;

public interface UserService {
        ResultData<Object> login(String username, String password);

}
