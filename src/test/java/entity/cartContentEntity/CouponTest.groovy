package entity.cartContentEntity

import spock.lang.Specification

class CouponTest extends Specification {


    def "give a product when product is 2014.3.2 1000 200  etc. then return is 200"() {
        def coupon = new Coupon(a)
        expect:
        coupon.getAbatement() == b
        where:
        a                    || b
        "2014.3.2 1000 200 " || 200
        null                 || 0
    }

    def "give a product when product is 2014.3.2 1000 200  etc. then return is 1000"() {
        def coupon = new Coupon(a)
        expect:
        coupon.getQuota() == b
        where:
        a                    || b
        "2014.3.2 1000 200 " || 1000
        null                 || 0
    }

}