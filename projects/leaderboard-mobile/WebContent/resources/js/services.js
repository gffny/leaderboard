'use strict';

var serviceModule = angular.module('lrdbrdServices', ['ngResource']);
/* services */
serviceModule.factory("LoginService", ['$resource', function ($resource) {
	var service =  $resource('http://localhost\\:8080/leaderboard/authentication/a_process');
    return {
        save: function(data, onSuccess, onFail){
            //onFail = onFail || globalErrorService;
            service.save(data, onSuccess, onFail);
        }
    };
}]);

serviceModule.factory("MenuService", ['$resource', function ($resource) {
	var service =  $resource('http://localhost\\:8080/leaderboard/authentication/a_process');
    return {
        save: function(data, onSuccess, onFail){
            //onFail = onFail || globalErrorService;
            service.save(data, onSuccess, onFail);
        }
    };
}]);

serviceModule.factory("PracticeService", ['$resource', function ($resource) {
	var service =  $resource('http://localhost\\:8080/leaderboard/authentication/a_process');
	var courseMap = {};
	var course = {};
    return {
        save: function(data, onSuccess, onFail){
            //onFail = onFail || globalErrorService;
            service.save(data, onSuccess, onFail);
        },
    	saveCourseMap: function(courseMapToSave) {
    		courseMap = courseMapToSave;
    	},
        getCourse: function(courseKey) {
        	return courseMap[courseKey];
        },
    	saveCourse: function(courseToSave) {
    		course = courseToSave;
    	},
        getHole: function(holeNumber) {
        	// subtract one for the array index
        	holeNumber = holeNumber-1;
        	return { 
        		par: course.holeParArray[holeNumber], 
        		index: course.holeIndexArray[holeNumber],
        		distance: course.teeDistanceArray[holeNumber]
        	};
        }
    };
}]);