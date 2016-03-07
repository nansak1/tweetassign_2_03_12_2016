package tweetassign_01

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Stepwise


@Integration
@Stepwise
class AccountFunctionalTestSpec extends GebSpec{

    @Shared
    def accountId

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }

    /*def 'A2: Return an error response from the create Account endpoint if the account values are invalid #description'(){

        given:

        def acc = new Account(fullName: fullName, emailAddress: emailAddress, accountHandle:accountHandle , accountPassword: accountPassword)
        def json = acc as JSON

        when:
        def response = restClient.post(path: '/accounts', body: json as String, requestContentType: 'application/json')

        then:

        HttpResponseException problem = thrown(HttpResponseException)
        problem.statusCode == 422
        problem.message


        where:
        description               | fullName      | emailAddress      | accountHandle | accountPassword
        "emailAddress missing"    | 'nayna n'     | ''                | 'b15'         | 'h3Lloworld'
        "accountHandle missing"   | 'Walt Disney' | 'wd@disney.world' | ''            | 'waltd1sNey'
        "accountPassword missing" | 'Walt Disney' | 'wd@disney.world' | 'walt'        | ''
        "fullName missing"        | ''            | 'wd@disney.world' | 'waltd'       | 'waltd1sNey'

    }*/

    def 'M2'(){
        given:
        def account = new Account(accountHandle: 'janeakinyi', fullName: 'Janet Akinyi', emailAddress: 'jakinyi@umn.edu', accountPassword: 'msse2016ASSIGN')
        def json = account as JSON

        when:

        def responseA = restClient.post(path:'/accounts', body: json as String, requestContentType: 'application/json')

        then:
        responseA.status == 201
        responseA.data.id

        when:
        account.id = responseA.data.id
        def mssg = new Message(msgText: 'Welcome to Atlanta', acc: account)
        json = mssg as JSON
        def responseM = restClient.post(path:'/messages', body: json as String, requestContentType:'application/json')

        then:
        responseM.status == 201

    }
    /*def 'M3: Create a REST endpoint that will return the most recent messages for an Account. The endpoint must honor a limit parameter that caps the number of responses. The default limit is 10. (data-driven test) #description'()
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
        def msg = new Message(msgText: 'booya', acc: account)
        json = msg as JSON
        def responseM = restClient.post(path:'/messages', body: json as String, requestContentType:'application/json')
        //acc == accountId

        then:
        responseM.status == 201*/


        /*HttpResponseException problem = thrown(HttpResponseException)
        problem.statusCode == 422
        problem.message*/


       /* where:
        description               | someText
        "some letters "           | 'This is the unit test'
        "less than 40 characters" | 'Supercalifragilisticexpialidocious'
        "number"                  | 12345
        "some text 4 "            | 'text 4'
        "some text 5 "            | 'text 5'
        "some text 6 "            | 'text 6'
        "some text 7 "            | 'text 7'
        "some text 8 "            | 'text 8'
        "some text 9 "            | 'text 9'
        "some text 10 "           | 'text 10'
        "some text 11 "           | 'text 11'
        "some text 12 "           | 'text 12'*/

   /* }*/

    /*def 'M2: Return an error response from the create Message endpoint if user is not found or message text is not valid (data-driven test) #description'() {

        given:
        def newAccount = new Account(accountHandle: 'janeakinyi', fullName: 'Janet Akinyi', emailAddress: 'jakinyi@umn.edu', accountPassword: 'msse2016ASSIGN')
        def json = newAccount as JSON

        ​when:
        def accountResponse = restClient.post(path: '/accounts', body: json as String, requestContentType: 'application/json')

        then:
        accountResponse.status == 201
        accountResponse.data.id

        when:
        newAccount.id = accountResponse.data.id
        def message = new Message(msgText: 'Welcome to Atlanta', acc: newAccount)
        json = message as JSON
        def messageResponse = restClient.post(path: '/messages', body: json as String, requestContentType: 'application/json')

        ​then:
        messageResponse.status == 201

    }*/

}