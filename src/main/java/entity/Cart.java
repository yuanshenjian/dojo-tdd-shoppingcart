package entity;

import common.CommonTools;
import constant.InputType;
import entity.cartContentEntity.CartContentFactory;
import entity.cartContentEntity.Coupon;
import entity.cartContentEntity.Discount;
import entity.cartContentEntity.Product;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {

    private List<Product> products = new ArrayList<>();
    private List<Coupon> coupons = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();
    private BigDecimal totalAmount = new BigDecimal(0);
    private Date balanceDate;
    private final BigDecimal defaultDiscount = new BigDecimal(1);

    public Cart(List<String> info) throws ParseException {
        for (String i : info) {
            addCartContent(i).addBalanceDate(i);
        }
    }

    public BigDecimal getTotalAmount() {
        getDiscountedAmount();
        getAmount();
        return CommonTools.setScale(totalAmount);
    }

    private void getDiscountedAmount() {
        for (Product p : products) {
          //  double discount = getDiscount(p);
            totalAmount = totalAmount.add(p.getTotalPrice().multiply(getDiscount(p)));
            //totalAmount += p.getTotalPrice().doubleValue() * discount;
        }
    }

    private BigDecimal getDiscount(Product p) {
        for (Discount d : discounts) {
            if (is_Effective_Discount(p, d)) {
                return defaultDiscount.multiply(d.getDiscount());
            }
        }
        return defaultDiscount;
    }

    private boolean is_Effective_Discount(Product p, Discount d) {
        return d.getDiscountType().equals(p.getDiscountType())
                && d.getEffectiveDate().getTime() >= this.balanceDate.getTime();
    }

    private void getAmount() {
        for (Coupon c : coupons) {
            if (is_Effective_Coupon(c))
               // totalAmount -= c.getAbatement();
                totalAmount= totalAmount.subtract(c.getAbatement());
        }
    }

    private boolean is_Effective_Coupon(Coupon c) {
        return c.getEffectiveDate().getTime() >= this.balanceDate.getTime() && totalAmount.compareTo(c.getQuota()) >= 0;
    }

    private Cart addCartContent(String input) throws ParseException {
        if (input == null) return null;
        if (InputType.COUPON.equals(Input.getInputType(input))) {
            addCoupon(input);
        }
        if (InputType.DISCOUNT.equals(Input.getInputType(input)))
            addDiscount(input);

        if (InputType.PRODUCT.equals(Input.getInputType(input)))
            addProduct(input);
        return this;
    }

    private void addProduct(String input) throws ParseException {
        products.add((Product) CartContentFactory.createCartContent(input));
    }

    private void addDiscount(String input) throws ParseException {
        discounts.add((Discount) CartContentFactory.createCartContent(input));
    }

    private void addCoupon(String input) throws ParseException {
        if (coupons.size() == 0) {//only one coupon can be used
            coupons.add((Coupon) CartContentFactory.createCartContent(input));
        }
    }

    private Cart addBalanceDate(String p) throws ParseException {
        if (InputType.BALANCE_DATE.equals(Input.getInputType(p))) {
            this.balanceDate = CommonTools.formatDate(p);
        }
        return this;
    }

}
