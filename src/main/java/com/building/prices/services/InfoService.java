package com.building.prices.services;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.Info;
import com.building.prices.mapper.InfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoService {

    private static Logger log = LoggerFactory.getLogger(InfoService.class);

    @Autowired
    private InfoMapper infoMapper;

    public Info getInfoById(Integer id) {
        return infoMapper.selectOneById(id);
    }

    public void calculateInfo(Integer requestYear, Integer requestMonth) {
//        Integer currentYear = requestYear;
//        Integer currentMonth = requestMonth;
//        Integer lastYear = 0;
//        Integer lastMonth = 0;
//        //需要对参数进行处理
//        if(requestMonth.equals(1) ) {
//            lastMonth = 12;
//            lastYear = requestYear - 1;
//
//        } else {
//            lastMonth = requestMonth - 1;
//            lastYear = requestYear;
//        }
//
//        //先查询传递入来的年月总条数
//        List<Info> currentMonthInfos = infoMapper.selectListByYearAndMonth(currentYear, currentMonth);
//        //再查询上个月的总条数
//        List<Info> lastMonthInfos = infoMapper.selectListByYearAndMonth(lastYear, lastMonth);
//
//        List<Object> resultList = new ArrayList<>();
//
//        for(int i = 0; i < currentMonthInfos.size(); i++) {
//            //由于已进行排序，两个数组取出来的必然是相同房间
//            double sum = calculatePrice(lastMonthInfos.get(i), currentMonthInfos.get(i));
//            Integer roomNumber = lastMonthInfos.get(i).getRoomNumber();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("roomNumber:", roomNumber);
//            jsonObject.put("sum", sum);
//            resultList.add(jsonObject);
//        }
//
//        JSONObject jsonObject2 = new JSONObject();
//        jsonObject2.put("'resultList'", resultList);
//        return jsonObject2;
    }

    private void calculatePrice(Info before, Info after) {
//        log.info(before.getRoomNumber().toString());
//        log.info(after.getRoomNumber().toString());
//        if(before.getRoomNumber() != after.getRoomNumber()) {
//            throw new RuntimeException("查询的数据异常，出现不同房间的计算价格");
//        } else {
//            //计算电费
//            Double beforeElectric = before.getElectric();
//            Double afterElectric = after.getElectric();
//            BigDecimal b1 = new BigDecimal(Double.toString(beforeElectric));
//            BigDecimal b2 = new BigDecimal(Double.toString(afterElectric));
//            BigDecimal percent = new BigDecimal(Double.toString(1.5));
//            //计算出电费的BigDecimal类型
//            BigDecimal electricResult = (b2.subtract(b1)).multiply(percent);
//
//            //计算水费
//            Double beforeWater = before.getWater();
//            Double afterWater = after.getWater();
//            BigDecimal a1 = new BigDecimal(Double.toString(beforeWater));
//            BigDecimal a2 = new BigDecimal(Double.toString(afterWater));
//            BigDecimal percent1 = new BigDecimal(Double.toString(4));
//            //计算出电费的BigDecimal类型
//            BigDecimal waterResult = (a2.subtract(a1)).multiply(percent1);
//
//            return electricResult.add(waterResult).doubleValue();
//        }
    }
}
