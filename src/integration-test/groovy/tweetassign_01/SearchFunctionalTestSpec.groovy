package tweetassign_01

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import groovyx.net.http.RESTClient
import spock.lang.Ignore

/**
 * Created by nayna on 4/4/2016.
 */
@Integration
class SearchFunctionalTestSpec extends GebSpec{


    RESTClient restClient

    //@Shared
    //def token

    def setup(){

        when:
        go'/'
        $("#login-form input[name=username]").value("richelliot")
        $("#login-form input[name=password]").value("msse2016ASSIGN")
        $("#login").click()
        $("#search").click()

        then:
        $(".page-header").text() == "Search"


    }


   /* def 'Search Page'(){

        when:
        go'/'
        $("#login-form input[name=username]").value("richelliot")
        $("#login-form input[name=password]").value("msse2016ASSIGN")
        $("#login").click()
        $("#search").click()

        then:
        $(".page-header").text() == "Search"
    }*/

      def 'S1: Provide a search box for finding messages by message poster and message contents '(){

          when:
          //perform a search by message content
          $("#searchInput").value("Atl")
          $("#searchBtn").click()
          sleep(1000)

          then:
          $("#searchResults td")[0].text() == "richelliot"
          $("#searchResults td")[1].text() == "Welcome to Atlanta"


      }

  /*   def 'S2: Display matching results in a scrollable area below the search box '(){

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




      }*/

      def 'S3: Search result messages will display the message contents as well as the posting user.'(){

           when:
          //perform a search by message content
          $("#searchInput").value("Atl")
          $("#searchBtn").click()
          sleep(1000)

          then:

          $("#searchResults td")[0].text() == "richelliot"
          $("#searchResults td")[1].text() == "Welcome to Atlanta"

      }

   /* def 'S4: Clicking on the posting user’s name in a message will route to the user’s detail page.'(){

        when:
        //perform a search by message content
        $("#searchInput").value("warm")
        $("#searchBtn").click()
        sleep(1000)

        then:

        $("#searchResults td")[0].text() == "donaldtrump"
        $("#searchResults td")[1].text() == "It's getting better, infact it is warm."

       when:

          $(".handle")[0].click()
          sleep(2000)

        then:
        //perform a search by account
        $("#userDetails td")[0].text()=="Donald Trump"



    }*/
}
