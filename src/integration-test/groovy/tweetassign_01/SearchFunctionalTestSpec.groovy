package tweetassign_01

import groovyx.net.http.RESTClient
import spock.lang.Shared

/**
 * Created by nayna on 4/4/2016.
 */
class SearchFunctionalTestSpec {


    RESTClient restClient

    @Shared
    def token

    def setup(){
        restClient = new RESTClient(baseUrl)
    }


    /*  def 'S1: Provide a search box for finding messages by message poster and message contents '(){

          when:
          //perform a search by message content
          (searchInput).text() ="h"

          then:
          //$("#resultScroll").text()

          when:
          //perform a search by username
          (searchInput).text() ="richelliot"

          then:
          //$("#resultScroll").text()

      }

      def 'S2: Display matching results in a scrollable area below the search box '(){

          when:
          search is successful
          //go(/search) api.login

          then:

          findElementbyId(#resultScroll) == true

      }

      def 'S3: Search result messages will display the message contents as well as the posting user.'(){

          when:
          //go(/search) with some input text

          then:
          //go() api/login
          find element for account handle and message

      }

    def 'S4: Clicking on the posting user’s name in a message will route to the user’s detail page.'(){

        when:
        //go(/search) user's name in clicked on in search results
        (#username).click

        then:
        //go(/accounts/username)
        go to account detail page of user

    }*/
}
