package com.example.sp.sevice.impl;

import com.example.sp.sevice.PancakeService;
import jakarta.annotation.Resource;
import com.example.sp.mapper.PancakeMapper;
import com.example.sp.pojo.entity.Pancake;
import com.example.sp.pojo.response.ResultData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PancakeServiceImpl implements PancakeService {
    @Resource
    private PancakeMapper pancakeMapper;

    @Override
    public ResultData<Map<String, List<Pancake>>> getPancake() {
        Map<String, List<Pancake>> map = new HashMap<>();
        map.put("pancakes", pancakeMapper.getPancake());
        return ResultData.success(map);
    }

    @Override
    public ResultData<Object> postPancake(Pancake pancake) {
        if (pancake.getTitle() == null) {
            return ResultData.fail(400, "title不为空");
        }
        if (pancake.getCreate_time() == null) {
            return ResultData.fail(400, "create_time不为空");
        }
        if (pancake.getDdl() == null) {
            return ResultData.fail(400, "ddl不能为空");
        }
        pancakeMapper.postPancake(pancake.getTitle(), pancake.getCreate_time(), pancake.getDdl());
        return ResultData.success();
    }

    @Override
    public ResultData<Object> putPancake(int pancakeId) {
        pancakeMapper.putPancake(pancakeId);
        return ResultData.success();
    }

    @Override
    public ResultData<Object> deletePancake(int pancakeId) {
        pancakeMapper.deletePancake(pancakeId);
        return ResultData.success();
    }
}
