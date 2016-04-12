<!doctype html>
<html ng-app="app">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application.css"/>
</head>


<body> <!--ng-controller="authController">-->
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">twtr</a>
           <!-- <li ng-class="{ active: isActive('/login')}" ng-click="destroyToken()"><a href="#">Logout</a></li>-->

           <!--ng-show="isLoggedIn"--> <!-- TO DO: Show if user is logged in-->
            <ul class="nav navbar-nav navbar-right" >
                <li id="logout" ng-class="{ active: isActive('/login')}" ng-click="destroyToken()"><a href="#logout">Logout</a></li>
                <li id="search" ng-class="{active: isActive('/search')}"><a href="#/search">Search</a></li>

            <!--<form ng-controller="searchController">
                <div class="form-group">
                    <li ng-class="{ active: isActive('/search')}"><a href="#/search"><label for="searchInput">Search</label></a></li>
                    <input id="searchInput" class="form-control" placeholder="searchTerm" type="text" name="text" ng-model="text"/>
                    <button ng-click="searchMessages()">Search</button>
                </div></form>-->

                <li id="details" ng-class="{ active: isActive('/details')}"><a href="#/details">Details</a></li>
            </ul>
        </div>
        <!--</div>-->
</div>
</nav>

<div ng-view></div>

<!--<footer class="jumbotron text-center">
    <p>Footer Content</p>
</footer>-->

<div ng-model="isLoggedIn">
    {{isLoggedIn}}
</div>

<div ng-model="aToken">
    {{ aToken }}
</div>

</body>
</html>