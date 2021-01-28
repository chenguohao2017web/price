package com.building.prices.services;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.InsertPtVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface InsertService {

    JSONObject insertDetailList(InsertPtVo insertPtVo);
}
