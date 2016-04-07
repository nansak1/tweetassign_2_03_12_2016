/**
 * Created by nayna on 4/5/2016.
 */
app.controller('searchController', function ($scope, msgService, authService) {
    $scope.message = 'Search something';
    $scope.toggle = true;

    var user = authService.getUsername();
    $scope.aToken = authService.getToken();
    // var user = $scope.accountHandle

    console.log(user);
    //console.log(token);
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
    };

    $scope.searchMessages = function() {

        //var params = {text: $scope.text};
        console.log($scope.text);

        msgService.searchMessages($scope.text)
            .then(function(response){

                    $scope.messages = response.data;
                    console.log($scope.messages);
                    return response.data;
                },
                function(error) {
                    console.log('error', error);

                });

        msgService.searchMessagesbyPoster($scope.text)
            .then(function(response){

                    $scope.messages = response.data;
                    console.log($scope.messages);
                    return response.data;
                },
                function(error) {
                    console.log('error', error);

                });
    };
    //console.log($scope.searchResults)


    //};

    //$scope.getMessage();
    //$scope.searchMessages();




});