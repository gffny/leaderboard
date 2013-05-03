'use strict';

/* Controllers */

function CompetitionCtrl($scope, $routeParams, $http) {
	$http.get('http://localhost:8080/leaderboard/competition/a_options').success(function(data) {
		$scope.competitionTypeList = data.payload.competitionTypeList;
		$scope.competitionVisibilityList = data.payload.competitionVisibilityChoiceList;
	});
};

function LoginCtrl($scope, $location, $routeParams, LoginService) {
	$scope.loginUser = function() {
		var data = {
			profileHandle: $scope.auth.name,
			password: $scope.auth.password
		};
		LoginService.save(data,function(data) {
			$scope.goToHome();
        }, function(data) {
        	if(data.status == '401') {
            	alert(data.data.message);
        	}
        	else if(data.status == '423') {
        		alert('error');
        	}
        	else if(data.status == '401') {
        		alert('error');
        	}      	
        });
	};
	
	$scope.goToHome = function(){
		$location.path('menu');
	};
};

function MenuCtrl($scope, $location, $routeParams, MenuService) {

};

function PracticeCtrl($scope, $http, $location, $routeParams, PracticeService) {
	$http.get('http://localhost:8080/leaderboard/mobile/a_courselist').success(function(data) {
		$scope.courseList = data.payload.golfCourseList;
		var courseMap = {};
		for(var i = 0; i < $scope.courseList.length; i++) {
			var golfCourse = $scope.courseList[i];
			var key = golfCourse.id+'.'+golfCourse.teeColour;
			courseMap[key] = golfCourse;
		}
		PracticeService.saveCourseMap(courseMap);
	}).error(function() {
        alert("error");
    });
	
	$scope.playPracticeRound = function() {
		$location.path('practice/'+$scope.practice.course);
	};
};

function PracticeDetailCtrl($scope, $http, $location, $routeParams, PracticeService) {
	$scope.course = PracticeService.getCourse($routeParams.course);
	var frontNine = {};
	frontNine.holeIndexArray = $scope.course.holeIndexArray.slice(0,9);
	frontNine.holeParArray = $scope.course.holeParArray.slice(0,9);
	frontNine.teeDistanceArray = $scope.course.teeDistanceArray.slice(0,9);
	var backNine = {};
	backNine.holeIndexArray = $scope.course.holeIndexArray.slice(9,18);
	backNine.holeParArray = $scope.course.holeParArray.slice(9,18);
	backNine.teeDistanceArray = $scope.course.teeDistanceArray.slice(9,18);
	$scope.course.frontNine = frontNine;
	$scope.course.backNine = backNine;
};