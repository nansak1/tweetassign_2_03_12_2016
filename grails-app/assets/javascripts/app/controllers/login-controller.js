/**
 * Created by nayna on 4/5/2016.
 */
app.controller('loginController', function ($scope, $location, $http, loginService ) {

    $scope.message = 'Login Page';
    $scope.auth ={};
    $scope.auth.token = loginService.getToken();

    $scope.Login = function(){

        var authentication = new Object();
        authentication.username = $scope.auth.username;
        authentication.password = $scope.auth.password;

        loginService.setToken({});

        $http.post('api/login', authentication)

            .success(function (response) {
                loginService.setUsername(response.username);
                loginService.setToken(response.access_token);
                //redirect to user page
                //$location.path("/accounts/"+response.username);
                //$scope.auth.roles = data.roles;
            })
            .error(function (error) {
                $scope.auth.authError = error;
            })
            .finally(function () {
                $scope.auth.token = loginService.getToken();
                //redirect to user detail page of user in login
                $location.path("/accounts");
            })


    };
});