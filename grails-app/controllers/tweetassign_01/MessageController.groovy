package tweetassign_01

import grails.converters.JSON
import grails.rest.RestfulController
import groovy.json.JsonOutput

class MessageController extends RestfulController {

    static responseFormats = ['json', 'xml']

    MessageController() {

        super(Message)
    }

    /*   @Override
       protected Message queryForResource(Serializable id) {
           def accountId = params.accountId
           def acc = Message.findByAcc(accountId)
           Message.where{
               id == id && acc.id == accountId
           }.find()
       }*/

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def accountId = params.accountId
        if (accountId) {
            def accountInfo = (params.accountId as String).isNumber()
            if (accountInfo) {
                accountId = Account.get(params.accountId)
            } else {
                accountId = Account.findByAccountHandle(params.accountId)
            }
            def msgList = Message.findAllByAcc(accountId, [max: params.max, sort: accountId.msg.dateCreated, order: "desc", offset: params.offset, text: params.text])
            respond msgList
        } else {
            respond(status: 200, msgError: "No message found")
        }
    }

    def searchText() {
        def searchTerm = params.text
        if (searchTerm) {
            //def results = Message.findAllByMsgTextIlikeAndAcc("%${searchTerm}%", Account.)
            def queryTxt = "select a.accountHandle,m.msgText,m.dateCreated from Message m,Account a where m.acc.id=a.id and m.msgText like '%${searchTerm}%')"
            def results = Message.executeQuery(queryTxt)
            def searchResult = results.collect { res -> ['accountHandle': res[0], 'msgText': res[1], 'dateCreated': res[2]]
            }
            render searchResult as JSON
        } else {
            respond(status: 200, msg: "No message found")
        }
    }

    @Override
    def createResource() {
        def accountId
        def msg = request.JSON.msgText
        if (!msg) {
            respond(status: 200, msgError: 'No messages')
        } else {
            def accountInfo = (params.accountId as String).isNumber()
            if (accountInfo) {
                accountId = Account.get(params.accountId)
            } else {
                accountId = Account.findByAccountHandle(params.accountId)
            }
            msg = new Message(msgText: msg, acc: accountId)
            msg.save()
        }
    }
}
