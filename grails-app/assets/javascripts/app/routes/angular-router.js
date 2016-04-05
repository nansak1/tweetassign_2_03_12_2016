/**
 * Created by nansak1 on 4/1/2016.
 */
app.config(function ($routeProvider) {
    $routeProvider
        .when('/login', {
            templateUrl: '/app/login.htm',
            controller: 'loginController'
        })
        .when('/details', {
            templateUrl: '/app/account.htm',
            controller: 'accountController'
        })
        .when('/search', {
            templateUrl: '/app/search.htm',
            controller: 'searchController'
        })
        .when('/attendee/:action?/:id?', {
            templateUrl: 'twtr/partials/attendee.htm'
        })
        .otherwise({
            redirectTo: '/login'
        });
});

