package com.tw.sc.model;

import java.time.LocalDate;

public class Coupon {
    private double value;
    private double minConsumption;
    private LocalDate deadline;

    public Coupon(double value, double minConsumption) {
        this.value = value;
        this.minConsumption = minConsumption;
    }

    public double getValue() {
        return value;
    }

    public double getMinConsumption() {
        return minConsumption;
    }

    public boolean available() {
        if (deadline == null){
            return true;
        }
        LocalDate now = LocalDate.now();
        return deadline.isAfter(now) || deadline.equals(now);
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
