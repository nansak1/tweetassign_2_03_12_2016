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


class MessageFunctionalTestSpec extends GebSpec{

    @Shared
    def accountId

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }

    def 'M1_accountId: Create a REST endpoint will create a Message given a specified Account id and message text'(){
        when:
        def response = restClient.get(path:'/accounts')
        then:
        response.data.id
        response.status == 200

        when:
        def postBody = [msgText: 'Hello World!']
        accountId = response.data[0].id
        def json = postBody as JSON
        def responseM1 = restClient.post(path: "/accounts/${accountId}/messages", body: json as String, requestContentType:'application/json' )
        then:
        responseM1.status == 201
        responseM1.data.msgText == 'Hello World!'
    }

   def 'M1_accountHandle: Create a REST endpoint will create a Message given a specified Account handle and message text'(){
        when:
        def response = restClient.get(path:'/accounts')
        then:
        response.data.id
        response.status == 200

        when:
        def postBody = [msgText: 'Hello World!']
        def accountHandle = response.data[1].accountHandle
        def json = postBody as JSON
        def responseM1 = restClient.post(path: "/accounts/${accountHandle}/messages", body: json as String, requestContentType:'application/json' )

        then:
        responseM1.status == 201
        responseM1.data.msgText == 'Hello World!'

    }

    //def "M2: Return an error response from the create Message endpoint if user is not found or message text is not valid (data-driven test)"()

    def "M2 (invalid text): Return an error response from the create Message endpoint if message text is not valid (data-driven test)"() {
        given:
        def newAccount = new Account(accountHandle: accountHandle, fullName: fullName, emailAddress: emailAddress, accountPassword: 'msse2016ASSIGN')
        def json = newAccount as JSON

        when: 'Account is created'
        def accountResponse = restClient.post(path: '/accounts', body: json as String, requestContentType: 'application/json')

        then:
        accountResponse.status == 201
        accountResponse.data.id

        when: 'Message is created'
        newAccount.id = accountResponse.data.id
        def message = new Message(msgText: messageText, acc: newAccount)
        json = message as JSON
        def messageResponse = restClient.post(path: '/messages', body: json as String, requestContentType: 'application/json')

        then:
        HttpResponseException problem = thrown(HttpResponseException)
        problem.statusCode == 404
        problem.message

        where:
        description             | messageText | accountHandle | fullName         | emailAddress
        'Message text too long' | 'W' * 41    | 'jakinyi'     | 'Janet Akinyi'   | 'jakinyi@umn.edu'
        'Empty message text'    | ''          | 'bchris'      | 'Chris Brown'    | 'chrisbrown@umn.edu'
        'Null message'          | null        | 'bspears'     | 'Britney Spears' | 'bspears@umn.edu'
    }


    def "M3:Create a REST endpoint that will return the most recent messages for an Account. The endpoint must honor a limit parameter that caps the number of responses. The default limit is 10. (data-driven test) #description"(){
       when:
       accountId = 1
       def max = 2
       def responseM3 = restClient.get(path:"/accounts/${accountId}/messages", query:[max:max])
       then:
       responseM3.status == 200
       responseM3.data.size == 2
    }

    def 'M4: Support an offset parameter into the recent Messages endpoint to provide paged responses'(){
        when:
        accountId = 1
        def offset = 2
        def responseM4 = restClient.get(path:"/accounts/${accountId}/messages", query:[offset:offset])
        then:
        responseM4.status == 200

    }

    def "M5: Create a REST endpoint that will search for messages containing a specified search term. Each response value will be a JSON object containing the Message details (text, date) as well as the Account (handle)"(){

        when:

        def text = 'Wor'
        def responseM5 = restClient.get(path:"/messages/searchText", query:[text:text])
        then:
        responseM5.status == 200
        responseM5.data.size == 15

    }




}
