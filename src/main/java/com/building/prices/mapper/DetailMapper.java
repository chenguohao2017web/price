package com.building.prices.mapper;

import com.building.prices.entity.Detail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DetailMapper {

    List<Detail> getList(@Param("year")Integer year,
                         @Param("month")Integer month);

    boolean addDetail(Detail detail);

    Detail getDetailByYearAndMonth(@Param("month")Integer month, @Param("year")Integer year, @Param("roomId")Integer roomId);

    boolean update(Detail updateDetail);
}
