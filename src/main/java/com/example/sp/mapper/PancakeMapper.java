package com.example.sp.mapper;

import org.apache.ibatis.annotations.*;
import com.example.sp.pojo.entity.Pancake;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PancakeMapper {
    @Select("select * from pancake")
    List<Pancake> getPancake();

    @Update("update pancake set is_done=1 where id=#{id}")
    void putPancake(int id);

    @Insert("insert into pancake(title, create_time, ddl, is_done) VALUES (#{title},#{create_time},#{ddl},0)")
    void postPancake(@Param("title") String title, @Param("create_time") LocalDate create_time, @Param("ddl") LocalDate ddl);

    @Delete("delete from pancake where id=#{id}")
    void deletePancake(int id);
}
