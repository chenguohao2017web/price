package com.building.prices.mapper;


import com.building.prices.entity.Info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoMapper {
    Info selectOneById(Integer id);

    List<Info> selectListByYearAndMonth(@Param("requestYear") Integer requestYear,
                                        @Param("requestMonth") Integer requestMonth);
}