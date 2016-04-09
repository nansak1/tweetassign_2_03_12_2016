package tweetassign_01

import groovyx.net.http.RESTClient
import spock.lang.Ignore
import spock.lang.Shared

@Ignore

/**
 * Created by nayna on 4/5/2016.
 */
class NavigationFunctionalTestSpec {

        RESTClient restClient

        @Shared
        def token

        def setup(){
            restClient = new RESTClient(baseUrl)
        }


        /*  def 'N1: User’s detail page'(){

              when:
              //go(/accounts) Not logged in

              then:
              //$(".page-header").text() == "Account Detail"

          }

          def 'N2: Search box '(){

              when:
              search is successful
              //go(/search)

              then:
              findElementbyId(#resultScroll) == true

          }

          def 'N3: Logout - clicking this should bring you to the login screen and provide a helpful message ‘Sorry to see you go… etc’'(){

              when:
              //you click on the logout button
              (#logout).click

              then:
              //go() api/login
              alert("sorry to see you go")
              go(/login)

          }*/

}
