package entity.cartContentEntity;

import common.CommonTools;
import constant.ProductType;

import java.math.BigDecimal;

public class Product extends CartContent {
    private double count;
    private double price;
    private Enum productType;

    //input format is  "3 * 蔬 菜 : 5.98 "
    public Product(String input) {
        if (input == null) return;
        input = input.replace(" ", "").replace("*", ":");
        String[] foodInfo = input.split(":");
        productType = ProductType.getProductType(foodInfo[1]);
        count = Double.valueOf(foodInfo[0]);
        price = Double.valueOf(foodInfo[2]);
    }

    public BigDecimal getTotalPrice() {
        return CommonTools.setScale(new BigDecimal(price * count));
    }

    public Enum getDiscountType() {
        return productType;
    }

}
