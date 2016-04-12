/**
 * Created by nansak1 on 4/1/2016.
 */
app.config(function ($routeProvider) {
    $routeProvider
        .when('/login', {
            templateUrl: '/app/login.htm',
            controller: 'authController'
        })
        .when('/home', {
            templateUrl: '/app/welcome.htm',
            controller: 'mainController'
        })
        .when('/details', {
            templateUrl: '/app/account.htm',
            controller: 'accountController'
        })
        .when('/details/:handle?', {
            templateUrl: '/app/account.htm',
            controller: 'accountController'
        })
        .when('/search', {
            templateUrl: '/app/search.htm',
            controller: 'searchController'
        })
        /* .when('/search:text', {
         templateUrl: '/app/search.htm',
         controller: 'searchController'
         })
        .when('/attendee/:action?/:id?', {
            templateUrl: 'twtr/partials/attendee.htm'
        })*/
        .otherwise({
            redirectTo: '/login'
        })

});

//shows nav bar only when logged in
app.run(function($rootScope, $location) {
    $rootScope.location = $location;

    //auth token
    //$http.defaults.headers.common.['X-auth-token'] = token;
});
    // Protect all routes other than login
   /* .run(function ($rootScope, $location, authService) {
        $rootScope.$on('$routeChangeStart', function (event, next) {
            if (next.$$route.originalPath != '/login') {
                if (!authService.getUsername()) {
                    $location.path('/login');
                }
            }
        });
});*/






