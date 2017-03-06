package common

import spock.lang.Specification

import java.text.ParseException

class CommonToolsTest extends Specification {

    def "when give a number  then return is half adjusting"() {
        def commonTools = new CommonTools()

        expect:
        commonTools.setScale(a) == b

        where:
        a           || b
        111231.5585 || 111231.56
        111231.534  || 111231.53
        111         || 111.00
    }

    def "when give a date  then return is format with exception"() {
        def commonTools = new CommonTools()

        when:
        commonTools.formatDate(a) == b

        then:
        thrown(ParseException)

        where:
        a            || b
        "2013-11-11" || null

    }
}