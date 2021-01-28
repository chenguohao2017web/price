package com.building.prices.controller;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.services.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfoController {

    private static Logger log = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;

    /**
     * 参数 当前年份
     * 参数 当前月份
     * 需求根据输入噶年份月份查询计算
     * @return
     */
    @PostMapping("/calculateMonth")
    public void calculateMonthPrice () {
        Integer requestYear = 2020;
        Integer requestMonth = 10;
//        return infoService.calculateInfo(requestYear, requestMonth);
    }
}
