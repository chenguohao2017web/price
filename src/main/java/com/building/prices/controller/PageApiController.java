package com.building.prices.controller;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PageApiController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/addRoom")
    public JSONObject addRoom(@RequestBody Map<String, String> map) {
        return roomService.addRomm(map);
    }
}
