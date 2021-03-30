package com.future.message.bean;

import java.util.Objects;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/2/25 11:42
 * Description: 水果
 */
public class FruitBean implements Comparable{
    private String color = "RED";
    private int num = 2;

    public FruitBean(String color, int num) {
        this.color = color;
        this.num = num;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FruitBean fruitBean = (FruitBean) o;
        return num == fruitBean.num &&
                Objects.equals(color, fruitBean.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, num);
    }

    @Override
    public String toString() {
        return "FruitBean{" +
                "color='" + color + '\'' +
                ", num=" + num +
                '}';
    }
}
