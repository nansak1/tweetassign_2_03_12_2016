class BootStrap {

    def init = { servletContext ->
        new tweetassign_01.Account(accountHandle: 'w', fullName: 'nay na', emailAddress:'a@com', accountPassword:'msse2016ASSIGN').save()
        new tweetassign_01.Account(accountHandle: 'wa', fullName: 'nay na', emailAddress:'ab@com', accountPassword:'msse2016ASSIGN').save()
        new tweetassign_01.Account(accountHandle: 'wal', fullName: 'nay na', emailAddress:'abc@com', accountPassword:'msse2016ASSIGN').save()
        new tweetassign_01.Account(accountHandle: 'walt', fullName: 'nay na', emailAddress:'abcd@com', accountPassword:'msse2016ASSIGN').save()
        new tweetassign_01.Account(accountHandle: 'walte', fullName: 'nay na', emailAddress:'abcde@com', accountPassword:'msse2016ASSIGN').save()
        new tweetassign_01.Account(accountHandle: 'walter', fullName: 'nay na', emailAddress:'abcdef@com', accountPassword:'msse2016ASSIGN').save()
        new tweetassign_01.Account(accountHandle: 'walter', fullName: 'nay na', emailAddress:'', accountPassword:'msse2016ASSIGN').save()

        new tweetassign_01.Message(acc: 1, msgText: 'Hello World').save()
        new tweetassign_01.Message(acc: 1, msgText: '15 minutes can save you 15% or more').save()
        new tweetassign_01.Message(acc: 3, msgText: 'A quick brown fox jumps on the lazy dog').save()
        new tweetassign_01.Message(acc: 1, msgText: 'Hello World').save()
        //new tweetassign_01.Account(accountHandle: 'walter', fullName: 'nay na', emailAddress:'ab@com', accountPassword:'msse2016ASSIGN').save()
        //new tweetassign_01.Account(accountHandle: 'walter', fullName: 'nay na', emailAddress:'ab@com', accountPassword:'msse2016ASSIGN').save()
    }
    def destroy = {
    }
}
