package com.building.prices.controller;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.Room;
import com.building.prices.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class homeController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/")
    public String toHome() {
        return "index/index";
    }

    @PostMapping("/getList")
    @ResponseBody
    public JSONObject getList(@RequestBody Map<String, String> map) {
        Integer year = Integer.valueOf(map.get("year"));
        Integer month = Integer.valueOf(map.get("month"));
       return roomService.getList(year, month);
    }
}
