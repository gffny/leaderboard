var leaderboard = {};

leaderboard.home = function() {
	
	var scoreArray = new Array();
	var score=0;
	var toPar=0;
	var holePointerIndex = 1;
	var courseList = new Array();
	var competitionList = new Array();
	var course = {};
	var competition = {};

	function attachNavHandlers() {
		/*
				Flow:
				1)			Open > Mobile Scorecard (http://.../mobilescorecard)
				2) 			Select > Add Scorecard
				3a) 		Answer > Is this a competition {competitionQuestion}
				3a.1) 			Answer > Select competition {competitionSelection}
				3b)			else Answer > Which course {courseSelection}
				4)			for i=0; i<noOfHoles; i++
								Answer > Enter score for hole [i]
								Click > Continue {holeDetail}
				5)			Review > Final scorecard
							Click > Sign and Submit {scorecardDetail}
		 */
		//1) competition question 
		$('#mblscrdCompetitionQuestionYes').bind('click', function(e) {loadCompetitionSelection();});
		$('#mblscrdCompetitionQuestionNo').bind('click', function(e) {loadCourseList();});
		//3a competition selection
		$('#mblscrdCompetitionSelectionButton').bind('click', function(e) {loadCompetitionDetail();});
		//3a1  screen - competition detail
		$('#mblscrdCompetitionDetailButton').bind('click', function(e) {loadCourseDetail();});
		//3a2 screen - course detail
		$('#mblscrdCourseOverviewButton').bind('click', function(e) {loadHoleDetail(holePointerIndex, 1);});
		//3b screen - course selection
		$('#mblscrdCourseSelectionButton').bind('click', function(e) {loadPreCourseDetail();});
		$('#mblscrdHoleDetailPrevHole').bind('click', function(e) {loadHoleDetail(holePointerIndex, (holePointerIndex-1));});
		$('#mblscrdHoleDetailNextHole').bind('click', function(e) {loadHoleDetail(holePointerIndex, (holePointerIndex+1));});
		$('#mblscrdHoleDetailCompleteScorecard').bind('click', function(e) {completeScorecard();});
		//$('#').bind('click', function(e) {functionName();});
	}

	//** EVENT HANDLING FUNCTIONS **
	function loadCompetitionSelection() {
		//ajax call to load the list of open competitions
		var competitionList = getUserCompetitionList();
		//load competition list into the UI
		for(var i=0;i<competitionList.length;i++) {
			$('#mblscrdCompetitionSelectionDropdown').append('<option value='+i+'>'+competitionList[i].competitionName+'</option>');
		}
		//attachScoringHandler('live');
		//attachConnectivityListener();
		$('#competitionQuestion').hide();
		$('#competitionSelection').show();
	}

	function loadCompetitionDetail() {
		//get the competitionId from the selection drop down and pass it to the getCompetitionDetailFunction
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		competition = getCompetitionDetail($('#mblscrdCompetitionSelectionDropdown').val());
		course = getCompetitionCourse(competition.courseId);
		$('#competitionName').append(competition.competitionName);
		$('#competitionType').append(competition.competitionType);
		$('#competitionCourseName').append(course.courseName);
		$('#competitionCourseLocation').append(course.courseLocation);
		$('#competitionCoursePar').append(course.coursePar);
		$('#competitionSelection').hide();
		$('#competitionDetail').show();
	}

	function loadCourseList() {
		//ajax call to load the list of open competitions
		
		//load competition list into the UI
		for(var i=0;i<courseList.length;i++) {
			$('#mblscrdCourseSelectionDropdown').append('<option value='+i+'>'+courseList[i].courseName+'</option>');
		}
		//attachScoringHandler('live');
		//attachConnectivityListener();
		$('#competitionQuestion').hide();
		$('#courseSelection').show();
	}
	
	function loadPreCourseDetail() {
		course = getCompetitionCourse($('#mblscrdCourseSelectionDropdown').val());
		loadCourseDetail();
	}

	function loadCourseDetail() {
		setupScorecard(false);
		for(var i=0; i<course.holeArray.length; i++) {
			$('#scorecardDetailTable').append(
					'<tr id=hole'+(i+1)+'><td>'
					+(i+1)+'</td><td>'
					+course.holeArray[i].distance+'</td><td>'
					+course.holeArray[i].index+'</td><td>'
					+course.holeArray[i].par+'</td></tr>');
		}
		$('#scorecardDetailTable').append('</tbody');
		$('#scoreDetailTableScoreHead').show();
		$('#competitionDetail').hide();
		$('#courseSelection').hide();
		$('#mblscrdCourseOverviewButton').show();
		$('#mblscrdSubmitScorecard').hide();
		$('#scorecardDetail').show();
	}
	function loadHoleDetail(currHoleNumber, holeToShow) {
		//get the competitionId from the selection drop down and pass it to the getCompetitionDetailFunction
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		if(holeToShow == 1) {
			//case1: holeToShow is 1 - do not show the "previous" button, if the currHoleNumber is > 1 (think)
			$('#mblscrdHoleDetailPrevHole').hide();
			$('#mblscrdHoleDetailNextHole').show();
			$('#mblscrdHoleDetailCompleteScorecard').hide();
		} else if(holeToShow == 18) {
			//case2: holeToShow is 18 - do not show the "next" button, score the previous hole
			$('#mblscrdHoleDetailPrevHole').show();
			$('#mblscrdHoleDetailNextHole').hide();
			$('#mblscrdHoleDetailCompleteScorecard').show();
			scoreHole(holeToShow, currHoleNumber);
		} else {
			//case3: holeToShow is > 1 and < 18 - score the previousHole
			$('#mblscrdHoleDetailPrevHole').show();
			$('#mblscrdHoleDetailNextHole').show();
			$('#mblscrdHoleDetailCompleteScorecard').hide();
			scoreHole(holeToShow, currHoleNumber);
		}
		clearPreviousHoleData();
		$('#holeDetailNumber').append(course.holeArray[holeToShow-1].number);		
		$('#holeDetailCourseSize').append('18');
		$('#holeDetailDistance').append(course.holeArray[holeToShow-1].distance);
		$('#holeDetailHolePar').append(course.holeArray[holeToShow-1].par);
		$('#holeDetailIndex').append(course.holeArray[holeToShow-1].index);
		$('#holeDetailScore').append(score);
		$('#holeDetailScoreToPar').append(toPar);
		//if no score has been registered for that hole, leave blank, otherwise fill the value
		if((typeof scoreArray[holeToShow-1])!='undefined') {
			$('#mblscrdHoleDetailScore').val(scoreArray[holeToShow-1]);
		} else {
			$('#mblscrdHoleDetailScore').val('1');
		}
		$('#scorecardDetail').hide();
		$('#holeDetail').show();
		holePointerIndex=holeToShow;
	}

	function completeScorecard() {
		//get the competitionId from the selection drop down and pass it to the getCompetitionDetailFunction
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		scoreHole(19, 18);
		setupScorecard(true);
		for(var i=0; i<scoreArray.length; i++) {
			$('#scorecardDetailTable').append(
					'<tr id=hole'+(i+1)+'><td>'
					+(i+1)+'</td><td>'
					+course.holeArray[i].distance+'</td><td>'
					+course.holeArray[i].index+'</td><td>'
					+course.holeArray[i].par+'</td><td>'
					+scoreArray[i]+'</td></tr>');
		}
		$('#scorecardDetailTable').append('</tbody');
		$('#scoreDetailTableScoreHead').show();
		$('#holeDetail').hide();
		$('#mblscrdCourseOverviewButton').hide();
		$('#mblscrdSubmitScorecard').show();	
		$('#scorecardDetail').show();
	}

	//** HELPER FUNCTIONS **
	function setupScorecard(showScore) {
		$('#scorecardDetailTable').empty();
		var scorecardHead = '<thead><th>Hole</th><th>Distance</th><th>Index</th><th>Par</th>';
		if(showScore) { scorecardHead+='<th>Score</th>'; }
		scorecardHead += '</thead>';
		$('#scorecardDetailTable').append(scorecardHead);
		$('#scorecardDetailTable').append('<tbody>');
	}
	
	function scoreHole(holeToShow, currHoleNumber) {
		scoreArray[currHoleNumber-1] = Number($('#mblscrdHoleDetailScore').val());
		score=0;
		toPar=0;
		for(var i=0; i<holeToShow-1; i++) {
			score+=scoreArray[i];
			//toPar+=course.holeArray[i].par;
			toPar+=(scoreArray[i]-course.holeArray[i].par);
		}
	};
	
	function clearPreviousHoleData() {
		$('#holeDetailNumber').empty();		
		$('#holeDetailCourseSize').empty();
		$('#holeDetailDistance').empty();
		$('#holeDetailHolePar').empty();
		$('#holeDetailIndex').empty();
		$('#holeDetailScore').empty();
		$('#holeDetailScoreToPar').empty();
	}
	
	function getCompetitionDetail(competitionListPostion) {
		//for the moment we will return a dummy list of courses, but otherwise we will query a RESTful web service get a meaningful object!
		//we may probably have this already in memory depending on how the getUserCompetitionList works
		return competitionList[competitionListPostion];
	}

	function getUserCompetitionList() {
		//for the moment we will return a dummy list of competitions, but otherwise we will query a RESTful web service get a meaningful list!
		return competitionList;
	}
	
	function getCompetitionCourse(courseId) {
		//for the moment we will return a dummy list of courses, but otherwise we will query a RESTful web service get a meaningful list!
		return courseList[courseId];
	}
	
	function getCourseList() {
		//for the moment we will return a dummy list of courses, but otherwise we will query a RESTful web service get a meaningful list!
		return courseList;
	}

	//** DUMMY DATA FUNCTIONS **
	function loadDummyData() {
		loadDummyCompetitionData();
		loadDummyCourseData();
	}

	function loadDummyCompetitionData() {
		for(var i=0; i<4; i++) {
			competitionList[i] = {};
			competitionList[i].competitionId = i+1000;
			competitionList[i].competitionName = 'Presidents Day Cup 2011 Day '+(i+1);
			competitionList[i].competitionType = 'Stapleford';
			competitionList[i].courseId = 0;
			competitionList[i+4] = {};
			competitionList[i+4].competitionId = i+2000;
			competitionList[i+4].competitionName = 'Presidents Day Cup 2012 Day '+(i+1);
			competitionList[i+4].courseId = 1;
			competitionList[i+4].competitionType = 'Scramble';
		}
	}
	
	function loadDummyCourseData() {
		var dummyCourse = {};
		dummyCourse.courseName = 'Fresh Pond Golf Course';
		dummyCourse.courseLocation = 'Cambridge, MA';
		dummyCourse.coursePar = '70';
		dummyCourse.holeArray = new Array();
		dummyCourse.holeArray[0] = loadDummyHoleData(1, 356, 4, 4);
		dummyCourse.holeArray[1] = loadDummyHoleData(2, 269, 4, 8);
		dummyCourse.holeArray[2] = loadDummyHoleData(3, 155, 3, 16);
		dummyCourse.holeArray[3] = loadDummyHoleData(4, 370, 4, 2);
		dummyCourse.holeArray[4] = loadDummyHoleData(5, 480, 5, 6);
		dummyCourse.holeArray[5] = loadDummyHoleData(6, 172, 3, 10);
		dummyCourse.holeArray[6] = loadDummyHoleData(7, 331, 4, 14);
		dummyCourse.holeArray[7] = loadDummyHoleData(8, 132, 3, 18);
		dummyCourse.holeArray[8] = loadDummyHoleData(9, 467, 5, 12);
		dummyCourse.holeArray[9] = loadDummyHoleData(10, 356, 4, 3);
		dummyCourse.holeArray[10] = loadDummyHoleData(11, 269, 4, 7);
		dummyCourse.holeArray[11] = loadDummyHoleData(12, 155, 3, 15);
		dummyCourse.holeArray[12] = loadDummyHoleData(13, 370, 4, 1);
		dummyCourse.holeArray[13] = loadDummyHoleData(14, 480, 5, 5);
		dummyCourse.holeArray[14] = loadDummyHoleData(15, 172, 3, 9);
		dummyCourse.holeArray[15] = loadDummyHoleData(16, 331, 4, 13);
		dummyCourse.holeArray[16] = loadDummyHoleData(17, 132, 3, 17);
		dummyCourse.holeArray[17] = loadDummyHoleData(18, 467, 5, 11);
		courseList[0] = dummyCourse;
		var courseEile = {};
		courseEile.courseName = 'Newton Commonwealth GC';
		courseEile.courseLocation = 'Newton, MA';
		courseEile.coursePar = '72';
		courseEile.holeArray = dummyCourse.holeArray;
		courseList[1] = courseEile;
	}

	function loadDummyHoleData(holeNumber, distance, par, index) {
		var hole = {};
		hole.number = holeNumber;
		hole.distance = distance;
		hole.par = par;
		hole. index = index;
		return hole;
	}
	
	return {
		init: function() {
			loadDummyData();
			attachNavHandlers();
		}
	};

}();

$(document).ready(function() {
	leaderboard.home.init();
});