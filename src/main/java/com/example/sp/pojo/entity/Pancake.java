package com.example.sp.pojo.entity;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Pancake {
    private int id;
    private String title;
    private LocalDate create_time;
    private LocalDate ddl;
    private Integer is_done;
}
