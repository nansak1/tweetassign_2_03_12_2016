package tweetassign_01

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Stepwise

@Integration
@Stepwise
class FollowFunctionalTestSpec extends GebSpec {

    @Shared
    def accountId

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }

     def 'F1 Create a REST endpoint that will allow one account to follow another.'() {

        when:
        def response = restClient.get(path:"/accounts")

        then:
        response.data.id
        response.status == 200

        when:
        accountId = (response.data as List)[0].id
        def followerId = (response.data as List)[1].id
        def responseF1 = restClient.put(path:"/accounts/${accountId}/follow", query:[follower:followerId])

        then:
        responseF1.status == 200
        responseF1.data.followers.size == 1
    }

    def 'F2: For the endpoint created for requirement A3, add properties for total counts of followers and following for the account.'() {
        when:
        def resp = restClient.get(path: "/accounts/${account_handle}")

        then:
        resp.status == 200
        resp.data.followersTotal == followersTotal
        resp.data.followingTotal == followingTotal

        where:
        description            | account_handle | followingTotal | followersTotal
        "first account shown"  | 'richelliot'   | 0              | 1
        "second account shown" | 'donaldtrump'  | 0              | 0
        "third account shown"  | 'jacquekult'   | 1              | 1
        "fourth account shown" | 'jeremyn'      | 1              | 2
        "fifth account shown"  | 'kkadeshian'   | 2              | 4
    }

    def 'F3:Add an endpoint to get the followers for an account. This will return the details about the followers (handle, name, email, id). Add the limit and offset logic implemented for messages to this endpoint.'(){
        when:
        accountId = 3
        def max = 2
        def offset = 1
        def responseF3 = restClient.get(path:"/accounts/${accountId}/followers", query:[max: max, offset:offset])

        then:
        responseF3.status == 200

        when:
        def followers = responseF3 as List

        then:
        followers[0].accountHandle == 'richelliot'
        followers[0].fullName == 'Richard Elliot'
        followers[0].emailAddress == 'richelliot@gmail.com'
        followers[0].id == 1
    }

    def "F4: Create a ‘feed’ endpoint which will return the most recent messages by Accounts being followed by an Account. Include a response limit parameter. Include a parameter to only look for messages after a specified date."(){
        when:
        accountId = 5
        def date = '2010-12-25'
        def max = 0
        def responseF4 = restClient.get(path:"/accounts/${accountId}/feed", query:[max: max, dateMsg:date] )

        then:
        responseF4.status == 200
    }

}
