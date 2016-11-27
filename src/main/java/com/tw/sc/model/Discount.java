package com.tw.sc.model;

public class Discount {
    private Goods.Category category;
    private double rate = 1.0;

    public Discount(Goods.Category category) {
        this.category = category;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


}
