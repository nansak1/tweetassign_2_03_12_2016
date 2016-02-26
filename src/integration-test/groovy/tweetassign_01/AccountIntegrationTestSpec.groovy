package tweetassign_01
import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*
@Integration
@Rollback
class AccountIntegrationTestSpec extends Specification {
    def "A4. Saving account with a non-unique email or handle address must fail (integration test)"() {
        setup:
        def accountsBefore = Account.count()
        def arr = [
                [accountHandle: 'walterauma', fullName: 'Walter Auma', emailAddress: 'walterauma@umn.edu', accountPassword: "msse2016ASSIGN"],
                [accountHandle: 'naynanayate', fullName: 'Nayna Nayate', emailAddress: 'walterauma@umn.edu', accountPassword: "msse2016ASSIGN"],
                [accountHandle: 'walterauma', fullName: 'Nayna Nayate', emailAddress: 'naynan@umn.edu', accountPassword: "msse2016ASSIGN"],
                [accountHandle: 'nayna2016', fullName: 'Nayna Natalie', emailAddress: 'natalien@umn.edu', accountPassword: "msse2016ASSIGN"]
        ]
        def acc01 = new Account(arr[0]) // initial valid account
        def acc02 = new Account(arr[1]) // second account with same emailAddress as acc01
        def acc03 = new Account(arr[2]) // third account with the same accountHandle as acc01
        def acc04 = new Account(arr[3]) // fourth account with unique accountHandle or emailAddress when compared to acc01
        when:
        acc01.save(flush: true, failOnError: true)
        acc02.save(flush: true)
        acc03.save(flush: true)
        acc04.save(flush: true, failOnError: true)
        then: "Initial acc01 and acc04 are saved without any issues"
        acc01.id
        acc01.get(acc01.id).accountHandle == 'walterauma'
        acc01.get(acc01.id).emailAddress == 'walterauma@umn.edu'
        acc01.hasErrors() == false
        // Duplicate emailAddress
        acc02.hasErrors() == true
        // Duplicate accountHandle
        acc03.hasErrors() == true
        // Unique emailAddress or accountHandle
        acc04.id
        acc04.get(acc04.id).accountHandle == 'nayna2016'
        acc04.get(acc04.id).emailAddress == 'natalien@umn.edu'
        acc04.hasErrors() == false
        Account.count() == accountsBefore + 2
    }
    def "F1. An account may have multiple followers (integration test)"() {
        setup:
        def arr = [
                [accountHandle: 'walterauma', fullName: 'Walter Auma', emailAddress: 'walterauma@umn.edu', accountPassword: 'msse2016ASSIGN'],
                [accountHandle: 'naynanayate', fullName: 'Nayna Nayate', emailAddress: 'naynayate@umn.edu', accountPassword: 'msse2016ASSIGN'],
                [accountHandle: 'janeakinyi', fullName: 'Janet Akinyi', emailAddress: 'jakinyi@umn.edu', accountPassword: 'msse2016ASSIGN'],
                [accountHandle: 'nnatalie', fullName: 'Nayna Natalie', emailAddress: 'natalien@umn.edu', accountPassword: 'msse2016ASSIGN']
        ]
        def acc01 = new Account(arr[0]).save(flush: true, failOnError: true)
        def acc02 = new Account(arr[1]).save(flush: true, failOnError: true)
        def acc03 = new Account(arr[2]).save(flush: true, failOnError: true)
        def acc04 = new Account(arr[3]).save(flush: true, failOnError: true)
        when:
        // Add new account with multiple followers
        def newAcc = new Account(accountHandle: 'billgraham', fullName: 'Bill Graham', emailAddress: 'bgraham@umn.edu', accountPassword: 'msse2016ASSIGN', followers: acc01)
        newAcc.addToFollowers(acc02)
        newAcc.addToFollowers(acc04)
        newAcc.save(flush: true, failOnError: true)
        // Update an account, adding new followers
        def foundAcc = acc02.get(acc02.id)
        foundAcc.addToFollowers(acc04)
        foundAcc.addToFollowers(acc03)
        foundAcc.save(flush: true, failOnError: true)
        then:
        newAcc.id
        newAcc.hasErrors() == false
        newAcc.get(newAcc.id).fullName == 'Bill Graham'
        newAcc.get(newAcc.id).followers.find{it.fullName =='Walter Auma'}
        newAcc.get(newAcc.id).followers.find{it.fullName =='Nayna Nayate'}
        newAcc.get(newAcc.id).followers.find{it.fullName =='Nayna Natalie'}
        foundAcc.id
        foundAcc.hasErrors() == false
        acc02.get(acc02.id).followers.find { it.fullName == 'Nayna Natalie' }
        acc02.get(acc02.id).followers.find { it.fullName == 'Janet Akinyi' }
    }
    //F2. Two accounts may follow each other
    def "F2. Two accounts may follow each other (integration test)"() {
        setup:
        def userA = new Account(accountHandle: 'walterauma', fullName: 'Walter Auma', emailAddress: 'walterauma@umn.edu', accountPassword: 'msse2016ASSIGN')
        def userB = new Account(accountHandle: 'naynan', fullName: 'Nayna Nayate', emailAddress: 'naynan@umn.edu', accountPassword: 'msse2016ASSIGN')
        when:
        //userA follows UserB, userB follows UserA
        userA.addToFollowing(userB)
        userA.save(flush: true, failOnError: true)
        userB.addToFollowing(userA)
        userA.save(flush: true, failOnError: true)
        then:
        //shows that User A is following User B
        userA.id
        !userA.hasErrors()
        userA.fullName == 'Walter Auma'
        userA.following.find { it.fullName == 'Nayna Nayate' }
        //shows that User B is following User A
        userB.id
        !userB.hasErrors()
        userB.fullName == 'Nayna Nayate'
        userB.following.find { it.fullName == 'Walter Auma' }
    }
}
