package constant

import spock.lang.Specification

class ProductTypeTest extends Specification {
    def "give a input when input is product items then return is the type what they belong"() {
        expect:
        ProductType.getProductType(a) == b
        where:
          a           || b
        "显示器"       || ProductType.ELECTRON
        "ipad"        || ProductType.ELECTRON
        "笔记本电脑"   || ProductType.ELECTRON
        "面包"         || ProductType.FOOD
        "餐巾纸"       || ProductType.COMMODITY
        "白酒"         || ProductType.LIQUOR
        null          || null
    }

    def "give a input when input is category then return is product they belong"() {
        expect:
        ProductType.getProductTypeByName(a) == b
        where:
         a      || b
        "电子"   || ProductType.ELECTRON
        "食品"   || ProductType.FOOD
        "日用品" || ProductType.COMMODITY
        "酒类"   || ProductType.LIQUOR
         null    || null
    }


}
