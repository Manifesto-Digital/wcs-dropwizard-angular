'use strict';

var AngularWcsApp = {};

var App = angular.module('AngularWcsApp', ['ui.bootstrap']);

App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/articles', {
        templateUrl: 'articles/layout.html',
        controller: ArticleController
    });
   
    
    $routeProvider.otherwise({redirectTo: '/articles'});
}]);
