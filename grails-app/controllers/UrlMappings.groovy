import tweetassign_01.Message
import tweetassign_01.MessageController

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/accounts" (resources:'account')
        //"/messages" (resources:'message')
        //"/accounts/${accountId}/messages" (controller: 'message', action: "index", method: "GET")
        //"/accounts/$accountHandle/messages" (controller: 'message', action: "createResource", method: "POST")
       // "/accounts/$accountId/messages" (controller: 'message', action: "createResource", method: "POST")
       // "/accounts/$accountId/messages" (controller: 'message', action: "show", method: "GET")
        "/accounts"(resources:'account'){
            "/messages" (resources:'message')}
        "/messages/searchText" (controller: 'message', action: "searchText", method: "GET")
        "/accounts/$accountId/followers"(controller: 'account', action: 'showFollower',method:"GET")
        "/accounts/$accountId/recentFollowerMsg"(controller: 'account', action: 'showMostRecentMessage',method:"GET")
        //"/accounts/$accountId/messages"(controller: 'message', action: "show", method: "GET")

        "/accounts/$accountId/follow"(controller:'account', action:'follow')



        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        /*
    "500"(controller: 'Error', action: 'internalServerError')
    "404"(controller: 'Error', action: 'notFound')
    "401"(controller: 'Error', action: 'unauthorized')
    "403"(controller: 'Error', action: 'forbidden')
    */
    }
}
