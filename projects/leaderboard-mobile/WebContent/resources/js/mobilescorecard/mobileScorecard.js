leaderboard = {};

leaderboard.home = function() {
	
	var scoreArray = new Array();
	var score=0;
	var toPar=0;
	var holePointerIndex = 1;

	var golferArray = new Array();

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
		//$('#mblscrdCompetitionQuestionYes').bind('click', function(e) {loadCompetitionSelection();});
		$('#mblscrdCompetitionQuestionYes').bind('click', function(e) {asynchCompetitionListRequest();});
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
		$('#mblscrdSubmitScorecard').bind('click', function(e) {asynchScorecardSubmitRequest();});
		//$('#').bind('click', function(e) {functionName();});
	}

	//** ASYCHRONOUS FUNCTIONS **
	function asynchCompetitionListRequest() {
		$.ajax({
			  type: 'POST',
			  url: 'http://localhost:8080/leaderboard-portal/mobilescorecard/asynch/competitionlist',
			  data: { userId : '11' },
			  success: function success(data) {
				  leaderboard.home.competitionList =  data;
				  loadCompetitionSelection();
			  },
			  dataType: "json"
			});
	}

	function asychCourseDetailRequest(courseId) {
		$.ajax({
			  type: 'POST',
			  url: 'http://localhost:8080/leaderboard-portal/mobilescorecard/asynch/coursedetail',
			  data: { courseId : courseId },
			  success: function success(data) {
				  leaderboard.home.course =  data[0];
				  loadCourseOverview();
			  },
			  dataType: "json"
		});
	}
	
	function asynchScorecardSubmitRequest() {
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8080/leaderboard-portal/mobilescorecard/asynch/scorecardsubmission',
			data: { scorecardArray : scoreArray }, //TODO put in data!
			success: null,
			dataType: "json"
		});
	}
	
	//** EVENT HANDLING FUNCTIONS **
	function loadCompetitionSelection() {
		
		//load competition list into the UI
		for(var competitionIndex=0;competitionIndex<leaderboard.home.competitionList.length;competitionIndex++) {
			var competitionRoundList = leaderboard.home.competitionList[competitionIndex].competitionRoundList;
			for(var roundIndex=0; roundIndex<competitionRoundList.length; roundIndex++) {
				$('#mblscrdCompetitionSelectionDropdown').append('<option value='+competitionRoundList[roundIndex].roundId+'>'+competitionRoundList[roundIndex].roundName+'</option>');				
			}
		}
		$('#competitionQuestion').hide();
		$('#competitionSelection').show();
	}
	
	function loadCourseOverview() {
		$('#competitionCourseName').append(leaderboard.home.course.name);
		$('#competitionCourseLocation').append(leaderboard.home.course.location);
		$('#competitionCoursePar').append(leaderboard.home.course.par);
	}

	function loadCompetitionDetail() {
		//get the competitionId from the selection drop down and pass it to the getCompetitionRoundDetail function
		
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		competitionRound = getCompetitionRoundDetail($('#mblscrdCompetitionSelectionDropdown').val());
		asychCourseDetailRequest(competitionRound.courseId);
		var golfGroup = getGolfGroup("11", competitionRound); //TODO use a real UserId here
		$('#competitionName').append(competitionRound.competitionName);
		$('#competitionType').append(competitionRound.competitionType);
		$('#groupName').append(golfGroup.groupName);
		var playerTableContents = "<tr><td>First Name</td><td>Last Name</td><td>Handicap</td></tr>";
		for(var playerIndex = 0; playerIndex < golfGroup.golferList.length; playerIndex++) {
			golferArray[playerIndex] = golfGroup.golferList[playerIndex];
			playerTableContents+=("<tr><td>"+golferArray[playerIndex].firstName+"</td>" +
					"<td>"+golferArray[playerIndex].lastName+"</td>" +
							"<td>"+golferArray[playerIndex].handicap+"</td></tr>");
		}
		$('#groupPlayerTable').html(playerTableContents);
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
		var course = leaderboard.home.course;
		for(var i=0; i<course.holeIndexArray.length; i++) {
			$('#scorecardDetailTable').append(
					'<tr id=hole'+(i+1)+'><td>'
					+(i+1)+'</td><td>'
					+course.teeDistanceArray[i]+'</td><td>'
					+course.holeIndexArray[i]+'</td><td>'
					+course.holeParArray[i]+'</td></tr>');
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
		var course = leaderboard.home.course;
		$('#holeDetailNumber').append(holeToShow);		
		$('#holeDetailCourseSize').append('18');
		$('#holeDetailDistance').append(course.teeDistanceArray[holeToShow-1]);
		$('#holeDetailHolePar').append(course.holeParArray[holeToShow-1]);
		$('#holeDetailIndex').append(course.holeIndexArray[holeToShow-1]);
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
		
		var course = leaderboard.home.course;
		for(var i=0; i<scoreArray.length; i++) {
			$('#scorecardDetailTable').append(
					'<tr id=hole'+(i+1)+'><td>'
					+(i+1)+'</td><td>'
					+course.teeDistanceArray[i]+'</td><td>'
					+course.holeIndexArray[i]+'</td><td>'
					+course.holeParArray[i]+'</td><td>'
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
			toPar+=(scoreArray[i]-leaderboard.home.course.holeParArray[i]);
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
	
	function getCompetitionRoundDetail(competitionRoundId) {
		//for the moment we will return a dummy list of courses, but otherwise we will query a RESTful web service get a meaningful object!
		//we may probably have this already in memory depending on how the getUserCompetitionList works
		var competitionRound = null;
		// use JQuery to parse the competition list for values of "competitionRoundId
		$(leaderboard.home.competitionList).each(function(count, competition){
			$(competition.competitionRoundList).each(function(count, round){				
				if(round.roundId == competitionRoundId) {
					competitionRound = round;
				}
			});
		});
		return competitionRound;
	}
	
	function getGolfGroup(userId, competitionRound) {
		//set up the golf group to return
		returnGroup = null;
		//for each group in the competition round, inspect for our user id
		$(competitionRound.groupList).each(function(count, golfGroup){
			//inspect each member in each group
			$(golfGroup.golferList).each(function(playerCount, golfer){
				//if the user id is in this group, set it as the return
				if(golfer.userId==userId) {
					returnGroup = golfGroup;
				}
			});
		});
		return returnGroup;
	}


	function getCourseList() {
		//for the moment we will return a dummy list of courses, but otherwise we will query a RESTful web service get a meaningful list!
		return courseList;
	}
	
	return {
		init: function() {
			attachNavHandlers();
		}
	};

}();

$(document).ready(function() {
	leaderboard.home.init();
});