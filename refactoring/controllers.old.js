'use strict';

/* Controllers */

function CompetitionCtrl($scope, $routeParams, $http) {
	$http.get('http://localhost:8080/leaderboard/competition/a_options').success(function(data) {
		$scope.competitionTypeList = data.payload.competitionTypeList;
		$scope.competitionVisibilityList = data.payload.competitionVisibilityChoiceList;
		//$scope.params.value = 3;
	});

// SAMPLE HOW TO POST DATA
//	$scope.createCompetition = function() {
//		var data = {
//				name: $scope.competition.name,
//				competitionVisibility: $scope.competition.visibility,
//				competitionScoringSystem: {
//					id: $scope.competition.type
//				}
//		};
//		$http({
//		    url: 'http://localhost:8080/leaderboard/competition/a_create',
//		    method: "POST",
//		    data: data
//		}).success(function(data, status, headers, config) {
//			alert('success');
//		}).error(function(data, status, headers, config) {
//			alert('fail');
//		});
//	};
};

function LoginCtrl($scope, $location, $routeParams, LoginService) {
	$scope.loginUser = function() {
		var data = {
			profileHandle: $scope.auth.name,
			password: $scope.auth.password
		};
		LoginService.save(data,function(data) {
			$scope.goToHome();
//        	if(data.status == config.CONST.SUCCESS) {
//        	
//        		if(data.payload.passwordChangeRequired) {
//        			$scope.goToChangePassword();        			
//        		}
//        		else {        			
//        			$scope.goToHome();        			
//        		}
//        	}
        }, function(data) {
//        	$rootScope.hash = null;
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
		//$rootScope.hash = null;
	};
};