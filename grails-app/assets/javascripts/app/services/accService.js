/**
 * Created by nansak1 on 4/3/2016.
 */


app.service('accService', function($http){


    var getAccounts = function() {
        return $http.get('/accounts');

    }


    return {
        getAccounts : function () {
            return $http.get('/accounts');
        }
    };

});