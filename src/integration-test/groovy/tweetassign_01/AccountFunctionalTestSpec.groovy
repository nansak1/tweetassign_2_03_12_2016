package tweetassign_01

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Ignore
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

}