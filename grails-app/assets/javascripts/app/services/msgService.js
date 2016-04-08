/**
 * Created by nansak1 on 4/3/2016.
 */

app.service('msgService', function($http){

    var someMsg ={};
    var handle = {};

    var getMessages = function() {
        //return $http.get('/messages');
        return someMsg;
    };

    var setMessages = function(msgResults){
        someMsg = msgResults;
    };

    var searchMessages = function(searchText) {

       // if ($route.params) //determine if its by an account
        //{
        //var url = 'accounts/'+paramText+'/messages'
         //  return $http.get('/accounts/'+paramText+'/messages'); //search by user
        //}
        //else {
            return  $http.get("/messages/searchText", {params: {text: searchText}});  //search by message content
        //}

    };

    var searchMessagesbyPoster = function(accountHandle) {
        //handle = accountHandle;
        return $http.get('accounts/'+accountHandle +'/messages');

    };


    return {
        //getMessages : function () {return $http.get('/messages');},
        //searchMessages: function (paramText){return  $http.get("/messages/searchText", {params: {text: paramText}});}
        getMessages:getMessages,
        setMessages:setMessages,
        searchMessages: searchMessages,
        searchMessagesbyPoster : searchMessagesbyPoster

    };

});