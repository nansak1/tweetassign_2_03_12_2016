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


      def 'S1: Provide a search box for finding messages by message poster and message contents '(){

          when:
          go '/login'
          then:
          $(".page-header").text() == "Login"

          when:
          $("#login-form input[name=username]").value("richelliot")
          $("#login-form input[name=password]").value("msse2016ASSIGN")
          $("#login-form input[type=submit]").click()

          when:
          //perform a search by message content
          $("#searchInput").text("Atl")

          then:
          //$("#resultScroll").text()
          $("#searchInput").value() == "richelliot"
          $("#searchInput").value() == "some message about atlanta"


      }

     def 'S2: Display matching results in a scrollable area below the search box '(){

         when:
         go '/login'
         then:
         $(".page-header").text() == "Login"

         when:
         $("#login-form input[name=username]").value("richelliot")
         $("#login-form input[name=password]").value("msse2016ASSIGN")
         $("#login-form input[type=submit]").click()

         when:
         //perform a search by account
         $("#searchInput").text("")

         then:
         findElementbyId("#resultScroll") == true
         $("#searchInput").value() == "richelliot"
         $("#searchInput").value() == "some message about atlanta"




      }

      def 'S3: Search result messages will display the message contents as well as the posting user.'(){

          when:
          go '/login'
          then:
          $(".page-header").text() == "Login"

          when:
          $("#login-form input[name=username]").value("richelliot")
          $("#login-form input[name=password]").value("msse2016ASSIGN")
          $("#login-form input[type=submit]").click()

          when:
          //perform a search by account
          $("#searchInput").text("")

          then:

          $("#accountHandle").value() == "richelliot"
          $("#mgText").value() == "some message about atlanta"

      }

    def 'S4: Clicking on the posting user’s name in a message will route to the user’s detail page.'(){

        when:
        go '/login'
        then:
        $(".page-header").text() == "Login"

        when:
        $("#login-form input[name=username]").value("richelliot")
        $("#login-form input[name=password]").value("msse2016ASSIGN")
        $("#login-form input[type=submit]").click()

        when:
        //perform a search by account
        $("#searchInput").text("")

        then:

        $("#accountHandle").value() == "richelliot"
        $("#mgText").value() == "some message about atlanta"

        when:
        //go(/search) user's name in clicked on in search results
        ("#accountHandle").click()

        then:
        go'/details'

    }
}
