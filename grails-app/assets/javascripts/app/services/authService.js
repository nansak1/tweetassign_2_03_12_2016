/**
 * Created by nansak1 on 4/5/2016.
 */


app.service('authService', function($http){
    var username ={};
    var authToken ={};

    var Login = function (accountHandle, accountPassword) {
        return $http.post('/api/login', {username: accountHandle, password: accountPassword })
    };
    var isLoggedIn = function(){
        return (username)? username : false;
    };


    var getUsername = function(){
        return username
    };

    var setCredentials = function(accountHandle){
        username = accountHandle
    };
    var setToken = function(token){
        authToken = token
    };

    var getToken = function(){
        return authToken
    };

    var destroyToken = function(){
        authToken = null;
        username = null;
        //return authToken;
        //return username;
    };


    return {
        Login : Login,
        isLoggedIn:isLoggedIn,
        setCredentials: setCredentials,
        getUsername: getUsername,
        setToken : setToken,
        getToken: getToken,
        destroyToken:destroyToken
        };


});