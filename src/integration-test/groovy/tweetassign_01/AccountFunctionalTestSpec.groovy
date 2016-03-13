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

    def 'A2: Return an error response from the create Account endpoint if the account values are invalid #description'(){

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

    }


    def "A1: Create a REST endpoint that receives JSON data to create an Account"() {
        given:
        def createAccount = new Account(accountHandle: 'rosemuhondo', fullName: 'Rose Muhondo', emailAddress: 'rmuhondo@umn.edu', accountPassword: 'msse2016ASSIGN')
        def json = createAccount as JSON
        when:
        def resp = restClient.post(path: '/accounts', body: json as String, requestContentType: 'application/json')
        then:
        resp.status == 201
        resp.data.id
        resp.data.accountHandle == 'rosemuhondo'
    }

    def "A3 (account id): Create a REST endpoint that returns JSON data with Account values for a user based on an account id. (data-driven test)"() {
        when:
        def resp = restClient.get(path: "/accounts/${account_id}")
        then:
        resp.status == 200
        where:
        description            | account_id
        "first account shown"  | '1'
        "second account shown" | '2'
        "third account shown"  | '3'

    }

    def "A3 (account handle): Create a REST endpoint that returns JSON data with Account values for a user based on an handle address. (data-driven test)"() {
        when:
        def resp = restClient.get(path: "/accounts/${account_handle}")
        then:
        resp.status == 200
        where:
        description            | account_handle
        "first account shown"  | 'richelliot'
        "second account shown" | 'donaldtrump'
        "third account shown"  | 'jacquekult'

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

    def "F2: For the endpoint created for requirement A3, add properties for total counts of followers and following for the account."() {
        when:
        def resp = restClient.get(path: "/accounts/${account_handle}")
        then:
        resp.status == 200
        resp.data.followersTotal == followersTotal
        resp.data.followingTotal == followingTotal
        where:
        description            | account_handle | followingTotal | followersTotal
        "first account shown"  | 'richelliot'   | 0              | 0
        "second account shown" | 'donaldtrump'  | 0              | 0
        "third account shown"  | 'jacquekult'   | 1              | 1
        "fourth account shown" | 'jeremyn'      | 1              | 2
        "fifth account shown"  | 'kkadeshian'   | 2              | 4

    }

    def "F3:"(){
        when:
        def resp=restClient.get(path:"/accounts/donaldtrump/followers")
        then:
        resp.status==200

    }






}