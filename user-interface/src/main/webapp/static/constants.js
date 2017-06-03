var MENU = {
    "student": [
        {
            "title": "Article",
            "link": "/article"
        },
        {
            "title": "Competencies",
            "link": "/competency"
        },
        {
            "title": "Subjects",
            "link": "/subjects"
        },
        {
            "title": "Tests",
            "link": "/tests"
        }
    ],
    "teacher": [
        {
            "title": "Subjects",
            "link": "/subjects"
        },
        {
            "title": "Tests",
            "link": "/tests"
        }
    ],
    "admin": [
        {
            "title": "Competency",
            "link": "/competency"
        },
        {
            "title": "Subjects",
            "link": "/subjects"
        }
    ]
};

var ACCESS_MAP = {

    "student": {
        "create": ['article']
    },
    "teacher": {
        "create": ['subjects', 'test', 'subj-comp']
    },
    "admin": ["competency", "subjects", 'subj-comp']
};

var SUBJECT = [{
    "id": 10,
    "name": "OOP"
}, {
    "id": "20",
    "name": "Math"
}
];

var COMPETENCY = [{
    "id": 10,
    "name": "Computer science",
    "description": "Descr"
}, {
    "id": "20",
    "name": "Programing Engineering",
    "description": "dsds"
}, {
    "id": "30",
    "name": "DB",
    "description": "dsds"
}
];

var SUBSCRIPTIONS = [
    {
        "email": "ancobs@gmail.com",
        "competencies": [
            {
                "id": 10,
                "name": "Computer science",
                "description": "Descr"
            },
            {
                "id": "30",
                "name": "DB",
                "description": "dsds"
            }
        ]
    }
];

var USER_STUDENT = "student";
var USER_TEACHER = "teacher";
var USER_ADMIN = "admin";