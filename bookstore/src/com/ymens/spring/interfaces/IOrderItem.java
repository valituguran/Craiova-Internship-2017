package com.ymens.spring.interfaces;

import com.ymens.spring.beans.OrderItem;

import java.util.List;

public interface IOrderItem {
    int setOrderItem(OrderItem orderItem);
    int getPrice(int id);
    int getIdOrder(int id);
    int getIdBook(int id);

    List<OrderItem> getOrderItems(String string);
}
