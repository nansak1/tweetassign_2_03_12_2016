package tweetassign_01

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.*


@Integration
@Stepwise

/**
 * Created by nayna on 3/17/2016.
 */
class AccountResourceFunctionalSpec extends GebSpec{

    RESTClient restClient

    @Shared
    def token

    def setup(){
        restClient = new RESTClient(baseUrl)
    }

    def 'L3: Invalid login will be rejected with an error message'(){

        when:
        restClient.get(path: "/api/accounts")

        then:
        HttpResponseException problem = thrown(HttpResponseException)
        problem.statusCode == 403
        problem.message.contains('Forbidden')
    }

   def 'passing a valid username and password generates a token'(){
        setup:
        def authentication =([accountHandle:'admin', accountPassword:'msse2016ASSIGN'] as JSON) as String

        when:
        def response = restClient.post(path:'/api/login', body: authentication, requestContentType: 'application/json')

        then:
        response.status == 200
        response.data.username=='admin'
        response.data.roles ==['ROLE_READ']

        //noinspection GroovyDoubleNegation
        !!(token = response.data.access_token)

    }

    def 'using token to account endpoint allowed'(){

        when:
        def response = restClient.get(path:'/accounts', headers: ['X-Auth-Token':token])

        then:
        response.status == 200
        response.data.size() == 0


    }

}
