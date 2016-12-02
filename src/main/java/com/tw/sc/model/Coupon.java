package com.tw.sc.model;

public class Coupon {
    private double value;
    private double minConsumption;

    public Coupon(double value, double minConsumption) {
        this.value = value;
        this.minConsumption = minConsumption;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMinConsumption() {
        return minConsumption;
    }

    public void setMinConsumption(double minConsumption) {
        this.minConsumption = minConsumption;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "value=" + value +
                ", minConsumption=" + minConsumption +
                '}';
    }
}
