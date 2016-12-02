package com.tw.sc.model;

import com.tw.sc.factory.GoodsFactory;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class GoodsTest {
    @Test
    public void should_calculate_goods_original_price_when_discount_not_exist() {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 10.2);
        double result = goods.calculateDiscountedPrice();
        assertEquals(result, 10.2, 0);
    }

    @Test
    public void should_calculate_goods_discounted_price_when_half_discount() {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 10.2);
        Discount discount = new Discount(Goods.Category.ELECTRON);
        discount.setRate(0.5);
        goods.setDiscount(discount);
        double result = goods.calculateDiscountedPrice();
        assertEquals(result, 5.1, 0);

    }

    @Test
    public void should_calculate_goods_original_price_when_discount_out_of_date() {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 10.2);
        Discount discount = new Discount(Goods.Category.ELECTRON);
        LocalDate now = LocalDate.now();
        LocalDate deadline = now.minusDays(4);
        discount.setDeadline(deadline);
        discount.setRate(0.5);
        goods.setDiscount(discount);
        double result = goods.calculateDiscountedPrice();
        assertEquals(result, 10.2, 0);
    }

    @Test
    public void should_calculate_goods_original_price_when_discount_is_note_suitable() {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 10.2);
        Discount discount = new Discount(Goods.Category.FOOD);
        discount.setRate(0.5);
        goods.setDiscount(discount);
        double result = goods.calculateDiscountedPrice();
        assertEquals(result, 10.2, 0);
    }
}