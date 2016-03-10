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
class FollowFunctionalTestSpec extends GebSpec{

    @Shared
    def accountId

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }


     def 'F1 Create a REST endpoint that will allow one account to follow another.'()
    {

        when:
        def response = restClient.get(path:"/accounts")

        then:
        response.data.id
        response.status == 200

        when:

        accountId = response.data[0].id //def UserA = new Account(fullName: 'Nayna', emailAddress: 'nay@nay.com', accountHandle:'nayna', accountPassword: 'h3Lloworld')
        def UserB = response.data[1]

        def json = UserB as JSON
        //def responseB = restClient.post(path:"/accounts", body:json as String, requestContentType: 'application/json')
        def responseA = restClient.post(path: "/accounts/${accountId}/follow", body: json as String, requestContentType: 'application/json')

        //def response = restClient.get(path:'/accounts', body:json as String, requestContentType:'application/json')

        then:
        responseA.status ==200
       // HttpResponseException problem = thrown(HttpResponseException)
       // problem.statusCode == 422
       // problem.message
       // responseA.status == 200
        //responseA.data.id
       // responseA.data.follow




    }





}
