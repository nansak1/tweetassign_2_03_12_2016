/**
 * Created by nansak1 on 4/3/2016.
 */

app.service('msgService', function($http){


   /* var getMessages = function() {
        return $http.get('/messages');

    };*/

    var searchMessages = function(paramText) {

       // if ($route.params) //determine if its by an account
        //{
        //var url = 'accounts/'+paramText+'/messages'
         //  return $http.get('/accounts/'+paramText+'/messages'); //search by user
        //}
        //else {
            return  $http.get("/messages/searchText", {params: {text: paramText}});  //search by message content
        //}

    };

    var searchMessagesbyPoster = function(paramText) {
        return $http.get('accounts/'+paramText +'/messages');

    }


    return {
        //getMessages : function () {return $http.get('/messages');},
        //searchMessages: function (paramText){return  $http.get("/messages/searchText", {params: {text: paramText}});}
        searchMessages: searchMessages,
        searchMessagesbyPoster : searchMessagesbyPoster

    };

});