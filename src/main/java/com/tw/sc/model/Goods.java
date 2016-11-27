package com.tw.sc.model;

public abstract class Goods {
    private String name;
    private double price;
    private Discount discount;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract double countDiscountedPrice();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    public enum Category {
        ELEC
    }
}
