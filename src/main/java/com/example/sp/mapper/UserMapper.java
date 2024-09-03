package com.example.sp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.sp.pojo.entity.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User getUser(@Param("username") String username,@Param("password")String password);
}
