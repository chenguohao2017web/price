package com.building.prices.mapper;


import com.building.prices.entity.Detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsertMapper {

    boolean InsertList(List<Detail> list);
}
