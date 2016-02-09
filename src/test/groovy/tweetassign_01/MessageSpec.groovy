package tweetassign_01

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Message)
class MessageSpec extends Specification {

    def setup() {

    }

    def "M1. Saving a message with a valid account and message text will succeed (unit test)"() {

        setup:
        def a=["accountHandle": 'nayna2016', "fullName": 'Nayna Natalie', "emailAddress": 'natalien@umn.edu', "accountPassword": 'msse2016ASSIGN']
        def myAcc = new Account(a)
        def m = ["msgText": 'This is the unit test',"acc":myAcc]
        def tweetMsg = new Message(m)
        when:

        tweetMsg.save(failOnError:true)

        then:

        tweetMsg.id
        tweetMsg.errors.errorCount == 0
        tweetMsg.get(tweetMsg.id).msgText == 'This is the unit test'

    }




    def cleanup() {
    }

}
