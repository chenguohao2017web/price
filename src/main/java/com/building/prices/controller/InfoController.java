package com.building.prices.controller;

import com.building.prices.entity.Info;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoController {

    private static Logger log = LoggerFactory.getLogger(InfoController.class);

    @Mapper
    private com.building.prices.mapper.InfoMapper infoMapper;

    @GetMapping("/test")
    public Object forTest(){
        List<Info> all = infoMapper.findAll();
        for(Info i : all) {
            log.info(String.valueOf(i.getYear()));
        }
        return null;
    }
}
