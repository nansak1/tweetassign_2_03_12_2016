/**
 * Created by nansak1 on 4/5/2016.
 */


//app.controller('authController', function ($scope, $location, $http, authService ) {
app.controller('authController', ['$scope', 'authService', '$location', function ($scope, authService, $location) {

    //$scope.loggedInUser = null;

    $scope.Login = function () {
        authService.Login($scope.accountHandle, $scope.accountPassword)
            .then(function(response) {

                authService.setCredentials(response.data.username);
                authService.setToken(response.data.access_token);
                $scope.aToken = authService.getToken();
                $scope.loggedInUser = authService.getUsername();

                console.log($scope.aToken);
                $location.path('/home');
            },
            function() {
                $scope.error = "Wrong credentials";
                console.log($scope.error);

            });
        };

        $scope.destroyToken = function(){
            $scope.loggedInUser = null;
            alert("Sorry to see you go...");
            //console.log($scope.text);
            $location.path('/');
            authService.destroyToken();
            console.log("User logged out and token destroyed");
               /* .then(function(response){

                        //$scope.messages = response.data;
                        console.log("User logged out and token destroyed");
                        return response.data;

                    },
                    function(error) {
                        console.log('error', error);

                    });*/

        };

    /*$scope.$watch($scope.isLoggedIn, function(user, token) {
        if (!user && !token){
            $location.path('/login');
            $scope.isLoggedIn = null;
            console.log( "No token:" + $scope.isLoggedIn)
        }
        else{
            $location.path('/home')
            $scope.isLoggedIn = user;
            console.log( "token found:" + $scope.isLoggedIn)
        }
   });*/

    //$scope.isLoggedIn = authService.isLoggedIn();
    //function to keep track of login or logout

 /*   $scope.$watch(authService.isLoggedIn, function (value, oldValue) {
        token = authService.getToken
        console.log (token);
        //console.log(oldValue);
        //if(!value && oldValue)
        if(!value) {
            console.log("Disconnect");
            $location.path('/login');
        }
        if(value) {
            console.log("Connect");
            $scope.loggedInUser = value;
            $location.path('/home');
            //Do something when the user is connected
        }

    }, true);*/

}]);