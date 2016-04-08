/**
 * Created by nayna on 4/5/2016.
 */
app.controller('accountController', function($scope, accService, authService, msgService, $location){

    var user = authService.getUsername();
    var token = authService.getToken();

    console.log("in acc controller" + user);
    $scope.aToken = token;
    $scope.isLoggedIn;
    $scope.accounts = accService.getAccount();

     $scope.messages = msgService.getMessages($scope.accounts); //to display messages

    console.log($scope.messages);
    //console.log($scope.accounts);

    if (!token){
        $location.path('/login');
        $scope.isLoggedIn = null
    }
    else{
        $location.path('/details');
        $scope.isLoggedIn = user;
    }

    /*if (!user && !token){
        $location.path('/login');
        $scope.isLoggedIn = null
    }
    else{
        $location.path('/details');
        $scope.isLoggedIn = user;
    }*/


   // var user = authService.getUsername();
   // var token = authService.getToken();
   // var user = $scope.accountHandle

    console.log(user);
    console.log(token);

  /*  accService.getAllAccounts()
        .then(function(response){
            $scope.accounts = response.data;
            return response.data;
        },
            function (error) {
        console.log('error', error);
    });*/


    accService.findAccount(user)
        .then(function(response){
            $scope.accounts = response.data;
            return response.data;
        },
            function (error) {
                console.log('error', error);
            })
});