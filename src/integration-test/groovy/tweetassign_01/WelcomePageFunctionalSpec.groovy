package tweetassign_01

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration


/**
 * Created by nansak1 on 3/28/2016.
 */
@Integration
class WelcomePageFunctionalSpec extends GebSpec{

    def 'welcome page displays welcome message'() {
        when:
        go '/'

        then: 'Static welcome displayed properly'
        $('h1').first().text() == 'Welcome to the sample Grails 3 Angular App'

        and: 'Angular generated test displayed properly'
        $('h2').first().text() == 'Hello Stranger'
    }
}
