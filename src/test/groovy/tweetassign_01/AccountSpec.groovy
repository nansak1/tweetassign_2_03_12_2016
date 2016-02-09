package tweetassign_01

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Account)
@TestMixin(DomainClassUnitTestMixin)
class AccountSpec extends Specification {

    def setup() {

    }

    def "A3: Test saving an account with invalid password"() {
        setup:

        def cuser = ["accountHandle": 'walterauma', "fullName": 'Walter Auma', "emailAddress": 'walterauma@umn.edu', "accountPassword": accPass]
        def user = new Account(cuser)

        when: "When an attempt is made to save user information"

        user.save()

        then:

        !user.hasErrors() == isSaved

        where: "Different typed of passwords"

        descrip                                 | accPass             | isSaved
        "blank password"                        | ''                  | false
        "null password"                         | null                | false
        "uppercase password no digits"          | 'MSSEASSIGNMENT'    | false
        "lowercase password no digits"          | 'msseassignment'    | false
        "mixed case password no digits"         | 'msseASSIGNMENT'    | false
        "digits only password"                  | '1234562342342'     | false
        "mixed case with digits password"       | 'msse2016ASSIGN'    | true
        "password with 7 characters"            | 'msSSE12'           | false
        "password with 17 characters"           | '9msse2016A23dgIGN' | false
        "password word with special characters" | "msse2016A@#_gIGN"  | true
        "password word with spaces characters"  | "msse216 Ass 01"    | true

    }



    def cleanup() {

    }


}
