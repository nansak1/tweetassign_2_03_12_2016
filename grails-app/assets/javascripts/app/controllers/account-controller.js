/**
 * Created by nayna on 4/5/2016.
 */
app.controller('accountController', function($scope, accService, authService, msgService, $location, $routeParams){

    var currentUser = authService.getUsername();
    var currentUserInfo =  accService.getUserProfile();
    var token = authService.getToken();
    var poster = $routeParams.handle;

    console.log("Logged in User in acc controller " + currentUser);
    console.log("Poster in acc controller " + $routeParams.handle);
    console.log("User id of logged in user in acc controller " + currentUserInfo.id);
    console.log("following of logged in user in acc controller " + currentUserInfo.following);



    //auth stuff
    if (!token && !currentUser){
        $location.path('/login');
       // $scope.isLoggedIn = null
        console.log("No token in acc:" + $scope.isLoggedIn);
    }
    else{

        var anAccount = !poster ? currentUser : poster;
        //$location.path('/');
        $scope.isLoggedIn = currentUser;
        console.log("token found in acc:" + $scope.isLoggedIn);
    }

    //is the user the current user or the poster

    $scope.aToken = token;
    $scope.isLoggedIn = currentUser;
    $scope.poster = poster;
    $scope.state = "Follow"
    $scope.editorEnabled = false;

    console.log("Logged in User in acc controller " + currentUser);
    console.log("Poster in acc controller " + $routeParams.handle);
    console.log("User id of logged in user in acc controller " + currentUserInfo.id);
    console.log("following of logged in user in acc controller " + currentUserInfo.following);
    //var currentUserFollowing = currentUserInfo.following;
    //console.log(currentUserFollowing);
    //console.log(currentUserFollowing.indexOf(poster))
    //console.log($scope.Following);
    console.log("Logged in user: " + currentUser);

    //if current user is already following poster follow button should say "following"
    if (poster && (poster!= currentUser)){
        var currentUserFollowing = currentUserInfo.following;
        if (currentUserFollowing.indexOf(poster) == -1) {
            $scope.state = "Follow";
            console.log($scope.state);
        }
        else {
            $scope.state = "Following";
        }
    }

    //function ClickToEditCtrl($scope){
        //$scope.accounts;
    //}

    //console.log("Posting in user: " + $routeParams);





//to get followers
    accService.accountsFollowing(currentUser)
    //$scope.Following = function(poster, currentUserInfo){
        .then(function(response)
        {
        var currentUserFollowing = response.data.following;
            console.log(currentUserFollowing);
            console.log(poster);
        },function(error){

            console.log('error', error);
    });

   // }

//edit stuff


//display messages by user
    msgService.searchMessagesbyPoster(anAccount)
        .then(function(response){
            $scope.messages = response.data;
            return response.data;
        },
        function (error) {
            console.log('error', error);
        });


    //display logged in user info
    accService.findAccount(anAccount, token)
        .then(function(response){
                $scope.accounts = response.data;
                // currentUserId = $scope.accounts.id;
                return response.data;
            },
            function (error) {
                console.log('error', error);
            });




    $scope.Follow = function(posterId){
        accService.followAccount(currentUserInfo.id, posterId)
            .then(function(response){
                    $scope.accounts = response.data;
                    $scope.state ="Following";
                    console.log("current user following poster");
                    return response.data;
                },
                function (error) {
                    console.log('error', error);
                });


    };

    $scope.saveDetails = function(fullName, emailAddress)
    {
        /*$scope.editorEnabled = true;
        console.log($scope.editorEnabled);
        var updatedCredentials = {
            fullName: $scope.accounts.fullName,
            emailAddress: $scope.accounts.emailAddress,
        };*/


    };

  /*  $('button.followButton').live('click', function(e){
        e.preventDefault();
        $button = $(this);
        if($button.hasClass('following')){

//$.ajax(); Do Unfollow

            $button.removeClass('following');
            $button.removeClass('unfollow');
            $button.text('Follow');
        } else {

            // $.ajax(); Do Follow

            $button.addClass('following');
            $button.text('Following');
        }
    });

    $('button.followButton').hover(function(){
        $button = $(this);
        if($button.hasClass('following')){
            $button.addClass('unfollow');
            $button.text('Unfollow');
        }
    }, function(){
        if($button.hasClass('following')){
            $button.removeClass('unfollow');
            $button.text('Following');
        }
    });*/

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