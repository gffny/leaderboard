angular.module('scorecard', []).config(
		['$routeProvider', function($routeProvider) {
			$routeProvider.when('/menu', 
					{templateUrl: 'partials/menu.html', controller: MenuCtrl}
			).when('/competition/', 
					{templateUrl: 'partials/competition.html', controller: PhoneDetailCtrl}
			).when('/competition/:competitionId', 
					{templateUrl: 'partials/competition-detail.html', controller: PhoneDetailCtrl}
			).when('/practice/', 
					{templateUrl: 'partials/practice.html', controller: PhoneDetailCtrl}
			).when('/practice/:courseId', 
					{templateUrl: 'partials/phone-detail.html', controller: PhoneDetailCtrl}
			).otherwise({redirectTo: 'http://localhost:8080/leaderboard'});
    }]);