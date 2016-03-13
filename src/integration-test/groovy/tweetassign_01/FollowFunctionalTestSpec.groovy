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
        accountId = response.data[0].id
        def followerId = response.data[1].id
        def responseF1 = restClient.put(path:"/accounts/${accountId}/follow", query:[follower:followerId])
        then:
        responseF1.status == 200
        responseF1.data.followers.size == 1
    }

    def "F4: Create a ‘feed’ endpoint which will return the most recent messages by Accounts being followed by an Account. Include a response limit parameter. Include a parameter to only look for messages after a specified date."(){
        when:
        accountId = 4
        def following
        def date
        def max = 2
        def responseF5 = restClient.get(path:"/accounts/${accountId}", query:[following: following, max: max, date:date] )

        then:
        responseF4.status == 200
        responseF4.data[2].followingTotal == 2
    }





}
