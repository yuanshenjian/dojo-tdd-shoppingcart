package entity

import constant.InputType
import spock.lang.Specification

class InputTest extends Specification {

    def "give a input when input is 2013.11.11|0.7| 电子 etc. then return is what the input type they belong"() {
        def input = new Input()

        expect:
        input.getInputType(a) == b

        where:
        a                    || b
        "2013.11.11|0.7| 电子" || InputType.DISCOUNT
        "2013.11.11"         || InputType.BALANCE_DATE
        "2014.3.2 1000 200 " || InputType.COUPON
        "1* 显示器:1799.00"     || InputType.PRODUCT
        null                 || null
        ""                   || null
        " "                  || null
    }

    def "give a input text when input is caseB  then return is info size"() {
        def input = new Input()

        expect:
        input.loadInfo(a).size() == b

        where:
        a           || b
        "caseBData" || 3

    }

}