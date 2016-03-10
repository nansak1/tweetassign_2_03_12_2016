package tweetassign_01

import grails.converters.JSON
import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Stepwise


@Integration
@Stepwise

/**
 * Created by nansak1 on 3/9/2016.
 */
class MessageFunctionalTestSpec extends GebSpec{

    @Shared
    def accountId

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }


    def 'M1_account: Create a REST endpoint will create a Message given a specified Account id and message text'(){
        when:

        //def account = new Account(fullName: 'Nayna', emailAddress: 'nay@nay.com', accountHandle:'nayna', accountPassword: 'h3Lloworld')
        //def json = account as JSON
       // def responseA = restClient.post(path: "/accounts", body: json as String, requestContentType: 'application/json')

        def response = restClient.get(path:'/accounts')

        then:
        response.data.id

       when:
        def messageText = 'Hello World!'
        accountId = response.data[0].id
        def msgAccId = new Message(msgText:messageText, acc:accountId)
        def json = msgAccId as JSON
        def responseM1 = restClient.post(path: "accounts/${accountId}/messages", body: json as String, requestContentType:'application/json' )

        then:
        responseM1.data.msgText == 'Hello World!'
        //responseM1.data.acc.id == account.id
        //HttpResponseException problem = thrown(HttpResponseException)
        //problem.statusCode == 422
        //problem.message

    }

   def 'M1_handle: Create a REST endpoint will create a Message given a specified Account handle and message text'(){

       //def account = new Account(fullName: 'Nayna', emailAddress: 'nay@nay.com', accountHandle:'nayna', accountPassword: 'h3Lloworld')
       //def json = account as JSON
       //def responseA = restClient.post(path: '/accounts', body: json as String, requestContentType: 'application/json')
       when:

       def response = restClient.get(path:'/accounts')

       then:
       response.data.accountHandle


        //def response = restClient.get(path:'/accounts', body:json as String, requestContentType:'application/json')

        when:
        def messageText = 'Hello World!'
        def accountHandle = responseA.data[0].accountHandle
        def msgAccHandle = new Message(msgText:messageText, acc:accountHandle)
        json = msgAccHandle as JSON
        def responseM2 = restClient.post(path: "accounts/${accountHandle}/messages", body: json as String, requestContentType:'application/json')

        then:
        responseM2.data.msgText =='Hello World!'
        responseM2.data.acc.accountHandle == 'nayna'
    }


    /*  def 'M3: Create a REST endpoint that will return the most recent messages for an Account. The endpoint must honor a limit parameter that caps the number of responses. The default limit is 10. (data-driven test) #description'()
   {
       given:
       def account = new Account(fullName: 'Nayna', emailAddress: 'nay@nay.com', accountHandle:'nayna', accountPassword: 'h3Lloworld')
       def json = account as JSON

       when:
       def responseA = restClient.post(path: '/accounts', body: json as String, requestContentType: 'application/json')

       then:
       responseA.status == 201
       responseA.data.id


       when:
       account.id =  responseA.data.id
       //def msg = new Message(msgText: 'hello World', acc: account)
       def msg = new Message(msgText: someText, acc:account)
       json = msg as JSON
       def responseM = restClient.post(path:'accounts/$accountId/messages', body: json as String, requestContentType:'application/json')
       //def responseM = restClient.post(path:'/messages', body: json as String, requestContentType:'application/json')
       //acc == accountId

       then:
       responseM.status == 201

     /*  when:
       accountId = account.get(params.id)
       response = restClient.get(path:'accounts/${accountId}/messages', body:json as String, requestContentType:'application/json')*/

    /*  then:
      response.data.size()
      response.data.msg
      response.data.dateCreated*/


    /*HttpResponseException problem = thrown(HttpResponseException)
    problem.statusCode == 404
    problem.message*/


    /*  where:
      description               | someText                                    //| acc
     "some letters "            | 'This is the unit test'                     //| account.id*/
    // "less than 40 characters" | 'Supercalifragilisticexpialidocious'       //| account
    //"number"                  | 12345
    /* "some text 4 "            | 'text 4'
     "some text 5 "            | 'text 5'
     "some text 6 "            | 'text 6'
     "some text 7 "            | 'text 7'
     "some text 8 "            | 'text 8'
     "some text 9 "            | 'text 9'
     "some text 10 "           | 'text 10'
     "some text 11 "           | 'text 11'
     "some text 12 "           | 'text 12'*/

    /* }*/




}
