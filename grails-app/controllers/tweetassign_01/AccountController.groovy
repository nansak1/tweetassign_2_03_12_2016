package tweetassign_01

import grails.converters.JSON
import grails.rest.RestfulController

import java.text.SimpleDateFormat

class AccountController extends RestfulController {

    static responseFormats = ['json', 'xml']

    AccountController() {
        super(Account)
    }

    @Override
    def show() {
        def accountId = (params.id as String).isNumber()

        if (accountId) {
            respond Account.get(params.id)
        } else {
            respond Account.findByAccountHandle(params.id)
        }
    }

    def follow() {
        def accountA = Account.findById(params.accountId)
        def accountAFollower = Account.findById(params.follower)
        if (accountAFollower && accountA && accountA != accountAFollower) {
            accountA.addToFollowers(accountAFollower)
            render accountA as JSON
        } else {
            respond(status: 200, msgError: 'No followers')
        }
    }

    def showFollower() {
        def accountId = (params.accountId as String).isNumber()
        if (accountId) {
            def newAccount = Account.findById(params.accountId)
            def results = Account.where { id in newAccount.followers.id }.list(max: 10, offset: 0)
            respond results

        } else {
            def newAccount = Account.findByAccountHandle(params.accountId)
            def results = newAccount.where { id in newAccount.followers.id }.list(max: 10, offset: 0)
            respond results
        }
    }


    def showRecentMessage() {
        def accountId = (params.accountId as String).isNumber()

        if (accountId) {

            def queryTxt
            def msgResults
            if (!params.dateMsg) {
                queryTxt = "select a.accountHandle,m.msgText,m.dateCreated from Message m, Account a where " +
                        "m.acc.id=a.id and m.acc.id in (select b.id from Account a inner join a.following b " +
                        "where a.id=${params.accountId}) and m.id=(select max(m.id) from Message m where m.acc.id=a.id)"
                msgResults = Message.executeQuery(queryTxt, [max: params.max])
            } else {
                def inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(params.dateMsg)
                queryTxt = "select a.accountHandle,m.msgText,m.dateCreated from Message m, " +
                        "Account a where m.acc.id=a.id and m.acc.id in (select b.id from Account a " +
                        "inner join a.following b where a.id=${params.accountId}) and trunc(m.dateCreated)>=trunc(:dateEntry) " +
                        "and m.id=(select max(m.id) from Message m where m.acc.id=a.id)"
                msgResults = Message.executeQuery(queryTxt, [dateEntry: inputDate], [max: params.max])
            }

            def results = msgResults.collect { res -> ['accountHandle': res[0], 'msgText': res[1], 'dateCreated': res[2]] }
            render results as JSON

        } else {
            respond (status:200,msgError:"Invalid Account ID" )
        }

    }

}
