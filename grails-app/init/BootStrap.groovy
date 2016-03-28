import grails.converters.JSON

import tweetassign_01.Account
import tweetassign_01.Message
import tweetassign_01.Role
import tweetassign_01.UserRole


class BootStrap {

    def jsonMarshAcc = JSON.registerObjectMarshaller(Account) {
        Account a -> return [id: a?.id, accountHandle: a?.accountHandle, fullName: a?.fullName, emailAddress: a?.emailAddress, accountPassword: a?.accountPassword, followers: a?.followers?.accountHandle, following: a?.following?.accountHandle, msg: a?.msg, followingTotal: a?.following?.size(), followersTotal: a?.followers?.size()]
    }

    def jsonMarshMsg = JSON.registerObjectMarshaller(Message) {
        Message m -> return [id: m?.id, msgText: m?.msgText, dateCreated: m?.dateCreated]
    }

    def init = { servletContext ->

        //roles

            def admin = new Account(accountHandle:'admin', fullName: 'Admin', emailAddress: 'admin@twtr.com', accountPassword:'msse2016ASSIGN').save(flush: true, failOnError: true)
            def role = new Role(authority: 'ROLE_READ').save(flush: true, failOnError: true)
            new UserRole(user: admin, role: role).save(flush: true, failOnError: true)


/*
        // account information
        def a1 = new Account(accountHandle: 'richelliot', fullName: 'Richard Elliot', emailAddress: 'richelliot@gmail.com', accountPassword: 'msse2016ASSIGN').save(failOnError: true)
        def a2 = new Account(accountHandle: 'donaldtrump', fullName: 'Donald Trump', emailAddress: 'dtrump@trumpmag.com', accountPassword: 'msse2016ASSIGN').save(failOnError: true)
        def a3 = new Account(accountHandle: 'jacquekult', fullName: 'Jacqualine Kimtai', emailAddress: 'jkimtaii@umn.edu', accountPassword: 'msse2016ASSIGN', followers:[a1], following: [a1]).save(failOnError: true)
        def a4 = new Account(accountHandle: 'jeremyn', fullName: 'Jeremy Nathan', emailAddress: 'jnath@umn.edu', accountPassword: 'msse2016ASSIGN', followers: [a1, a3], following: [a2]).save(failOnError: true)
        def a5 = new Account(accountHandle: 'kkadeshian', fullName: 'Kim Kadeshian', emailAddress: 'kkadeshian@umn.edu', accountPassword: 'msse2016ASSIGN', followers: [a1, a2, a3, a4], following: [a2, a4]).save(failOnError: true)
        def a6 = new Account(accountHandle: 'w', fullName: 'nay na', emailAddress:'a@com', accountPassword:'msse2016ASSIGN').save(failOnError: true)
        def a7 = new Account(accountHandle: 'wa', fullName: 'nay na', emailAddress:'ab@com', accountPassword:'msse2016ASSIGN').save(failOnError: true)
        def a8 = new Account(accountHandle: 'wal', fullName: 'nay na', emailAddress:'abc@com', accountPassword:'msse2016ASSIGN').save(failOnError: true)

        //messages
        def m1 = new Message(msgText: "Welcome to Atlanta", acc: a1).save(failOnError: true)
        def m2 = new Message(msgText: "Welcome to Minnesota", acc: a2).save(failOnError: true)
        def m3 = new Message(msgText: "How is the weather out there?", acc: a1).save(failOnError: true)
        def m4 = new Message(msgText: "It's getting better, infact it is warm.", acc: a2).save(failOnError: true)
        def m5 = new Message(acc: a1, msgText: 'Hello World 1').save(failOnError: true)
        def m6 = new Message(acc: a1, msgText: '15 minutes can save you 15% or more').save(failOnError: true)
        def m7 = new Message(acc: a3, msgText: 'A quick brown fox jumps on the lazy dog').save(failOnError: true)
        def m8 = new Message(acc: a1, msgText: 'Hello World 2').save(failOnError: true)
        def m9 = new Message(acc: a1, msgText: 'Hello World 3').save(failOnError: true)
        def m10 = new Message(acc: a1, msgText: 'Hello World 4').save(failOnError: true)
        def m11 = new Message(acc: a1, msgText: 'Hello World 5').save(failOnError: true)
        def m12 = new Message(acc: a1, msgText: 'Hello World 6').save(failOnError: true)
        def m13 = new Message(acc: a1, msgText: 'Hello World 7').save(failOnError: true)
        def m14 = new Message(acc: a1, msgText: 'Hello World 8').save(failOnError: true)
        def m15 = new Message(acc: a1, msgText: 'Hello World 9').save(failOnError: true)
        def m16 = new Message(acc: a1, msgText: 'Hello World 10').save(failOnError: true)
        def m17 = new Message(acc: a1, msgText: 'Hello World 11').save(failOnError: true)
        def m18 = new Message(acc: a1, msgText: 'Hello World 12').save(failOnError: true)
        def m19 = new Message(acc: a1, msgText: 'Hello World 13').save(failOnError: true)
        def m20 = new Message(msgText: "It's a tree.", acc: a4).save(failOnError: true)
        def m21 = new Message(msgText: "Are you sure about that?.", acc: a1).save(failOnError: true)
        def m22 = new Message(msgText: "Yes most certainly", acc: a4).save(failOnError: true)
        def m23 = new Message(msgText: "How many do you have?", acc: a3).save(failOnError: true)
        def m24 = new Message(msgText: "Six for the one in our family room", acc: a4).save(failOnError: true)
        def m25 = new Message(msgText: "Ok, see you!", acc: a1).save(failOnError: true)*/

    }
    def destroy = {
    }
}
