package com.ymens.spring.interfaces;

import com.ymens.spring.beans.OrderItem;

public interface IOrderItem {
    int setOrderItem(OrderItem orderItem);
    int getPrice(int id);
    int getIdOrder(int id);
    int getIdBook(int id);
}
