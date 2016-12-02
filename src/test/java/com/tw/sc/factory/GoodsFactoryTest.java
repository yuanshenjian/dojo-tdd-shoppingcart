package com.tw.sc.factory;

import com.tw.sc.exception.InvalidGoodsCatgoryException;
import com.tw.sc.model.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


public class GoodsFactoryTest {
    @Test
    public void should_create_electron_goods() throws Exception {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "", 1.0);
        assertThat(goods, isA(Goods.class));
        assertTrue(goods instanceof Electron);
    }

    @Test
    public void should_create_food_goods() throws Exception {
        Goods goods = GoodsFactory.create(Goods.Category.FOOD, "", 1.0);
        assertThat(goods, isA(Goods.class));
        assertTrue(goods instanceof Food);
    }

    @Test
    public void should_create_commodity_goods() throws Exception {
        Goods goods = GoodsFactory.create(Goods.Category.COMMODITY, "", 1.0);
        assertThat(goods, isA(Goods.class));
        assertTrue(goods instanceof Commodity);
    }

    @Test
    public void should_create_alcohol_goods() throws Exception {
        Goods goods = GoodsFactory.create(Goods.Category.ALCOHOL, "", 1.0);
        assertThat(goods, isA(Goods.class));
        assertTrue(goods instanceof Alcohol);
    }

    @Test(expected = InvalidGoodsCatgoryException.class)
    public void should_throw_exception_when_goods_category_invalid() throws Exception {
        GoodsFactory.create(Goods.Category.UNKNOW, "", 1.0);
    }
}