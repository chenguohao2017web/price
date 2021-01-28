package com.building.prices.mapper;

import com.building.prices.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomMapper {

    List<Room> getList(@Param("year")Integer year,
                       @Param("month")Integer month);

    List<Room> getRoomList();

    Room getDetailById(@Param("year")Integer year,
                       @Param("month")Integer month,
                       @Param("id")Integer id);

    Room findRoomByRoomNum(@Param("roomNum")Integer roomNum, @Param("year")Integer year, @Param("month")Integer month);

    int insertRoom(Room addRoom);

    void update(Room updateRoom);

    Room getRoomById(Integer id);
}
