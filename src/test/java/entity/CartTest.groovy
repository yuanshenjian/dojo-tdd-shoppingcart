package entity

import spock.lang.Specification

class CartTest extends Specification {

    def "give a list product when the list have no discount and coupon then return is 43.54"() {
        given:
        def input = new Input()
        def info = input.loadInfo(a)
        def cart = new Cart(info)

        expect:
        cart.getTotalAmount() == b

        where:
        a           || b
        "caseBData" || 43.54
    }

    def "give a list product when the list have effective discount and coupon then return is 3083.60"() {
        given:
        def input = new Input()
        def info = input.loadInfo(a)
        def cart = new Cart(info)

        expect:
        cart.getTotalAmount() == b

        where:
        a           || b
        "caseAData" || 3083.60
    }

    def "give a list product when the list have effective discount but no effective coupon then return is 3283.60"() {
        given:
        def input = new Input()
        def info = input.loadInfo(a)
        def cart = new Cart(info)

        expect:
        cart.getTotalAmount() == b

        where:
        a           || b
        "caseCData" || 3283.60
    }

    def "give a list product when the list have no effective discount but  effective coupon then return is 4343.00"() {
        given:
        def input = new Input()
        def info = input.loadInfo(a)
        def cart = new Cart(info)

        expect:
        cart.getTotalAmount() == b

        where:
        a           || b
        "caseDData" || 4343.00
    }

    def "give a list product when the list have effective discount and two effective coupon then return is 3083.60"() {
        given:
        def input = new Input()
        def info = input.loadInfo(a)
        def cart = new Cart(info)

        expect:
        cart.getTotalAmount() == b

        where:
        a           || b
        "caseEData" || 3083.60
    }

}