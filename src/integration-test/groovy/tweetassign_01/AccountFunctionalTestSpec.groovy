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









}