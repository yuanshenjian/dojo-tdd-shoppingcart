package com.tw.sc;

import com.tw.sc.factory.GoodsFactory;
import com.tw.sc.model.Coupon;
import com.tw.sc.model.Discount;
import com.tw.sc.model.Goods;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

        assertEquals(2699.0, shoppingCar.calculateAllItemsPrice(), 0);
    }

    @Test
    public void should_calculate_final_price_when_coupon_deadline_not_set() {
        double value = 100.0;
        double minConsumption = 300.0;
        Coupon coupon = new Coupon(value, minConsumption);

        ShoppingCar shoppingCarSpy = spy(shoppingCar);
        shoppingCarSpy.setCoupon(coupon);
        when(shoppingCarSpy.calculateAllItemsPrice()).thenReturn(500.0);

        assertEquals(400, shoppingCarSpy.tally(), 0);
        verify(shoppingCarSpy).calculateAllItemsPrice();
    }
}