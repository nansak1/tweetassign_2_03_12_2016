package tweetassign_01

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@TestFor(Account)
@TestMixin(DomainClassUnitTestMixin)
class AccountSpec extends Specification {

    def 'A1. Saving an account with a valid handle, email, password and name will succeed (unit test)'() {

        given:
        def accountsBefore = Account.count()
        def aUser = new Account(fullName: 'Nayna Nayate', emailAddress: 'nayat002@umn.edu', accountHandle: 'nayna', accountPassword: 'S0m3Word')

        when:
        aUser.save()

        then:
        aUser.id
        !aUser.hasErrors();
        Account.count() == accountsBefore+1
    }

    def 'A2. Saving an account missing any of the required values of handle email, password and name will fail (data-driven unit test): #description'() {

        given:
        def accountsBefore = Account.count()
        def aUser = new Account(fullName: fullName, emailAddress: emailAddress, accountHandle: accountHandle, accountPassword: accountPassword)

        when:
        aUser.save()

        then:
        !aUser.id
        aUser.hasErrors()
        accountsBefore == Account.count()

        where:
        description                | fullName      | emailAddress      | accountHandle | accountPassword
        "emailAddress missing"    | 'nayna n'     | ''                | 'b15'         | 'h3Lloworld'
        "accountHandle missing"   | 'Walt Disney' | 'wd@disney.world' | ''            | 'waltd1sNey'
        "accountPassword missing" | 'Walt Disney' | 'wd@disney.world' | 'walt'        | ''
        "fullName missing"        | ''            | 'wd@disney.world' | 'waltd'       | 'waltd1sNey'
    }

    def "A3. Saving an account with an invalid password will fail. Passwords must be 8-16 characters and have at least 1 number, at least one lower-case letter, at least 1 upper-case letter (data-driven unit test)"() {
        setup:
        def accountsBefore = Account.count()
        def cuser = [accountHandle: 'walterauma', fullName: 'Walter Auma', emailAddress: 'walterauma@umn.edu', accountPassword: accPass]
        def user = new Account(cuser)

        when: "When an attempt is made to save user information"

        user.save()

        then:
        user.hasErrors()
        user.errors.getFieldError('accountPassword')
        accountsBefore == Account.count()

        where: "Different typed of passwords"

        descrip                                 | accPass
        "blank password"                        | ''
        "null password"                         | null
        "uppercase password no digits"          | 'MSSEASSIGNMENT'
        "lowercase password no digits"          | 'msseassignment'
        "mixed case password no digits"         | 'msseASSIGNMENT'
        "digits only password"                  | '1234562342342'
        "digits with only upper case"           | '12345623MSSE'
        "digits with only lower case"           | '12345623msse'
        "password with 7 characters"            | 'msSSE12'
        "password with 17 characters"           | '9msse2016A23dgIGN'
    }

    def 'account saves with valid password: #descrip'() {
        setup:
        def accountsBefore = Account.count()
        def cuser = [accountHandle: 'walterauma', fullName: 'Walter Auma', emailAddress: 'walterauma@umn.edu', accountPassword: accPass]
        def user = new Account(cuser)

        when: "When an attempt is made to save user information"
        user.save()

        then:
        !user.hasErrors()
        Account.count() == accountsBefore + 1

        where: "Different typed of passwords"

        descrip                                 | accPass
        "mixed case with digits password"       | 'msse2016ASSIGN'
        "password word with special characters" | "msse2016A@#_gIGN"
        "password word with spaces characters"  | "msse216 Ass 01"
    }

}
