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
        var loadCompetencies = function() {
            CompetencyService.loadCompetencies(UserService.getCurrentUser(), function (data) {
                $scope.competencyList = data;
            });
        };

        this.init = function() {
            loadCompetencies();
            $scope.canCreate = UserService.canCreateOnThisPage();
        };

        this.create = function(name, descr) {
            CompetencyService.create(name, descr, $scope);
        }
    }
);

app.controller('ArticleController',
    function ($scope, UserService, CompetencyService) {
        var loadCompetencies = function() {
            CompetencyService.loadCompetencies(UserService.getCurrentUser(), function (data) {
                $scope.competencyList = data;
            });
        };

        this.init = function() {
            loadCompetencies();
            $scope.canCreate = UserService.canCreateOnThisPage();
        };

        this.create = function(name, descr) {
            CompetencyService.create(name, descr, $scope);
        }
    }
);