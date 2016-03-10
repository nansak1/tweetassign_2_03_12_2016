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
        "/accounts"(resources:'account'){
            "/messages" (resources:'message')}
        //"/accounts/$accountId/messages"(controller: 'message', action: "show", method: "GET")

        "/accounts/$accountId/follow"(controller:'account', action:'follow')



        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
