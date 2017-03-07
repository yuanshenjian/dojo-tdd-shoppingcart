package entity.cartContentEntity;

import common.CommonTools;
import constant.ProductType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public class Discount extends CartContent {
    private Date effectiveDate;
    private BigDecimal discount = new BigDecimal(0);
    private Enum productType;

    //input format is  2013.11.11|0.7| 电子
    public Discount(String input) throws ParseException {
        if (input == null) return;
        input = input.replace(" ", "");
        String[] info = input.split("\\|");
        effectiveDate = CommonTools.formatDate(info[0]);
        discount = new BigDecimal(info[1]);
        productType = ProductType.getProductTypeByName(info[2]);
    }

    public Enum getDiscountType() {
        return productType;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
