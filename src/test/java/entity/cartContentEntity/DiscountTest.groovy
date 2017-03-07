package entity.cartContentEntity

import constant.ProductType
import spock.lang.Specification

class DiscountTest extends Specification {


    def "give a product when product is 2013.11.11|0.7| 电子 etc. then return is what type they belong"() {
        def discount = new Discount(a)
        expect:
        discount.getDiscountType() == b
        where:
        a                    || b
        "2013.11.11|0.7| 电子" || ProductType.ELECTRON
        "2013.11.11|0.7| 日用品" || ProductType.COMMODITY
        null                 || null
    }
}