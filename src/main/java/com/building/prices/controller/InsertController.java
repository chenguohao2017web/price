package com.building.prices.controller;

import com.building.prices.entity.InsertPtVo;
import com.building.prices.services.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class InsertController {


    @Autowired
    private InsertService insertService;

    @GetMapping("/insert")
    public String insert() {
        return "insert/insert";
    }

    @PostMapping("/insertDetails")
    @ResponseBody
    public void inserts(@RequestBody InsertPtVo insertPtVo) {
        insertService.insertDetailList(insertPtVo);
    }
}
