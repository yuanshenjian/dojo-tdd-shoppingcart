package com.tw.sc.model;

public abstract class Goods {
    public enum Category {
        FOOD, COMMODITY, ALCOHOL, ELECTRON, UNKNOW
    }

    private String name;
    private double price;
    private Discount discount;
    private Category category;

    public Goods(Category category, String name, double price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }


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

    public double calculateDiscountedPrice() {
        Discount discount = getDiscount();
        boolean hasAvailableDiscount = (discount != null && discount.available() && discount.getCategory() == category);
        if (hasAvailableDiscount) {
            return getPrice() * discount.getRate();
        }
        return getPrice() * 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        ;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Goods goods = (Goods) o;
        return name.equals(goods.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
