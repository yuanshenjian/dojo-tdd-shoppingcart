package com.tw.sc.factory;

import com.tw.sc.model.Discount;
import com.tw.sc.model.Elec;
import com.tw.sc.model.Goods;

public class GoodsFactory {
    public static Goods create(Goods.Category category, String name, double price, Discount discount) {
        Goods goods = null;
        switch (category) {
            case ELEC:
                goods = new Elec(name, price);

        }
        goods.setDiscount(discount);
        return goods;
    }
}
