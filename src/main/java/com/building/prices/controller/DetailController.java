package com.building.prices.controller;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.services.DetailService;
import com.building.prices.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DetailController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private DetailService detailService;


    @GetMapping("/detail")
    public String toDetail() {
        return "/detail/detail";
    }

    @PostMapping("/getRoomDetail")
    @ResponseBody
    public JSONObject getRoomDetail(@RequestBody Map<String, String> map) {
        return roomService.getRoomDetail(map);
    }

    @PostMapping("/addDetail")
    @ResponseBody
    public JSONObject addDetail(@RequestBody Map<String, String> map) {
        return detailService.addDetail(map);
    }
}
