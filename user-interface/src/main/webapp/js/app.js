/**
 * Created by is_competent on 03.06.2017.
 */
var app = angular.module('distLearning', ['ngRoute', 'ngCookies', 'distLearning.controllers', 'distLearning.services']);

// Routing config
app.config(function ($routeProvider) {
    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'views/login.html'
        })
        .when('/profile', {
            controller: 'ProfileController',
            templateUrl: 'views/profile.html'
        })
        .when('/subjects', {
            controller: 'SubjectController',
            templateUrl: 'views/subjects.html'
        })
        .when('/competency', {
            controller: 'CompetencyController',
            templateUrl: 'views/competency.html'
        })
        .when('/article', {
            controller: 'ArticleController',
            templateUrl: 'views/article.html'
        })
    ;
    //.otherwise({redirectTo: '/login'});
});
