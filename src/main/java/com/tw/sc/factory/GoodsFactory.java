package com.tw.sc.factory;

import com.tw.sc.exception.InvalidGoodsCatgoryException;
import com.tw.sc.model.*;

public class GoodsFactory {
    public static Goods create(Goods.Category category, String name, double price) {
        switch (category) {
            case ELECTRON:
                return new Electron(name, price);
            case FOOD:
                return new Food(name, price);
            case COMMODITY:
                return new Commodity(name, price);
            case ALCOHOL:
                return new Alcohol(name, price);
        }
        throw new InvalidGoodsCatgoryException();
    }
}
