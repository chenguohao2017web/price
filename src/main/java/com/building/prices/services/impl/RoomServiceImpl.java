package com.building.prices.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.building.prices.entity.Detail;
import com.building.prices.entity.Room;
import com.building.prices.mapper.DetailMapper;
import com.building.prices.mapper.RoomMapper;
import com.building.prices.services.RoomService;
import com.building.prices.utils.Common;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private DetailMapper detailMapper;

    /**
     * 首页查询接口，查询所有用户
     * @param year
     * @param month
     * @return
     */
    @Override
    public JSONObject getList() {
        JSONObject jsonObject = new JSONObject();
        //查询该月中所有的用户
        List<Room> roomList = roomMapper.getRoomList();
        jsonObject.put("list", roomList);
        return jsonObject;
    }

    /**
     * 根据用户id获取该房间详情
     * @param map
     * @return
     */
    @Override
    public JSONObject getRoomDetail(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();
        //当前月的数据
        Room currentRoom = roomMapper.getRoomDetail(map);
        //上个月数据
        Integer beforeYear = Integer.valueOf(map.get("year"));
        Integer beforeMonth = Integer.valueOf(map.get("month"));

        //根据id查询room对象
        Room oriRoom = roomMapper.getRoomById(Integer.valueOf(map.get("id")));

        //如果当前月份为1
        if(beforeMonth.equals(1)) {
            beforeMonth = 12;
            beforeYear -=1;
        } else {
            beforeMonth -=1;
        }
        map.put("year", beforeYear.toString());
        map.put("month", beforeMonth.toString());
        Room beforeRoom = roomMapper.getRoomDetail(map);
        jsonObject.put("before", beforeRoom);
        jsonObject.put("current", currentRoom);
        jsonObject.put("origin", oriRoom);
        return jsonObject;
    }


    /**
     * 退房逻辑
     * @param map {roomNum}
     * @return
     */
    @Override
    public JSONObject getOut(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();
        String roomNum = map.get("roomNum");

        //根据roomNum先查询是否存在该room对象数据，如果存在则移除
        Room room = roomMapper.getRoomByRoomNum(roomNum);
        if(room !=null) {
            int result = roomMapper.delByRoomNum(roomNum);

            //移除detail表对应该roomNum数据
            int result2 = detailMapper.delByRoomId(room.getId());
            jsonObject.put("result","succ");
            return jsonObject;
        } else {
            jsonObject.put("result","fail");
            return jsonObject;
        }
    }

    @Override
    public JSONObject getDetailById(Integer year, Integer month, Integer id) {
        JSONObject jsonObject = new JSONObject();
        Room room = roomMapper.getDetailById(year, month, id);
        //上个月的记录
        if(month.equals(1)) {
            month = 12;
            year -= 1;
        } else {
            month -=1;
        }

        Room beforeRoom = roomMapper.getDetailById(year, month, id);

        if(room != null) {
            jsonObject.put("detail", room);
            jsonObject.put("before", beforeRoom);
        } else {
            //查询不出当月的详情 需要进行填写计算
            jsonObject.put("before", beforeRoom);
        }

        return jsonObject;
    }

    /**
     * 新增数据
     * @param map
     * @return
     */
    @Override
    public JSONObject addRomm(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();

        Integer year = Integer.valueOf(map.get("year"));
        Integer month = Integer.valueOf(map.get("month"));
        String roomNum = map.get("roomNum");
        Double price = Double.valueOf(map.get("price"));
        Double electric = Double.valueOf(map.get("electric").toString());
        Double water = Double.valueOf(map.get("water").toString());

        //需要先判断房间是否存在
        Room room = roomMapper.findRoomByRoomNum(roomNum, year, month);
        if(room != null) {
            jsonObject.put("result","fail");
            return jsonObject;
        } else {
            //添加房间数据
            Room addRoom = new Room();
            addRoom.setRoomNum(roomNum);
            addRoom.setPrice(price);
            addRoom.setStartTime(new Date());
            roomMapper.insertRoom(addRoom);
            Integer roomId = addRoom.getId();
            //添加房间对应该月的数据
            Detail addDetail = new Detail();
            addDetail.setMonth(month);
            addDetail.setYear(year);
            addDetail.setElectric(electric);
            addDetail.setWater(water);
            addDetail.setRoomId(Integer.valueOf(roomId));
            boolean b = detailMapper.addDetail(addDetail);

            jsonObject.put("result", b);
            return jsonObject;
        }
    }



}
