package com.tw.sc.model;

import com.tw.sc.factory.GoodsFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoodsTest {

    @Test
    public void should_count_elec_discounted_price() {
        Discount discount = new Discount(Goods.Category.ELEC);
        Goods goods = GoodsFactory.create(Goods.Category.ELEC, "ipad", 10.2, discount);

        double result = goods.countDiscountedPrice();
        assertEquals("result is not equals 10.2", result, 10.2, 0);
    }
}