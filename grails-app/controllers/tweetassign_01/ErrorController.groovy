package tweetassign_01

import grails.converters.JSON

class ErrorController {

   /* def index() {}*/

    def internalServerError(){
        response.status = 500
        render ([error: response.status, mesage: "Internal server error"]) as JSON
        /*render(contentType:'application/json'){
            error = response.status
            message ='Internal server error'
        }*/
    }

    def notFound(){
        response.status = 404
        render ([error: response.status, message:"Not found"]) as JSON
        /*render(contentType:'application/json'){
            error = reponse.status
            message ='Not found'
        }*/
    }

    def unauthorized() {
        response.status = 401
        render ([error:response.status, message: "Unauthorized"]) as JSON
       /* render(contentType: 'application/json') {
            error = response.status
            message = 'Unauthorized'
        }*/
    }

    def forbidden() {
        response.status = 403

        render ([error: response.status, message: "Forbidden"]) as JSON
        /*render(contentType: 'application/json') {
            error = response.status
            message = 'Forbidden'
        }*/
    }
}

