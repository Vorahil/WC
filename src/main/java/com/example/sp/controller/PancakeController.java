package com.example.sp.controller;

import com.example.sp.annotation.Role;
import com.example.sp.annotation.RoleRequired;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.example.sp.pojo.entity.Pancake;
import com.example.sp.pojo.response.ResultData;
import com.example.sp.sevice.PancakeService;

import java.util.List;
import java.util.Map;

@RestController
public class PancakeController {
    @Resource
    private PancakeService pancakeService;

    @GetMapping("/pancake")
    public ResultData<Map<String, List<Pancake>>> getPancake() {
        return pancakeService.getPancake();
    }

    @PostMapping("/pancake")
    @RoleRequired(Role.DEFAULT)
    public ResultData<Object> postPancake(@RequestBody Pancake pancake) {
        return pancakeService.postPancake(pancake);
    }

    @PutMapping("/pancake/{pancakeId}")
    @RoleRequired(Role.DEFAULT)
    public ResultData<Object> putPancake(@PathVariable("pancakeId") int pancakeId) {
        return pancakeService.putPancake(pancakeId);
    }

    @DeleteMapping("/pancake/{pancakeId}")
    @RoleRequired(Role.ADMIN)
    public ResultData<Object> deletePancake(@PathVariable("pancakeId") int pancakeId) {
        return pancakeService.deletePancake(pancakeId);
    }
}
