/**
 * Created by nansak1 on 4/3/2016.
 */

app.service('msgService', function($http){


    var getMessages = function() {
        return $http.get('/messages');

    };

    var searchMessages = function(paramText) {

       // if ($route.params) //determine if its by an account
        //{
        var url = 'accounts/'+paramText+'/messages'
           return $http.get(url, {params: {accountHandle: paramText}});
        //}
        //else {
            return  $http.get("/messages/searchText", {params: {text: paramText}});
        //}

    };

   /* var getMessagesbyPoster = function() {
        return $http.get('accounts/accounthandle/messages', {params:{"accountHandle": accountHandle}});

    }*/


    return {
        getMessages : function () {return $http.get('/messages');},
        //searchMessages: function (paramText){return  $http.get("/messages/searchText", {params: {text: paramText}});}
        searchMessages: searchMessages

    };

});