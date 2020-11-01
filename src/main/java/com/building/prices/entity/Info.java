package com.building.prices.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Info {
    private int id;
    private int year;
    private int month;
    private int roomNumber;
    private int buildType;
    private int electric;
    private int water;
    private Date createTime;
    private Date updateTime;
}
