package com.building.prices.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.Detail;
import com.building.prices.entity.Room;
import com.building.prices.mapper.DetailMapper;
import com.building.prices.mapper.RoomMapper;
import com.building.prices.services.DetailService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailMapper detailMapper;

    @Autowired
    private RoomMapper roomMapper;

    /**
     * 根据年月id 更新或者保存
     * @param map
     * @return
     */
    @Override
    public JSONObject saveDetail(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();

        Room updateRoom = new Room();
        updateRoom.setStartTime(new Date());
        updateRoom.setPrice(Double.valueOf(map.get("price")));
        updateRoom.setId(Integer.valueOf(map.get("roomId")));
        //更新房租
        roomMapper.update(updateRoom);

        //根据年月id查询是否存在数据
        Detail currentDetail = detailMapper.getDetailByYearAndMonth(map);
        if(currentDetail != null) {
            currentDetail.setElectric(Double.valueOf(map.get("electric")));
            currentDetail.setWater(Double.valueOf(map.get("water")));
            boolean result = detailMapper.update(currentDetail);
            jsonObject.put("result", result);
            return jsonObject;
        } else {
            //新增数据
            Detail detail = new Detail();
            detail.setRoomId(Integer.valueOf(map.get("roomId")));
            detail.setWater(Double.valueOf(map.get("water")));
            detail.setElectric(Double.valueOf(map.get("electric")));
            detail.setYear(Integer.valueOf(map.get("year")));
            detail.setMonth(Integer.valueOf(map.get("month")));
            boolean result = detailMapper.addDetail(detail);
            jsonObject.put("result", result);
            return jsonObject;
        }
    }

    @Override
    public JSONObject addDetail(Map<String, String> map) {
        //获取年份
        Integer year = Integer.valueOf(map.get("year"));
        Integer month = Integer.valueOf(map.get("month"));
        Double electric = Double.valueOf(map.get("electric"));
        Double water = Double.valueOf(map.get("water"));
        Integer roomId = Integer.valueOf(map.get("roomId"));

        //根据年份，月份获取上个月的detail对象
        Detail beforeDetail = detailMapper.getDetailByYearAndMonth(map);

        if(month.equals(12)) {
            month = 1;
            year +=1;
        } else {
            month +=1;
        }
        Detail detail = new Detail();
        detail.setElectric(electric);
        detail.setWater(water);
        detail.setMonth(month);
        detail.setYear(year);
        detail.setRoomId(roomId);
        //查询当前用户的屋租
        Room room = roomMapper.getRoomById(roomId);
        Double totalPrice = calculateTotalPrice(beforeDetail, detail, room.getPrice());
        detail.setTotalPrice(totalPrice);
        boolean result = detailMapper.addDetail(detail);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }



    private Double calculateTotalPrice(Detail beforeDetail, Detail detail, Double price) {
        //计算差价
        //计算电费
        Double beforeElectric = beforeDetail.getElectric();
        Double afterElectric = detail.getElectric();
        //上个月
        BigDecimal b1 = new BigDecimal(Double.toString(beforeElectric));
        //当前月
        BigDecimal b2 = new BigDecimal(Double.toString(afterElectric));
        BigDecimal percent = new BigDecimal(Double.toString(1.5));
        //计算出电费的BigDecimal类型
        BigDecimal electricResult = (b2.subtract(b1)).multiply(percent);

        //计算水费
        Double beforeWater = beforeDetail.getWater();
        Double afterWater = detail.getWater();
        BigDecimal a1 = new BigDecimal(Double.toString(beforeWater));
        BigDecimal a2 = new BigDecimal(Double.toString(afterWater));
        BigDecimal percent1 = new BigDecimal(Double.toString(4));
        //计算出电费的BigDecimal类型
        BigDecimal waterResult = (a2.subtract(a1)).multiply(percent1);
        BigDecimal priceResult = new BigDecimal(price.toString());
        return electricResult.add(waterResult).add(priceResult).doubleValue();
    }
}
