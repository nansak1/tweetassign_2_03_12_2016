/**
 * Created by nayna on 4/5/2016.
 */
app.controller('accountController', function($scope, accService, authService, msgService, $location, $routeParams){

    var user = authService.getUsername();
    var token = authService.getToken();

   // var poster = accService.getAccount($routeParams);

    console.log("in acc controller " + user);
    console.log("in acc controller " + $routeParams.handle);

    if (!$routeParams.handle){
        user = user;
    }
    else {
        user = $routeParams.handle;
    }


    $scope.aToken = token;
    $scope.isLoggedIn = user;
    console.log("Logged in user: " + user);
    //console.log("Posting in user: " + $routeParams);



//auth stuff
  /*  if (!token){
        $location.path('/login');
        $scope.isLoggedIn = null
    }
    else{
        $location.path('/details');
        $scope.isLoggedIn = user;
    }*/

//logic to determine logged in user or posting user




//edit stuff


//display messages by user
    msgService.searchMessagesbyPoster(user)
        .then(function(response){
            $scope.messages = response.data;
            return response.data;
        },
        function (error) {
            console.log('error', error);
        });


    //display logged in user info
    accService.findAccount(user)
        .then(function(response){
                $scope.accounts = response.data;
                return response.data;
            },
            function (error) {
                console.log('error', error);
            });



    //follow stuff


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


  /*  accService.getAllAccounts()
        .then(function(response){
            $scope.accounts = response.data;
            return response.data;
        },
            function (error) {
        console.log('error', error);
    });*/



});