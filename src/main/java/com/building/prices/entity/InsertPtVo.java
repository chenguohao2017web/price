package com.building.prices.entity;

import java.util.List;

public class InsertPtVo {

    private List<InsertVo> list;
    private Integer year;
    private Integer month;

    public List<InsertVo> getList() {
        return list;
    }

    public void setList(List<InsertVo> list) {
        this.list = list;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
