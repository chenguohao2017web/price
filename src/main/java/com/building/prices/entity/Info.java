package com.building.prices.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Info {
    private Integer id;
    private Integer year;
    private Integer month;
    private Integer roomNumber;
    private Integer buildType;
    private Double electric;
    private Double water;
    private Date createTime;
    private Date updateTime;
    private Integer roomPrice;
}
