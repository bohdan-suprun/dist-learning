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

        var paths = ACCESS_MAP[user.role.toLowerCase()].create;
        for (var index in paths) {
            if (url.startsWith('/' + paths[index])) {
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
            callback(this.getSubscriptions(user.email))
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

    this.getSubscriptions =
        function (userEmail) {
            for (var i in SUBSCRIPTIONS) {
                if (SUBSCRIPTIONS[i].email == userEmail) {
                    return SUBSCRIPTIONS[i].competencies;
                }
            }

            return [];
        }
});

app.service('ArticleService', function ($http, $location, CompetencyService) {
    this.loadArticles = function (user, callback) {
        var url = "/dist-learning/article/";
        var articles = [];
        var subscriptions = CompetencyService.getSubscriptions(user.email);

        for (var i in subscriptions) {
            for (var j in ARTICLES) {
                if (subscriptions[i].id == ARTICLES[j].targetCompetency) {
                    articles.push(ARTICLES[j]);
                }
            }
        }

        callback(articles);
        //$http.get(url)
        //    .success(function (data) {
        //        callback(data);
        //    }).error(function () {
        //    });
    };

    this.create = function (targetCompetency, articleText, title, user, $scope) {
        var newId = Number($scope.articleList[$scope.articleList.length - 1].id) + 1;

        $scope.articleList.push(
            {
                "targetCompetency": targetCompetency,
                "id": newId,
                "author": user.email,
                "title": title,
                "text": articleText
            });
    };
});

app.service('TestService', function ($http, $location) {
    this.loadTests = function (user, callback) {
        //var url = "/dist-learning/article/";
        //
        callback(TESTS);
        //$http.get(url)
        //    .success(function (data) {
        //        callback(data);
        //    }).error(function () {
        //    });
    };

    this.create = function (subj, subjId, maxQstions, maxMark, $scope) {
        var newId = Number($scope.testList[$scope.testList.length - 1].id) + 1;

        $scope.testList.push(
            {
                "targetSubject": subjId,
                "id": newId,
                "title": subj,
                "maxQuestions": maxQstions,
                "maxMark": maxMark,
                "questions": []
            });
    };
});

app.service('TestQuestionService', function ($http, $location) {
    this.loadTestQuestions = function (user, testId, callback) {
        //var url = "/dist-learning/article/";
        //
        var questions = [];
        for (var i in TESTS) {
            if (TESTS[i].id == testId) {
                questions = TESTS[i].questions;
            }
        }
        callback(questions);
        //$http.get(url)
        //    .success(function (data) {
        //        callback(data);
        //    }).error(function () {
        //    });
    };

    this.create = function (questionText, correct, options, testId, $scope) {
        var test;

        for (var i in TESTS) {
            if (TESTS[i].id == testId) {
                test = TESTS[i];
            }
        }
        var newId = Number(test.questions[test.questions.length - 1].id) + 1;
        test.questions.push(
            {
                "id": newId,
                "questionText": questionText,
                "options": [{
                    "id": 1,
                    "correct": true,
                    "text": correct
                }, {
                    "id": 2,
                    "correct": false,
                    "text": options[0]
                }, {
                    "id": 3,
                    "correct": false,
                    "text": options[1]
                }, {
                    "id": 3,
                    "correct": false,
                    "text": options[2]
                }]
            }
        );
    };
})
;