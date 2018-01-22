package com.ymens.spring.beans;

public class Order {
    private int id;
    private Double totalPrice;
    private Integer userId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
