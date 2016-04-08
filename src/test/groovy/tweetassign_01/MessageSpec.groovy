package tweetassign_01

import grails.test.mixin.TestFor
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

@Ignore
@Unroll
@TestFor(Message)
class MessageSpec extends Specification {

    def "M1: Saving a message with a valid account and message text will succeed (unit test): #description"() {

        setup:
        def beforeCount = Message.count()
        def a = [accountHandle: 'nayna2016', fullName: 'Nayna Natalie', emailAddress: 'natalien@umn.edu', accountPassword: 'msse2016ASSIGN']
        def myAcc = new Account(a)
        def tweetMsg = new Message(msgText: someText, acc: myAcc)

        when:
        tweetMsg.save(failOnError: true)

        then:
        tweetMsg.id
        tweetMsg.errors.errorCount == 0
        tweetMsg.get(tweetMsg.id).msgText == someText as String
        Message.count() == beforeCount + 1

        where:
        description               | someText
        "some letters "           | 'This is the unit test'
        "less than 40 characters" | 'Supercalifragilisticexpialidocious'
        "number"                  | 12345
    }

    def "M2. Message text is required to be non-blank and 40 characters or less (data-driven unit test): #descrip"() {

        given:
        def beforeCount = Message.count()
        def myAcc = new Account(accountHandle: 'naynan')
        def tweet = new Message(acc: myAcc, msgText: someText)

        when:
        tweet.save()

        then:
        !tweet.id
        tweet.hasErrors()
        tweet.errors.getFieldError('msgText')
        Message.count() == beforeCount

        where:
        descrip                   | someText
        "blank text"              | ''
        "more than 40 characters" | 'the quick brown fox jumps over the lazy dog'
        "null value"              | null
    }
}
