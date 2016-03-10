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
        def limit = params.int(max)
        //def query = "from Message as m where m.acc=accountId order by m.dateCreated"
        //def msgList = Message.findAll{query}
        //def msgList = Message.findAll(acc: accountId)
        //def messages = Message.findAll("from Message where author=${account.id} order by date_created", [max: 100])
        def msgList = Message.listOrderByDateCreated(max: limit, order: "desc", acc:accountId)
        if (accountId){
            respond  msgList //as JSON
        }
        else {
            response.status = 404
        }


    }

    @Override
    def createResource() {
        def msg = request.JSON.msgText

        if (!msg) {
            repond(status: 404, msgError: 'No messages')
        } else {
            def accountId = params.accountId
            def accountHandle = params.accountHandle

            if (accountHandle) {
                accountId = Account.findByAccountHandle(params.accountHandle)

            }

            msg = new Message(msgText: msg, acc: accountId)
            msg.save()
        }
    }





}
