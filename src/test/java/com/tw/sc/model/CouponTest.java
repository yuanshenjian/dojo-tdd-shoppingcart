package com.tw.sc.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CouponTest {
    @Test
    public void should_be_available_when_deadline_not_set() {
        Coupon coupon = new Coupon(100, 300);
        assertTrue(coupon.available());
    }

    @Test
    public void should_be_available_when_before_deadline() {
        Coupon coupon = new Coupon(100, 300);
        LocalDate deadline = LocalDate.now().plusDays(2);
        coupon.setDeadline(deadline);
        assertTrue(coupon.available());
    }

    @Test
    public void should_be_available_when_in_deadline() {
        Coupon coupon = new Coupon(100, 300);
        LocalDate deadline = LocalDate.now();
        coupon.setDeadline(deadline);
        assertTrue(coupon.available());
    }

    @Test
    public void should_be_unavailable_when_out_of_date() {
        Coupon coupon = new Coupon(100, 300);
        LocalDate deadline = LocalDate.now().minusDays(2);
        coupon.setDeadline(deadline);
        assertFalse(coupon.available());
    }
}