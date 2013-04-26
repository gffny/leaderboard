'use strict';

/* Controllers */

function CompetitionCtrl($scope, $http) {
	$http.get('http://localhost:8080/leaderboard/competition/a_options').success(function(data) {
		$scope.competitionTypeList = data.payload.competitionTypeList;
		$scope.competitionVisibilityList = data.payload.competitionVisibilityChoiceList;
		//$scope.params.value = 3;
	});

	$scope.createCompetition = function() {
		var data = {
				name: $scope.competition.name,
				competitionVisibility: $scope.competition.visibility,
				competitionScoringSystem: {
					id: $scope.competition.type
				}
		};
		$http({
		    url: 'http://localhost:8080/leaderboard/competition/a_create',
		    method: "POST",
		    data: data
		}).success(function(data, status, headers, config) {
			alert('success');
		}).error(function(data, status, headers, config) {
			alert('fail');
		});
	};
};
