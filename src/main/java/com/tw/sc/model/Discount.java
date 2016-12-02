package com.tw.sc.model;

import com.tw.sc.exception.InvalidDiscountRateException;

import java.time.LocalDate;

public class Discount {
    private Goods.Category category;
    private double rate = 1.0;
    private LocalDate deadline;

    public Discount(Goods.Category category) {
        this.category = category;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        if (rate <= 0 || rate > 1) {
            throw new InvalidDiscountRateException("Discount rate should between 0 and 1(include)");
        }
        this.rate = rate;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Goods.Category getCategory() {
        return category;
    }

    public void setCategory(Goods.Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "rate=" + rate +
                '}';
    }

    public boolean available() {
        if (deadline == null){
            return true;
        }
        LocalDate now = LocalDate.now();
        return deadline.isAfter(now) || deadline.isEqual(now);
    }
}
