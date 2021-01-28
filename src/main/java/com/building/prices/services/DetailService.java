package com.building.prices.services;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.Detail;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DetailService {

    JSONObject addDetail(Map<String, String> map);

}
