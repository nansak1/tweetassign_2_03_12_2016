package tweetassign_01

import grails.converters.JSON
import grails.rest.RestfulController

class AccountController extends RestfulController{

    static responseFormats = ['json', 'xml']
    AccountController(){
        super(Account)
    }



    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Account.list(params), model:[accountCount: Account.count()]
    }


   @Override
   def show() {
     def accountId = (params.id as String).isNumber()

     if (accountId){
        respond Account.get(params.id)
     }
     else {
        respond Account.findByAccountHandle(params.id)
     }


    /*if(accountId)
    {
        //render account as JSON
        //render msgList as JSON
        render accountId as JSON
    }
    else{

        render(status:404, text:'No account found.')
        //response.status = 404
        //respond  max(account.msg.)
    }*/

   }

    def follow(){
        def accountId = params.accountId
        def accountFollower = request.JSON

        if (accountFollower/* && accountId && accountId != accounttoFollow*/)
        {
            accountId.addToFollowers(accountFollower)
            //accounttoFollow.addToFollowers(accountId)
        }
        else {
            respond(status:404, msg:'Error')
        }



    }

    /*def create() {

        def response = restClient.get(path: '/accounts')


        if (response.data.size == 0){
                response.statusCode(422)
                response.message("No account was created")

            }
            else {
                [account:account]
            }



    }*/


}
