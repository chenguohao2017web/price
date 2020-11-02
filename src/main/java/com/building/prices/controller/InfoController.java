package com.building.prices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfoController {

    private static Logger log = LoggerFactory.getLogger(InfoController.class);

    @GetMapping("/test")
    public Object forTest(){
        return null;
    }
}
