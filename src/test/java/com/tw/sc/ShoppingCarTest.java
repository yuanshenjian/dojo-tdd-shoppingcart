package com.tw.sc;

import com.tw.sc.factory.GoodsFactory;
import com.tw.sc.model.Coupon;
import com.tw.sc.model.Discount;
import com.tw.sc.model.Goods;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingCarTest {
    private static ShoppingCar shoppingCar;

    @BeforeClass
    public static void setup() {
        shoppingCar = ShoppingCar.getInstance();

    }

    @After
    public void tearDown() {
        shoppingCar.clear();
    }


    @Test
    public void should_add_goods_to_shopping_car() {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 2399.0);
        shoppingCar.addGoods(goods);
        assertEquals(shoppingCar.sumItemsByGoods(goods), 1);

        goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 2399.0);
        shoppingCar.addGoods(goods, 5);
        assertEquals(shoppingCar.sumItemsByGoods(goods), 6);

        goods = GoodsFactory.create(Goods.Category.FOOD, "apple", 10.0);
        shoppingCar.addGoods(goods, 2);
        assertEquals(shoppingCar.sumItemsByGoods(goods), 2);

        assertEquals(shoppingCar.sumAllItems(), 8);
    }

    @Test
    public void should_calculate_all_items_price_with_discount() {
        Discount discount = new Discount(Goods.Category.ELECTRON);
        discount.setRate(0.5);
        shoppingCar.setDiscount(discount);

        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 2399.0);
        shoppingCar.addGoods(goods, 2);

        goods = GoodsFactory.create(Goods.Category.FOOD, "apple", 10.0);
        shoppingCar.addGoods(goods, 10);

        goods = GoodsFactory.create(Goods.Category.ALCOHOL, "beer", 15.0);
        shoppingCar.addGoods(goods, 10);

        goods = GoodsFactory.create(Goods.Category.COMMODITY, "toothset", 5.0);
        shoppingCar.addGoods(goods, 10);

        assertEquals(shoppingCar.calculateAllItemsPrice(), 2699.0, 0);
    }


    @Ignore
    @Test
    public void should_calculate_final_price_when_coupon_deadline_not_set() {
        Goods goods = GoodsFactory.create(Goods.Category.ELECTRON, "ipad", 2399.0);
        shoppingCar.addGoods(goods);
        double value = 100.0;
        double minConsumption = 300.0;
//        when(shoppingCar.calculateAllItemsPrice()).thenReturn(500);
        Coupon coupon = new Coupon(value, minConsumption);
        shoppingCar.setCoupon(coupon);
        assertEquals(400, shoppingCar.tally(), 0);
    }
}