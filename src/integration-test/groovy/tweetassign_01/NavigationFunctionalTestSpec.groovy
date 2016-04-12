package tweetassign_01

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import groovyx.net.http.RESTClient
import spock.lang.Ignore
import spock.lang.Shared

@Integration

/**
 * Created by nayna on 4/5/2016.
 */
class NavigationFunctionalTestSpec extends GebSpec{

        RESTClient restClient

        @Shared
        def token

        def setup(){
            //restClient = new RESTClient(baseUrl)

                when:
                go'/'
                $("#login-form input[name=username]").value("richelliot")
                $("#login-form input[name=password]").value("msse2016ASSIGN")
                $("#login").click()
                $("#search").click()

                then:
                $(".page-header").text() == "Search"
        }


         def 'N1: User’s detail page'(){

              when:
              $("#details").click()

              then:
              $(".page-header").text() == "Greetings!!"

          }

          def 'N2: Search box '(){

              when:
              $("#search").click()

              then:
              $("#searchInput").text() == ''

          }

         /* def 'N3: Logout - clicking this should bring you to the login screen and provide a helpful message ‘Sorry to see you go… etc’'(){

              when:
              //you click on the logout button
              $(#logout).click()

              then:
              //go() api/login
              alert("sorry to see you go")


          }*/

}
