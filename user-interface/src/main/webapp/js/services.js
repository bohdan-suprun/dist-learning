/**
 * Created by is_competent on 03.06.2017.
 */

var app = angular.module('distLearning.services', []);

app.service('LoginService', function ($http, $location, $cookieStore) {
    var loginUrl = '/dist-learning/login';
    var logoutUrl = '/dist-learning/logout';
    var profilePath = '/profile';
    var loginPath = '/login';

    var dummyUser = {
        name: 'Test',
        role: '',
        email: ''
    };

    this.login = function (username, password) {
        if (username.includes('admin')) {
            dummyUser.role = 'ADMIN';
        } else if (username.includes('teacher')) {
            dummyUser.role = 'TEACHER';
        } else {
            dummyUser.role = 'STUDENT';
        }

        dummyUser.email = username;
        $cookieStore.put('currentUser', dummyUser);
        $location.path(profilePath);


        //$http.post(loginUrl, {password: password, username: username})
        //    .success(function () {
        //        $location.path(profilePath);
        //    }).error(function () {
        //        alert('Error');
        //        $location.path(profilePath);
        //    });
    };

    this.logout = function () {
        $cookieStore.remove("currentUser");
        $http.post(logoutUrl);

        $location.path(loginPath);

    };
});

app.service('UserService', function ($http, $cookieStore, $location) {

    this.generateMenu = function () {
        var user = this.getCurrentUser();

        return MENU[user.role.toLowerCase()];
    };

    this.getCurrentUser = function () {
        return $cookieStore.get('currentUser');
    };

    this.canCreateOnThisPage = function () {
        var user = this.getCurrentUser();
        var url = $location.path();

        var paths = ACCESS_MAP[user.role.toLowerCase()];
        for (var index in paths) {
            if (url.endsWith(paths[index])) {
                return true;
            }
        }

        return false;
    }
});

app.service('SubjectService', function ($http, $location) {
    this.loadSubjects = function (user, callback) {
        var url = "/dist-learning/subject/";
        if (user.role === USER_STUDENT) {
            url += user.id;
        }

        callback(SUBJECT);
        //$http.get(url)
        //    .success(function (data) {
        //        callback(data);
        //    }).error(function () {
        //    });
    };

    this.create = function (name, $scope) {
        var newId = Number($scope.subjectList[$scope.subjectList.length - 1].id) + 1;
        $scope.subjectList.push({"name": name, "id": newId});
    }
});

app.service('CompetencyService', function ($http, $location) {
    this.loadCompetencies = function (user, callback) {
        var url = "/dist-learning/competency/";

        if (user.role.toLowerCase() == USER_STUDENT) {
            callback(getSubscriptions(user.email))
        } else {
            callback(COMPETENCY);
        }
        //$http.get(url)
        //    .success(function (data) {
        //        callback(data);
        //    }).error(function () {
        //    });
    };

    this.create = function (name, descr, $scope) {
        var newId = Number($scope.competencyList[$scope.competencyList.length - 1].id) + 1;
        $scope.competencyList.push({"name": name, "id": newId, "description": descr});
    };

    function getSubscriptions(userEmail) {
        for (var i in SUBSCRIPTIONS) {
            if (SUBSCRIPTIONS[i].email == userEmail) {
                return SUBSCRIPTIONS[i].competencies;
            }
        }

        return [];
    }
});