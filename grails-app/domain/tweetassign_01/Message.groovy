package tweetassign_01



class Message {

    String msgText
    Date dateCreated
    static hasOne = [acc: Account]
    static constraints = {
        msgText nullable: false, blank: false, size: 1..40
    }


}