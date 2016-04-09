/**
 * Created by nansak1 on 4/3/2016.
 */


app.service('accService', function($http){

    var handle ={};

   /* var getAllAccounts = function() {
        return $http.get('/accounts');
    };*/

    var setAccount = function(accountHandle){
        handle = accountHandle;
        return handle;
    }

    var getAccount = function() {
        return handle;
    };

    var findAccount = function(user) {
        return $http.get('/accounts/'+ user);
    };

    return {
        findAccount : findAccount,
        getAccount:getAccount,
        setAccount: setAccount
        //getAllAccounts : getAllAccounts
    };

});