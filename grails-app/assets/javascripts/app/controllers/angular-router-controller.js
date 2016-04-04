/**
 * Created by nansak1 on 4/2/2016.
 */
app.controller('headerController', function ($scope, $location) {
    $scope.message = 'I manage the header, meaning the nav tabs';
    $scope.isActive = function (viewLocation) {
        return viewLocation == $location.path();
    };
});

/*app.controller('mainController', function ($scope, $location, attendeeService) {
    $scope.message = 'Default Routed Page (Home)';
    $scope.toggle = true;

    //$scope.auth ={};
    //$scope.auth.token = authService.getToken();
    //$scope.attendees = attendeeService.getAttendees();
    //$scope.attendeePage = function () {
    //    $location.path("/attendee");
    //};

    //$scope.deleteAttendee = function (id) {
    //    attendeeService.deleteAttendee(id)
   // };
});*/

app.controller('loginController', function ($scope, $location, $http, authService ) {

    $scope.message = 'Login Page';
    $scope.auth ={};
    $scope.auth.token = authService.getToken();

    $scope.Login = function(){

        var authentication = new Object();
        authentication.username = $scope.auth.username;
        authentication.password = $scope.auth.password;

        authService.setToken({});

        $http.post('api/login', authentication)

            .success(function (response) {
                authService.setUsername(response.username);
                authService.setToken(response.access_token);
                //redirect to user page
                //location.path("/accounts")
                //$scope.auth.roles = data.roles;
            })
            .error(function (error) {
                $scope.auth.authError = error;
            })
            .finally(function () {
                $scope.auth.token = authService.getToken();
                //redirect to user detail page of user in login
                $location.path("/accounts");
            })


    };


    //$scope.username = {};
});

app.controller('searchController', function ($scope, msgService) {
    $scope.message = 'Search something';
    $scope.toggle = true;
    //console.log ($scope.text);

   // $scope.auth.token = authService.getToken();
    //$scope.auth.username = authService.getUsername()

    $scope.getMessages = function(){

        msgService.getMessages()
            .then(function(response){
                $scope.messages = response.data;
                console.log($scope.messages);
                return response.data;
            });

       /* $http.get('/messages')
            .success(function(response){
            console.log("success: " + response.data);
            $scope.messages =  response.data;
        })
            .error(function(response){
            console.log("error:" + response.data);
            $scope.messages = response.data;
        });*/
    };

    $scope.searchMessages = function() {

        //var params = {"text": $scope.text};
        console.log($scope.text);

      /*   $http.get('/messages/searchText?text='+ $scope.text)
         .success(function(response){
         console.log("success: " + response.data);
         $scope.messages =  response.data;
         })
         .error(function(response){
         console.log("error:" + response.data);
         $scope.messages = response.data;
         });*/


        msgService.searchMessages($scope.text)
            .then(function(response){

                $scope.messages = response.data;
                console.log($scope.messages);
                return response.data;
            })


   };

    //$scope.getMessage();
    //$scope.searchMessages();




});

app.controller('accountController', function($scope, accService){

    accService.getAccounts()
        .then(function(response){
            $scope.accounts = response.data;
            return response.data;
        })
});

/*app.controller('attendeeController', function ($scope, $location, $routeParams, attendeeService) {
    $scope.attendee = {};
    $scope.mode = 'Add';
    if ('edit' == $routeParams.action) {
        $scope.mode = 'Edit';
        var id = $routeParams.id;
        var attendees = attendeeService.getAttendees();
        for (i = 0; i < attendees.length; i++) {
            if (attendees[i].id == id) {
                $scope.attendee = attendees[i];
            }
        }
    }

    $scope.saveCurrentAttendee = function () {
        if ($scope.attendee.first && $scope.attendee.last) {
            if ($scope.attendee.id) {
                attendeeService.updateAttendee($scope.attendee);
            } else {
                attendeeService.addAttendee($scope.attendee);
            }
            attendeeService.attendee = {};
            $location.path("/home");
        }
    };

    $scope.message = 'Wire up controller in html (Not really good practice)';

    $scope.return = function () {
        $location.path("/home");
    };
});
*/