package tweetassign_01

import grails.converters.JSON
import grails.rest.RestfulController

class MessageController extends RestfulController{

    static responseFormats = ['json', 'xml']

    MessageController(){

        super(Message)
    }

    @Override
    protected Message queryForResource(Serializable id) {
        def accountId = params.accountId
        Message.where{
            id == id && acc.id == accountId
        }.find()
    }

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def accountId = params.accountId
        respond Message.where{ id == id && acc.id == accountId }.find()
    }

    @Override
   def show()
    {
        //given an account show the most recent messages first
        def accountId = params.accountId
        def msgList = Message.listOrderByDateCreated(max: 10, order: "desc", acc:accountId)
        if (accountId){
            respond  msgList //as JSON
        }
        else {
            response.status = 404
        }


    }

    @Override
    def createResource(){
        msg = params.msgText
        accountId = params.accountId

        msg = new Message(msgText:msg, acc:accountId )
        msg.save()

    }





}
