/**
 * Created by nayna on 4/5/2016.
 */
app.controller('searchController', function ($scope, msgService, authService, accService, $location) {
    $scope.message = 'Search something';
    $scope.toggle = true;


    var user = authService.getUsername();
    var token = authService.getToken();
    $scope.aToken = token;
    $scope.isLoggedIn = user;

   // $scope.$watch($scope.isLoggedIn, function(isLoggedIn, aToken) {
     /*   if (!token){
            $location.path('/login');
            $scope.isLoggedIn = null;
            console.log( "No token:" + $scope.isLoggedIn)
        }
        else{
            $location.path('/search');
            $scope.isLoggedIn = user;
            console.log( "token found:" + $scope.isLoggedIn)
        }*/
    if (!user && !token){
        $location.path('/login');
        $scope.isLoggedIn = null;
        console.log( "No token:" + $scope.isLoggedIn)
    }
    else{
        //$location.path('/');
        $scope.isLoggedIn = user;
        console.log( "token found:" + $scope.isLoggedIn)
    }
   // });
    //authService.isLoggedIn(user);

    // var user = $scope.accountHandle

    console.log(user);
    //console.log(token);
    //console.log ($scope.text);

    // $scope.auth.token = authService.getToken();
    //$scope.auth.username = authService.getUsername()


    //to route to account poster's detail page
    $scope.getMessages = function(params){

        accService.setAccount(params);

        msgService.searchMessagesbyPoster(params)
            .then(function(response){
                msgService.setMessages(response.data);
                $scope.messages = response.data;
                //msgService.setMessages($scope.messages);
                console.log($scope.messages);
                return response.data;
            },
                function(error) {
                    console.log('error', error);
                });
    };


    // search by poster and text?
    $scope.searchMessages = function() {
        //var params = {text: $scope.text};
        console.log($scope.text);

        //var userExists = accService.getAccount($scope.text);
       // console.log(userExists);

       /* accService.getAccount($scope.text)
            .then(function(response){
                    $scope.account = response.data;
                    return response.data;
                },
                function (error) {
                    console.log('error', error);
                });

        console.log($scope.account);



        if (!$scope.account) { */ //is a username
            msgService.searchMessages($scope.text)
                .then(function (response) {
                        $scope.messages = response.data;
                        console.log($scope.messages);
                        return response.data;
                    },
                    function (error) {
                        console.log('error', error);
                    });
     /*  }
        else {

            msgService.searchMessagesbyPoster($scope.text)
                .then(function (response) {
                        $scope.messages = response.data;
                        console.log($scope.messages);
                        return response.data;
                    },
                    function (error) {
                        console.log('error', error);

                    });


        }*/


    };
    //console.log($scope.searchResults)


    //};

    //$scope.getMessage();
    //$scope.searchMessages();




});