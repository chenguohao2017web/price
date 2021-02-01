package com.building.prices.mapper;

import com.building.prices.entity.Detail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DetailMapper {

    List<Detail> getList(@Param("year")Integer year,
                         @Param("month")Integer month);

    boolean addDetail(Detail detail);

    Detail getDetailByYearAndMonth(Map<String, String> map);

    boolean update(Detail updateDetail);

    int saveDetail(Detail detail);

    int delByRoomId(Integer roomId);
}
