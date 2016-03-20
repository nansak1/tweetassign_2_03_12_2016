package tweetassign_01

class ErrorController {

   /* def index() {}*/

    def internalServerError(){
        reponse.status =500
        render(contentType:'application/json'){
            error = response.status
            message ='Internal server error'
        }
    }

    def notFound(){
        reponse.status = 404
        render(contentType:'application/json'){
            error = reponse.status
            message ='Not found'
        }
    }

    def unauthorized() {
        response.status = 401
        render(contentType: 'application/json') {
            error = response.status
            message = 'Unauthorized'
        }
    }

    def forbidden() {
        response.status = 403
        render(contentType: 'application/json') {
            error = response.status
            message = 'Forbidden'
        }
    }
}

