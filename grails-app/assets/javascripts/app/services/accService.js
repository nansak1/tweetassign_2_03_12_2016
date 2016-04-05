/**
 * Created by nansak1 on 4/3/2016.
 */


app.service('accService', function($http){


    var getAccounts = function(user) {
        return $http.get('/accounts/' + user);

    }


    return {
        getAccounts : function (user) {
            return $http.get('/accounts/'+user);
        }
    };

});