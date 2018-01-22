package com.ymens.spring.beans;

public class OrderItem {

        private int id;
        private Double price;
        private int book_id;
        private int order_id;
    public OrderItem(){};
    public OrderItem(Double price, int book_id, int order_id) {
        this.price = price;
        this.book_id = book_id;
        this.order_id = order_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
}
