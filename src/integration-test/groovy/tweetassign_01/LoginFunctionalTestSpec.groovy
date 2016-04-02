package tweetassign_01

import geb.spock.GebSpec
import groovyx.net.http.RESTClient
import spock.lang.Shared

/**
 * Created by nansak1 on 4/1/2016.
 */
class LoginFunctionalTestSpec extends GebSpec{

    RESTClient restClient

    @Shared
    def token

    def setup(){
        restClient = new RESTClient(baseUrl)
    }


  /*  def 'L1: When not logged in, route user to the login screen '(){

        when:
        //account.username ="" and account.password=""
        //go() Not logged in

        then:
        //go(/api/login) api/login

    }

    def 'L2: Login screen allows a user to enter username and password to gain access '(){

        when:
        account.username !="" && account.password !=""
        //go() api.login

        then:
        //go() api/login
        respone

    }

    def 'L3: Invalid login will be rejected with an error message'(){

        when:
        //go() api.login

        then:
        //go() api/login
        respone

    }*/








}
