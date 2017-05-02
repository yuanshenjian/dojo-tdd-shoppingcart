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
import java.util.function.Predicate;

public class Cart {

    private List<Product> products = new ArrayList<>();
    private List<Coupon> coupons = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();
    private BigDecimal totalAmount = new BigDecimal(0);
    private Date balanceDate;
    private final BigDecimal defaultDiscount = new BigDecimal(1);
    private final Predicate<Coupon> is_coupon = c->c.getEffectiveDate().getTime() >=
            this.balanceDate.getTime() && totalAmount.compareTo(c.getQuota()) >= 0;
    private Predicate<String> couponTest = i->InputType.COUPON.equals(Input.getInputType(i));
    private Predicate<String> discountTest = i->InputType.DISCOUNT.equals(Input.getInputType(i));
    private Predicate<String> productTest = i->InputType.PRODUCT.equals(Input.getInputType(i));

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
        products.stream().forEach(p->totalAmount = totalAmount.add(p.getTotalPrice().multiply(getDiscount(p))));

    }

    private BigDecimal getDiscount(Product p) {
       return discounts
               .stream()
               .filter(d->is_Effective_Discount(p,d))
               .findAny()
               .map(s->defaultDiscount.multiply(s.getDiscount()))
               .orElse(defaultDiscount);
    }

    private boolean is_Effective_Discount(Product p, Discount d) {
        return d.getDiscountType().equals(p.getDiscountType())
                && d.getEffectiveDate().getTime() >= this.balanceDate.getTime();
    }

    private void getAmount() {
        coupons.stream()
                .filter(is_coupon)
                .forEach(c->totalAmount= totalAmount.subtract(c.getAbatement()));
    }


    private Cart addCartContent(String input) throws ParseException {
        Action action = getAction(input);
        if (input == null) return null;
        if (couponTest.test(input)) {
            action.addItem(input);
        }
        if (discountTest.test(input))
            action.addItem(input);

        if (productTest.test(input))
            action.addItem(input);
        return this;
    }

    private Action getAction(String input) {

        Action action = null;
        if (couponTest.test(input)) {
            action = (i)->{
                if (coupons.size() == 0) {//only one coupon can be used
                    coupons.add((Coupon) CartContentFactory.createCartContent(i));
                }};
        }
        if (discountTest.test(input))
            action = (i)->discounts.add((Discount) CartContentFactory.createCartContent(i));

        if (productTest.test(input))
            action=(i)->products.add((Product) CartContentFactory.createCartContent(i));
        return action;
    }

    private Cart addBalanceDate(String p) throws ParseException {
        if (InputType.BALANCE_DATE.equals(Input.getInputType(p))) {
            this.balanceDate = CommonTools.formatDate(p);
        }
        return this;
    }

}

interface Action{
    void addItem(String input) throws ParseException;
}