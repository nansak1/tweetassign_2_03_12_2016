/**
 * Created by nansak1 on 4/1/2016.
 */
app.config(function ($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: '/app/home.htm',
            controller: 'mainController'
        })
        .when('/about', {
            templateUrl: '/app/about.html',
            controller: 'aboutController'
        })
        .when('/contact', {
            templateUrl: '/app/contact.html',
            controller: 'contactController'
        })
        .when('/attendee/:action?/:id?', {
            templateUrl: 'twtr/partials/attendee.html'
        })
        .otherwise({
            redirectTo: '/home'
        });
});

/*angular.module("app").config(function($routeProvider){

$route.when("/logout",{
    templateUrl:"/views/logout.html"


});

    $route.when("/login",{
        templateUrl:"/views/login.html"

    });

    $route.when("/accounts",{
        templateUrl:"/views/accounts.html"

    });

    $route.when("/home",{
        templateUrl:"/app/home.html",
        controller:'mainController'

    });


});*/