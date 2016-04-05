/**
 * Created by nayna on 4/5/2016.
 */
app.controller('accountController', function($scope, accService, loginService){

    var user = loginService.getUsername();
    accService.getAccounts(user)
        .then(function(response){
            $scope.accounts = response.data;
            return response.data;
        })
});