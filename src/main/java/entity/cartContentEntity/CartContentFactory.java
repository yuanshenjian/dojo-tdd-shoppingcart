package entity.cartContentEntity;

import constant.InputType;
import entity.Input;

import java.text.ParseException;

public class CartContentFactory {

    public static CartContent createCartContent(String input) throws ParseException {
        if (input == null) return null;
        if (InputType.COUPON.equals(Input.getInputType(input))) return new Coupon(input);
        if (InputType.DISCOUNT.equals(Input.getInputType(input))) return new Discount(input);
        if (InputType.PRODUCT.equals(Input.getInputType(input))) return new Product(input);
        return null;
    }
}
