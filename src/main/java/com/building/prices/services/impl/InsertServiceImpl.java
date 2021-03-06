package com.building.prices.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.Detail;
import com.building.prices.entity.InsertPtVo;
import com.building.prices.entity.InsertVo;
import com.building.prices.entity.Room;
import com.building.prices.mapper.DetailMapper;
import com.building.prices.mapper.InsertMapper;
import com.building.prices.mapper.RoomMapper;
import com.building.prices.services.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class InsertServiceImpl implements InsertService {

    @Autowired
    private InsertMapper insertMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private DetailMapper detailMapper;

//    @Override
//    public JSONObject insertDetailList(InsertPtVo insertPtVo) {
//        List<InsertVo> insertVos = insertPtVo.getList();
//        Integer year = insertPtVo.getYear();
//        Integer month = insertPtVo.getMonth();
//
//
//        List<Detail> detailList = new ArrayList<>();
//
//        //根据年月查询所有详情列表
//        List<Room> allList = roomMapper.getList(year, month);
//
//        //根据提交上来的数据进行筛选
//        for(InsertVo insertVo : insertVos) {
//            //在这里进行detail对象的构建
//            Detail detail = new Detail();
//            detail.setRoomId(insertVo.getId());
//            detail.setWater(insertVo.getWater());
//            detail.setElectric(insertVo.getElectric());
//            detail.setYear(year);
//            detail.setMonth(month);
//            //totalPrice
//            //根据年份，月份获取上个月的detail对象
//            Integer beforeMonth = month;
//            Integer beforeYear = year;
//            if(month.equals(1)) {
//                beforeMonth = 12;
//                beforeYear -= 1;
//            } else {
//                beforeYear = year;
//                beforeMonth -=1;
//            }
//            Map<String,String> map = new HashMap<>();
//            map.put("month", beforeMonth.toString());
//            map.put("year", beforeYear.toString());
//            map.put("roomId",insertVo.getId().toString());
//            Detail beforeDetail = detailMapper.getDetailByYearAndMonth(map);
//
//            //查询当前用户的屋租
//            Room room = roomMapper.getRoomById(insertVo.getId());
//
//
//            Double totalPrice = calculateTotalPrice(beforeDetail, detail, room.getPrice());
//            detail.setTotalPrice(totalPrice);
//            detailList.add(detail);
//        }
//
//
//        JSONObject jsonObject = new JSONObject();
//        boolean bool = insertMapper.InsertList(detailList);
//        jsonObject.put("result", bool);
//
//        return jsonObject;
//    }

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

    /**
     * 批量注入接口
     * @param list
     * @return
     */
    @Override
    public JSONObject insertDetailList(InsertPtVo insertPtVo) {
        List<Room> roomList = insertPtVo.getList();
        Integer year = insertPtVo.getYear();
        Integer month = insertPtVo.getMonth();
        for(Room room: roomList) {
            //根据年月roomid查询是否存在数据 如果存在更新，否则添加数据
            Map<String, String> map = new HashMap<>();
            map.put("year", year.toString());
            map.put("month", month.toString());
            map.put("roomId", room.getId().toString());
            Detail detail = detailMapper.getDetailByYearAndMonth(map);
            if(detail != null) {
                //更新数据
                detail.setWater(room.getDetail().getWater());
                detail.setElectric(room.getDetail().getElectric());
                detailMapper.update(detail);
            } else {
                //新增数据
                Detail newDetail = new Detail();
                newDetail.setRoomId(room.getId());
                newDetail.setElectric(room.getDetail().getElectric());
                newDetail.setWater(room.getDetail().getWater());
                newDetail.setMonth(month);
                newDetail.setYear(year);
                detailMapper.addDetail(newDetail);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "succ");
        return jsonObject;
    }
}
