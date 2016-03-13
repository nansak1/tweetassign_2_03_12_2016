package tweetassign_01

import grails.converters.JSON
import grails.rest.RestfulController

class AccountController extends RestfulController{

    static responseFormats = ['json', 'xml']
    AccountController(){
        super(Account)
    }
  /*  def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Account.list(params), model:[accountCount: Account.count()]
    }*/

   @Override
   def show() {
     def accountId = (params.id as String).isNumber()

     if (accountId){
        respond Account.get(params.id)
     }
     else {
        respond Account.findByAccountHandle(params.id)
     }
   }

    def follow(){
        def accountA = Account.findById(params.accountId)
        def accountAFollower = Account.findById(params.follower)
        if (accountAFollower && accountA && accountA != accountAFollower)
        {
            accountA.addToFollowers(accountAFollower)
            render accountA as JSON
        }
        else {
            respond(status:404, msgError:'No followers')
        }
    }
}
