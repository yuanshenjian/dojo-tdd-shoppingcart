package com.tw.sc;

import com.tw.sc.model.Coupon;
import com.tw.sc.model.Discount;
import com.tw.sc.model.Goods;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCar {
    private final static ShoppingCar INSTANCE = new ShoppingCar();
    private static final Map<Goods, Integer> ORDER_LIST = new HashMap<>();
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

    public double tally() {
        double result = calculateAllItemsPrice();
        boolean isCouponAvailable = (coupon != null
                && coupon.available()
                && result >= coupon.getMinConsumption());
        if (isCouponAvailable) {
            result = result - coupon.getValue();
        }
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
        return ORDER_LIST.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
    }

    public void clear() {
        ORDER_LIST.clear();
    }

    public double calculateAllItemsPrice() {
        return ORDER_LIST.entrySet().stream().mapToDouble(goodsEntry -> {
            Goods goods = goodsEntry.getKey();
            goods.setDiscount(discount);
            return goods.calculateDiscountedPrice() * goodsEntry.getValue();
        }).sum();
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
