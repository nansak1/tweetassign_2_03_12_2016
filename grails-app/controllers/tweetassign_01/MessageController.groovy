package tweetassign_01

import grails.rest.RestfulController

class MessageController extends RestfulController <Message>{

    static responseFormats = ['json', 'xml']

    MessageController(){

        super(Message)
    }


    @Override
   def show()
    {
        //given an account show the most recent messages first
        def accId = Account.get(params.id)
        def msgList = Message.listOrderById(max: 10, order: "desc")
        /*if (accId){
            respond  Max(Message.where( accId == acc.id).find())
        }
        else {
            respond ("some message")
        }*/


    }
   /* @Override
    protected Message queryForResource(Serializable id) {
        //def accountId = Account.get(params.id)

        Message.where{
            id == id && account.id == params.accountId
        }.find()
    }*/


    //@Override
    //protected Message queryforResources(serialzable id)
    //def index() { }
}
