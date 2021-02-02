package com.building.prices.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/addRoom")
    public String addRoom() {
        return "addRoom/addRoom";
    }
}
