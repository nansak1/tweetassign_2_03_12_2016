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



   /* def 'M1_accountid: Create a REST endpoint will create a Message given a specified Account id and message text'(){
        when:

        def response = restClient.get(path:'/accounts')

        then:
        response.data.id
        response.status = 200

        when:
        def postBody = [msgText: 'Hello World!']
        accountId = response.data[0].id

        def json = postBody as JSON
        def responseM1 = restClient.post(path: "/accounts/${accountId}/messages", body: json as String, requestContentType:'application/json' )

        then:
        responseM1.data.msgText == 'Hello World!'
        responseM1.data.acc.id == 1


    }

    def 'M1_accountHandle: Create a REST endpoint will create a Message given a specified Account handle and message text'(){
        when:

        def response = restClient.get(path:'/accounts')

        then:
        response.data.id
        response.status = 200

        when:
        def postBody = [msgText: 'Hello World!']
        def accountHandle = response.data[0].accountHandle

        def json = postBody as JSON
        def responseM1 = restClient.post(path: "/accounts/${accountHandle}/messages", body: json as String, requestContentType:'application/json' )

        then:
        responseM1.data.msgText == 'Hello World!'
        responseM1.data.acc.accountHandle == 'w'


    }*/



  /*  def 'M3: Create a REST endpoint that will return the most recent messages for an Account. The endpoint must honor a limit parameter that caps the number of responses. The default limit is 10. (data-driven test) #description'() {
        when:
        accountId = 1
        def response = restClient.get(path: "/accounts/${accountId}")

        then:
        response.status == 200
        response.data.id == 1

    }*/

    def "M3:Messages for an account"(){

      when:
       accountId = 1
        def max
       //def limit = 12
       def responseM = restClient.get(path:"/accounts/${accountId}/messages/${accountId}")
      //def responseM = restClient.get(path:"/accounts/${accountId}/messages/${accountId}?(.$max:2)?")


       then:
       //responseM.status == 200
       //responseM.data
       //response.data.size == 2

       /*  then:
      response.data.size()
      response.data.msg
      response.data.dateCreated*/


    HttpResponseException problem = thrown(HttpResponseException)
    problem.statusCode == 404
    problem.message




     }




}
