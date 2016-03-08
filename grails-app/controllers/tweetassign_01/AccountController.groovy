package tweetassign_01

import grails.converters.JSON
import grails.rest.RestfulController

class AccountController extends RestfulController{

    static responseFormats = ['json', 'xml']
    AccountController(){
        super(Account)
    }



   /* }*/

    /*def get(){
        def account = Account.get(params.id)

        if (!account){
            response.sendError(404)*/

            /*if (account.hasErrors()){
            //respond account.errors*/
       /* }
            else{
            [account:account]
        }
    }*/
@Override
   def show() {

    def account = Account.get(params.id)

    def msgList = Message.listOrderByDateCreated(max: 10, order: "desc", acc: account)
    //def msg = Message.findAll("from Message as m where m.account = :account", [acc: account])
    def mssg = Message.findByAcc(acc: account)

    if(account)
    {
        //render account as JSON
        //render msgList as JSON
        render mssg as JSON
    }
    else{
        response.status = 404
        //respond  max(account.msg.)
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
