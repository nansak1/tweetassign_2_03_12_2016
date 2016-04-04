/**
 * Created by nansak1 on 4/3/2016.
 */

app.service('msgService', function($http, $routeParams){


    var getMessages = function() {
        return $http.get('/messages');

    }

    var searchMessages = function() {
       // $scope.searchResults = $http.get('/messages/searchText', {params:{"text": text}});
        return $http({
            url: "/messages/searchText",
            method: "GET",
            params: {text: $routeParams.text}
        });

    }

   /* var getMessagesbyPoster = function() {
        return $http.get('accounts/accounthandle/messages', {params:{"accountHandle": accountHandle}});

    }*/


    return {
        getMessages : function () {return $http.get('/messages');},
        searchMessages : function () {return $http({
            url: "/messages/searchText",
            method: "GET",
            params: {text: $routeParams.text}
        });}, //search params
        //getMessagesbyPoster : function(){return $http.get('/messages/searchText');}
    };

});