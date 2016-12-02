package com.tw.sc.model;

public class Commodity extends Goods {
    public Commodity(String name, double price) {
        super(Category.COMMODITY, name, price);
    }
}
