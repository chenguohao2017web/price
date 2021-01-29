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
     * 根据传入的年份和月份去统计当月需要进行房租计算
     * @param year
     * @param month
     * @return
     */
    @Override
    public JSONObject getList(Integer year, Integer month) {

        JSONObject jsonObject = new JSONObject();


        //首先获取当前所有的用户列表 roomList
        List<Room> roomList = roomMapper.getRoomList();

        //查询该月中 detail的总条数 总而获取ids通过前端判断筛选
        List<Detail> detailList = detailMapper.getList(year, month);

        if(detailList.size() > 0) {
            //如果找到了已经运算过，标记改字段
            jsonObject.put("list", roomList);
            jsonObject.put("finishList", detailList);
            return jsonObject;
        } else {
            //所有用户该月都没有进行过运算
            jsonObject.put("list", roomList);
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
