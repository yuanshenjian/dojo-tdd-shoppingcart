package com.tw.sc.model;

import com.tw.sc.exception.InvalidDiscountRateException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DiscountTest {

    @Test(expected = InvalidDiscountRateException.class)
    public void should_throw_exception_when_discount_equals_zero() {
        Discount discount = new Discount(Goods.Category.ELECTRON);
        discount.setRate(0);
    }

    @Test(expected = InvalidDiscountRateException.class)
    public void should_throw_exception_when_discount_surpass_one() {
        Discount discount = new Discount(Goods.Category.ELECTRON);
        discount.setRate(1.1);
    }

    @Test
    public void should_be_unavailable_when_discount_out_of_date(){
        Discount discount = new Discount(Goods.Category.ELECTRON);
        LocalDate deadline = LocalDate.now().minusDays(4);
        discount.setDeadline(deadline);
        assertFalse(discount.available());
    }

    @Test
    public void should_be_available_when_discount_before_deadline(){
        Discount discount = new Discount(Goods.Category.ELECTRON);
        LocalDate deadline = LocalDate.now().plusDays(2);
        discount.setDeadline(deadline);
        assertTrue(discount.available());
    }

    @Test
    public void should_be_available_when_discount_in_deadline(){
        Discount discount = new Discount(Goods.Category.ELECTRON);
        LocalDate deadline = LocalDate.now();
        discount.setDeadline(deadline);
        assertTrue(discount.available());
    }

    @Test
    public void should_be_available_when_discount_deadline_not_set(){
        Discount discount = new Discount(Goods.Category.ELECTRON);
        assertTrue(discount.available());
    }
}