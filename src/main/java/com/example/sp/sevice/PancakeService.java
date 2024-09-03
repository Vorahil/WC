package com.example.sp.sevice;

import com.example.sp.pojo.entity.Pancake;
import com.example.sp.pojo.response.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
public interface PancakeService {
    ResultData<Map<String, List<Pancake>>> getPancake();

    ResultData<Object> putPancake(int pancakeId);

    ResultData<Object> postPancake(Pancake pancake);

    ResultData<Object> deletePancake(int pancakeId);
}
