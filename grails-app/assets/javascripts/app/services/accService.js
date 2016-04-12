/**
 * Created by nansak1 on 4/3/2016.
 */


app.service('accService', function($http){

    var handle ={};
    var currentUserProfile ={};

   /* var getAllAccounts = function() {
        return $http.get('/accounts');
    };*/

    var setAccount = function(accountHandle){
        handle = accountHandle;
        return handle;
    };

    var followAccount = function(currentUser, poster){
        return $http.put("/accounts/"+ poster +"/follow?follower="+currentUser);
    };

    var accountsFollowing = function(currentUser){
        return $http.get("/accounts/"+ currentUser +"/followers");
    };

    var setUserProfile = function(currentUser, token){

        console.log(currentUser);
        console.log(token);
        $http.defaults.headers.post["Content-Type"] = "application/json";
        //$http.get("/accounts/"+ currentUser)
        return $http({
            url: '/api/accounts/'+ currentUser,
            method: "GET",
            headers: {'X-Auth-Token': token.toString()}
    } )
            .then(function(response){
                    currentUserProfile = response.data;
                },
                function(error) {
                    console.log('error', error);

                })

    };


    var getUserProfile = function(){
        //console.log(currentUserProfile);
        return currentUserProfile;
    };

    var getAccount = function() {
        return handle;
    };

    var findAccount = function(user, token) {
        $http.defaults.headers.post["Content-Type"] = "application/json";
        //return $http.get('/accounts/'+ user);

        return $http({
            url: '/api/accounts/'+user,
            method: "GET",
            headers: {'X-Auth-Token': token.toString()}
        });
    };

    return {
        findAccount : findAccount,
        setUserProfile: setUserProfile,
        getUserProfile:getUserProfile,
        getAccount:getAccount,
        setAccount: setAccount,
        followAccount:followAccount,
        accountsFollowing:accountsFollowing
        //getAllAccounts : getAllAccounts
    };

});