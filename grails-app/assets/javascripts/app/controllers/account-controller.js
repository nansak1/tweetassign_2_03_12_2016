/**
 * Created by nayna on 4/5/2016.
 */
app.controller('accountController', function($scope, accService, authService){


    var user = authService.getUsername();
    var token = authService.getToken();
   // var user = $scope.accountHandle

    console.log(user);
    console.log(token);

    accService.getAllAccounts()
        .then(function(response){
            $scope.accounts = response.data;
            return response.data;
        })


    /*accService.getAccounts(user)
        .then(function(response){
            $scope.accounts = response.data;
            return response.data;
        })*/
});