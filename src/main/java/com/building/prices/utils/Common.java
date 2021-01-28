package com.building.prices.utils;

import java.math.BigDecimal;

public class Common {

    public static Double calculatePrice(Double beforeWater, Double water, Double beforeElectric, Double electric, Double price) {

        //上个月
        BigDecimal b1 = new BigDecimal(Double.toString(beforeElectric));
        //当前月
        BigDecimal b2 = new BigDecimal(Double.toString(electric));
        BigDecimal percent = new BigDecimal(Double.toString(1.5));
        //计算出电费的BigDecimal类型
        BigDecimal electricResult = (b2.subtract(b1)).multiply(percent);

        //计算水费
        BigDecimal a1 = new BigDecimal(Double.toString(beforeWater));
        BigDecimal a2 = new BigDecimal(Double.toString(water));
        BigDecimal percent1 = new BigDecimal(Double.toString(4));
        //计算出电费的BigDecimal类型
        BigDecimal waterResult = (a2.subtract(a1)).multiply(percent1);

        BigDecimal priceResult = new BigDecimal(price.toString());
        return electricResult.add(waterResult).add(priceResult).doubleValue();
    }
}
