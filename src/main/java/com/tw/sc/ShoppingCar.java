package com.tw.sc;

import com.tw.sc.model.Coupon;
import com.tw.sc.model.Discount;
import com.tw.sc.model.Goods;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCar {
    private final static ShoppingCar INSTANCE = new ShoppingCar();
    private static final Map<Goods, Integer> ORDER_LIST = new HashMap<Goods, Integer>();
    private Coupon coupon;
    private Discount discount;

    private ShoppingCar() {

    }

    public static ShoppingCar getInstance() {
        return INSTANCE;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public double tally() {
        double result = 0.0;
        clear();
        return result;
    }

    public void addGoods(Goods goods) {
        addGoods(goods, 1);
    }

    public void addGoods(Goods goods, int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Goods number must larger than 0");
        }
        int currentNumber = ORDER_LIST.containsKey(goods) ? ORDER_LIST.get(goods) + number : number;
        ORDER_LIST.put(goods, currentNumber);
    }

    public int sumItemsByGoods(Goods goods) {
        return ORDER_LIST.get(goods) == null ? 0 : ORDER_LIST.get(goods);
    }

    public int sumAllItems() {
        int sum = 0;
        for (Map.Entry<Goods, Integer> goodsIntegerEntry : ORDER_LIST.entrySet()) {
            sum += goodsIntegerEntry.getValue();
        }
        return sum;
    }

    public void clear() {
        ORDER_LIST.clear();
    }

    public double calculateAllItemsPrice() {
        double sum = 0.0;
        for (Map.Entry<Goods, Integer> goodsEntry : ORDER_LIST.entrySet()) {
            Goods goods = goodsEntry.getKey();
            goods.setDiscount(discount);
            sum += goods.calculateDiscountedPrice() * goodsEntry.getValue();
        }
        return sum;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
