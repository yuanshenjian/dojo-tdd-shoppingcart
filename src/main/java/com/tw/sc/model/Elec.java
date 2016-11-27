package com.tw.sc.model;

public class Elec extends Goods {

    public Elec(String name, double price) {
        super(name, price);
    }

    public double countDiscountedPrice() {
        return getPrice() * getDiscount().getRate();
    }
}
