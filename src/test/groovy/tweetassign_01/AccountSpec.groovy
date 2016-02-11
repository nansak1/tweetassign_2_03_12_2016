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

    //A1: Saving an account with a valid handle, email, password and name will succeed

    def 'A1. Saving an account with a valid handle, email, password and name will succeed (unit test)'() {

        given:
        def aUser = new Account("fullName": 'Nayna Nayate', "emailAddress": 'nayat002@umn.edu', "accountHandle": 'nayna', "accountPassword": 'S0m3Word')

        when:
        aUser.save()

        then:
        aUser.id
        !aUser.hasErrors();

    }

    def 'A2. Saving an account missing any of the required values of handle email, password and name will fail (data-driven unit test)'() {

        given:
        def aUser = new Account("fullName": fullName, "emailAddress": emailAddress, "accountHandle": accountHandle, "accountPassword": accountPassword)

        when:
        aUser.save()

        then:
        //aUser.id
        !aUser.hasErrors() == saveAcc

        where:

        decription                | fullName      | emailAddress      | accountHandle | accountPassword | saveAcc
        "emailAddress missing"    | 'nayna n'     | ''                | 'b15'         | 'h3Lloworld'    | false
        "no missing info"         | 'nayna n'     | 'b15@yahoo.com'   | 'b15'         | 'h3Lloworld'    | true
        "accountHandle missing"   | 'Walt Disney' | 'wd@disney.world' | ''            | 'waltd1sNey'    | false
        "accountPassword missing" | 'Walt Disney' | 'wd@disney.world' | 'walt'        | ''              | false
        "fullName missing"        | ''            | 'wd@disney.world' | 'waltd'       | 'waltd1sNey'    | false


    }

    def "A3. Saving an account with an invalid password will fail. Passwords must be 8-16 characters and have at least 1 number, at least one lower-case letter, at least 1 upper-case letter (data-driven unit test)"() {
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
        "digits with only upper case"           | '12345623MSSE'      | false
        "digits with only lower case"           | '12345623msse'      | false
        "mixed case with digits password"       | 'msse2016ASSIGN'    | true
        "password with 7 characters"            | 'msSSE12'           | false
        "password with 17 characters"           | '9msse2016A23dgIGN' | false
        "password word with special characters" | "msse2016A@#_gIGN"  | true
        "password word with spaces characters"  | "msse216 Ass 01"    | true

    }


    def cleanup() {

    }


}
