package com.tw.sc.factory;

import com.tw.sc.exception.InvalidGoodsCategoryException;
import com.tw.sc.model.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class GoodsFactoryTest {
    @Test
    public void should_create_electron_goods() {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "", 1.0);
        assertThat(goods, is(Goods.class));
        assertTrue(goods instanceof Electron);
    }

    @Test
    public void should_create_food_goods() {
        Goods goods = GoodsFactory.create(Goods.Category.FOOD, "", 1.0);
        assertThat(goods, is(Goods.class));
        assertTrue(goods instanceof Food);
    }

    @Test
    public void should_create_commodity_goods() {
        Goods goods = GoodsFactory.create(Goods.Category.COMMODITY, "", 1.0);
        assertThat(goods, is(Goods.class));
        assertTrue(goods instanceof Commodity);
    }

    @Test
    public void should_create_alcohol_goods() {
        Goods goods = GoodsFactory.create(Goods.Category.ALCOHOL, "", 1.0);
        assertThat(goods, is(Goods.class));
        assertTrue(goods instanceof Alcohol);
    }

    @Test(expected = InvalidGoodsCategoryException.class)
    public void should_throw_exception_when_goods_category_invalid() {
        GoodsFactory.create(Goods.Category.UNKNOWN, "", 1.0);
    }
}