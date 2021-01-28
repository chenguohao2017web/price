package com.building.prices.entity;

import java.util.Date;

public class InsertVo{

    private Double electric;
    private Double water;

    private Integer id;
    private Integer roomNum;
    private Double price;
    private Date startTime;
    private Detail detail;

    public Double getElectric() {
        return electric;
    }

    public void setElectric(Double electric) {
        this.electric = electric;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
