var app = angular.module('distLearning.controllers', []);

app.controller('LoginController',
    function ($scope, LoginService) {
        this.login = function (username, password) {
            LoginService.login(username, password);
        };

        this.logout = function () {
            LoginService.logout();
        }
    }
);

app.controller('ProfileController',
    function ($scope, UserService) {
        this.init = function () {
            $scope.menuItems = UserService.generateMenu();
        }
    }
);

app.controller('SubjectController',
    function ($scope, UserService, SubjectService) {
        var loadSubjects = function() {
            SubjectService.loadSubjects(UserService.getCurrentUser(), function (data) {
                $scope.subjectList = data;
            });
        };

        this.init = function() {
            loadSubjects();
            $scope.canCreate = UserService.canCreateOnThisPage();
        };

        this.create = function(name) {
            SubjectService.create(name, $scope);
        }
    }
);

app.controller('CompetencyController',
    function ($scope, UserService, CompetencyService) {
        this.loadCompetencies = function() {
            CompetencyService.loadCompetencies(UserService.getCurrentUser(), function (data) {
                $scope.competencyList = data;
            });
        };

        this.init = function() {
            this.loadCompetencies();
            $scope.canCreate = UserService.canCreateOnThisPage();
        };

        this.create = function(name, descr) {
            CompetencyService.create(name, descr, $scope);
        }
    }
);

app.controller('ArticleController',
    function ($scope, UserService, ArticleService, CompetencyService) {
        var loadArticles = function() {
            ArticleService.loadArticles(UserService.getCurrentUser(), function (data) {
                $scope.articleList = data;
            });
        };

        this.init = function() {
            CompetencyService.loadCompetencies(UserService.getCurrentUser(), function (data) {
                $scope.competencyList = data;
            });
            loadArticles();
        };

        this.create = function(targetCompetency, title, editorId) {
            var articleText = $('#' + editorId).trumbowyg('html');
            ArticleService.create(targetCompetency, articleText, title, UserService.getCurrentUser(), $scope);
        }
    }
);

app.controller('TestController',
    function ($scope, UserService, TestService, SubjectService) {

        var loadTests = function() {
            TestService.loadTests(UserService.getCurrentUser(), function (data) {
                $scope.testList = data;
            });
        };

        this.init = function() {
            SubjectService.loadSubjects(UserService.getCurrentUser(), function (data) {
                $scope.subjectList = data;
            });

            $scope.canCreate = UserService.canCreateOnThisPage();
            loadTests();
        };

        this.create = function(test, subjId) {
            TestService.create(test.subject, subjId, test.maxQuestion, test.maxMark, $scope);
        }
    }
);

app.controller('TestQuestionController',
    function ($scope, $routeParams, UserService, TestQuestionService, SubjectService) {
        var testId = $routeParams.testId;

        var loadTestQuestions = function() {
            TestQuestionService.loadTestQuestions(UserService.getCurrentUser(), testId, function (data) {
                $scope.testQuestionList = data;
            });
        };

        this.init = function() {
            $scope.canCreate = UserService.canCreateOnThisPage();
            loadTestQuestions();
        };

        this.create = function(questionText, correct, options) {
            TestQuestionService.create(questionText, correct, options, testId, $scope);
        }
    }
);