angular.module('scorecard', ['lrdbrdServices']).config(
		['$routeProvider', function($routeProvider) {
			$routeProvider.when('/login', 
					{templateUrl: 'resources/html/login.html', controller: LoginCtrl}
			).when('/menu', 
					{templateUrl: 'resources/html/menu.html', controller: MenuCtrl}
			).when('/practice', 
					{templateUrl: 'resources/html/practice.html', controller: PracticeCtrl}
			).when('/practice/:course', 
					{templateUrl: 'resources/html/practice-detail.html', controller: PracticeDetailCtrl}
			);
    }]);