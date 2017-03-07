package entity.cartContentEntity

import spock.lang.Specification
import spock.lang.Unroll

class ProductTest extends Specification {

    @Unroll
    "give a product when product is 3 * 蔬 菜 : 5.98 etc. then return is amount"() {
        def product = new Product(a)
        expect:
        product.getTotalPrice() == b
        where:
        a                   || b
        "3 * 蔬 菜 : 5.98 "  || 17.94
        "8* 餐巾纸:3.20"     || 25.60
        "1* ipad:2399.00"   || 2399.00
        "1* 显示器:1799.00"  || 1799.00
        "12* 啤酒:25.00"    || 300
        "5 * 面 包 : 9.00"  || 45.00
        null               || 0
    }
}