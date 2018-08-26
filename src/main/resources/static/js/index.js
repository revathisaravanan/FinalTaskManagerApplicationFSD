var app = angular.module('TaskManagement', ['ngRoute']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'pages/welcome.html',
            controller: 'WelcomeController'
        })

        .when('/add', {
            templateUrl: 'pages/addTask.html',
            controller: 'TaskManagerController'
        })

        .when('/view', {
            templateUrl: 'pages/viewTask.html',
            controller: 'TaskManagerController'
        })

        .otherwise({redirectTo: '/'});
});

app.controller('WelcomeController', function ($scope) {
    $scope.message = 'Hello from AddTaskController';
    $scope.name = 'Your Name Here...';
});

app.controller('TaskManagerController', function ($scope, $http) {

    $scope.message = 'Hello from AddTaskController';
    $scope.task = {};
    console.log("Start.......|||||");
    $scope.saveTask = function () {

        console.log($scope.task);
        $scope.taskNameRequired = '';
        $scope.priorityRequired = '';
        $scope.startDateRequired = '';
        $scope.endDateRequired = '';

        if (!$scope.task.taskName) {
            $scope.taskNameRequired = 'Task Name Required';
            return false;
        }

        if (!$scope.task.priority) {
            $scope.priorityRequired = 'Priority Required';
            return false;
        }

        if (!$scope.task.startDate) {
            $scope.startDateRequired = 'Start Date Required';
            return false;
        }

        if (!$scope.task.endDate) {
            $scope.endDateRequired = 'End Date Required';
            return false;
        }
        var method = "POST";
        var url = "/taskManagercreateTask";
        $http({
            method : method,
            url : url,
            data : angular.toJson($scope.task),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then( _success);

    };
    $scope.master = {};
    function _success() {

        $scope.reset = function () {
            $scope.task = angular.copy($scope.master);
            $scope.taskNameRequired = '';
            $scope.priorityRequired = '';
            $scope.startDateRequired = '';
            $scope.endDateRequired = '';
        };
        $scope.reset();
    }

    $scope.sortType     = 'taskId'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    $scope.min_priority = 0;
    $scope.max_priority = 30;
    $scope.filterStartDate = "01/01/2000";
    $scope.filterEndDate = "12/31/9999";
    $scope.enableEdit=[];
    $scope.editTask = function (id) {
        console.log("Edit Index: "+id);
        $scope.enableEdit[id] = true;
    }

    $http({
        method : 'GET',
        url : '/getAllTask'
    }).then(function successCallback(response) {
        $scope.tasks = response.data;
    }, function errorCallback(response) {
        console.log(response.statusText);
    });

    $scope.endTask = function (taskId) {
        console.log(taskId);
        var method1 = "POST";
        var url1 = "/endTaskManager";
        $http({
            url : url1,
            method : method1,
            data : angular.toJson($scope.task),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then(function successCallBack() {
            $scope.enableEdit[taskId] = false;
        });
    };
});

app.filter('range', function() {
    return function(list, min, max) {
        max = parseInt(max);
        var result = [];
        var minimum = (min) ? min : 0;
        var maximum = (max) ? max : 30;
        angular.forEach(list, function(input) {
            if (input.priority >= minimum && input.priority <= maximum) result.push(input);
        });
        return result;
    };
});

/*app.filter('dateRange', function () {
    return function(list, filterStartDate, filterEndDate) {
        var result = [];
        var start_date = (filterStartDate) ? Date.parse(filterStartDate) : 0;
        var end_date = (filterEndDate) ? Date.parse(filterEndDate) : new Date(9999, 11, 31);
        for (var i = 0; i < list.length; i++) {
            if (list[i].startDate >= start_date && list[i].endDate <= end_date) {
                result.push(list[i]);
            }
        }
        return result;
    };
});*/

//Use this if the date input is in String format "04/01/2000"
app.filter('dateRange', function () {
    return function(list, filterStartDate, filterEndDate) {
        var result = [];
        var start_date = (filterStartDate) ? Date.parse(filterStartDate) : 0;
        var end_date = (filterEndDate) ? Date.parse(filterEndDate) : new Date(9999, 11, 31);
        for (var i = 0; i < list.length; i++) {
            var startDate = (list[i].startDate) ? Date.parse(list[i].startDate) : 0;
            var endDate = (list[i].endDate) ? Date.parse(list[i].endDate) : 0;
            if (startDate >= start_date && endDate <= end_date) {
                result.push(list[i]);
            }
        }
        return result;
    };
});