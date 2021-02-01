package com.building.prices.services;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoomService {
    //获取所有房间
    JSONObject getList();

    JSONObject getDetailById(Integer year,Integer month,Integer id);

    JSONObject addRomm(Map<String, String> map);

    JSONObject getRoomDetail(Map<String, String> map);

    JSONObject getOut(Map<String, String> map);
}
