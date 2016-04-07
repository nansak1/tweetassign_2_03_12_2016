package tweetassign_01

import geb.spock.GebSpec
import groovyx.net.http.RESTClient
//import spock.lang.Shared

/**
 * Created by nansak1 on 4/1/2016.
 */
class LoginFunctionalTestSpec extends GebSpec{

    RESTClient restClient

    //@Shared
    //def token

    def setup(){
        restClient = new RESTClient(baseUrl)
    }

    def 'L1: When not logged in, route user to the login screen '(){
        when:
        go'/'   //Not logged in
        then:
        $(".page-header").text() == "Login"
    }

    def 'L2: Login screen allows a user to enter username and password to gain access '(){
        when:
        go '/api/login'
        then:
        $(".page-header").text() == "Login"
        when:
        $("#login-form input[name=username]").value("richelliot")
        $("#login-form input[name=password]").value("msse2016ASSIGN")
        $("#login-form input[type=submit]").click()
        then:
        $(".page-header").text() == "Greetings!!"
    }

    def 'L3: Invalid login will be rejected with an error message'(){
        when:
        go '/api/login'
        then:
        $(".page-header").text() == "Login"
        when:
        $("#login-form input[name=username]").value("blue")
        $("#login-form input[name=password]").value("msse2016ASSIGN")
        $("#login-form input[type=submit]").click()
        then:
        $(".page-header").text() == "Login"
        $(".errors li").size() == 1
        $(".errors li")[0].text() == "Wrong credentials"
    }
}
