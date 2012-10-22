var leaderboard = {};

leaderboard.home = function () {

	//var APPLICATION_HOST='http://localhost:8080/leaderboard';
	var APPLICATION_HOST='/leaderboard';
	var holePointerIndex = 1, competitionList, course, scorecardRoundId;
	var golferArray = new Array();

	//** ASYCHRONOUS FUNCTIONS **
	function asynchCompetitionListRequest() {
		$.ajax({
			type: 'POST',
			url: APPLICATION_HOST+'/mobilescorecard/asynch/competitionlist',
			data: { userId : '11' },
			success: function success(data) {
				leaderboard.home.competitionList =  data;
				//load competition list into the UI
				for (var competitionIndex=0;competitionIndex<leaderboard.home.competitionList.length;competitionIndex++) {
					var competitionRoundList = leaderboard.home.competitionList[competitionIndex].competitionRoundList;
					for (var roundIndex=0; roundIndex<competitionRoundList.length; roundIndex++) {
						$('#mblscrdCompetitionSelectionDropdown').append('<option value='+competitionRoundList[roundIndex].roundId+'>'+competitionRoundList[roundIndex].roundName+'</option>');				
					}
				}
			},
			dataType: "json"
			});
	}

	function asychCourseDetailRequest(courseId) {
		$.ajax({
			type: 'POST',
			url: APPLICATION_HOST+'/mobilescorecard/asynch/coursedetail',
			data: { courseId : courseId },
			success: function success(data) {
				leaderboard.home.course =  data[0];
				var course = leaderboard.home.course;
				//TODO handle courses with less that 18 holes (i.e. 9 holes)
				//TODO make a showLandscape and showPortrait version
				$('#courseOverviewTable').append('<thead><tr><td>Hole</td><td>Par</td><td>Index</td><td>Distance</td></tr></thead><tbody></tbody>');
				var overallPar = 0, overallDistance = 0;
				for(var holeIndex = 0; holeIndex < course.holeParArray.length; holeIndex++){
					$('#courseOverviewTable tbody').append('<tr'+getAlt(holeIndex)+'><td>'+(holeIndex+1)+'</td><td>'+course.holeParArray[holeIndex]+
							'</td><td>'+course.holeIndexArray[holeIndex]+'</td><td>'+course.teeDistanceArray[holeIndex]+'</td></tr>');
					overallPar+=course.holeParArray[holeIndex];
					overallDistance+=course.teeDistanceArray[holeIndex];
				}
				$('#courseOverviewTable tbody').append('<tr><td>Par</td><td>'+overallPar+'</td><td>Distance</td><td>'+overallDistance+'</td></tr>');
				$('#competitionCourseName').append(course.name);
				$('#competitionCourseLocation').append(course.location);
			},
			dataType: "json"
		});
	}

	function getAlt(rowNumber) {
		if(rowNumber%2==0) {
			return ' class=alt';
		} else {
			return '';
		}
	}

	function asynchScorecardSubmitRequest() {
		$.ajax({
			type: 'POST',
			url: APPLICATION_HOST+'/mobilescorecard/asynch/scorecardsubmission',
			data: getScorecardForSubmission(), //TODO put in data!
			success: null,
			dataType: "json"
		});
	}
	
	//** EVENT HANDLING FUNCTIONS **
	function showCompetitionSelection() {
		asynchCompetitionListRequest();
		$('#landing').hide();
		$('#competitionSelection').show();
	}
	
	function showCompetitionDetail() {
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		competitionRound = getCompetitionRoundDetail($('#mblscrdCompetitionSelectionDropdown').val());
		scorecardRoundId = competitionRound.roundId;
		asychCourseDetailRequest(competitionRound.courseId);
		$('#competitionName').append(competitionRound.competitionName);
		$('#competitionType').append(competitionRound.competitionType);

		var golfGroup = getGolfGroup("11", competitionRound); //TODO use a real UserId here
		$('#groupName').append(golfGroup.groupName);
		var playerTableContents = "";
		for (var playerIndex = 0; playerIndex < golfGroup.golferList.length; playerIndex++) {
			golferArray[playerIndex] = golfGroup.golferList[playerIndex];
			golferArray[playerIndex].scorecard = new Array();
			golferArray[playerIndex].currentScore = 0;
			golferArray[playerIndex].relativeToPar = 0;
			playerTableContents+=("<tr><td>"+golferArray[playerIndex].firstName+"</td>" +
					"<td>"+golferArray[playerIndex].lastName+"</td>" +
							"<td>"+golferArray[playerIndex].handicap+"</td></tr>");
		}
		$('#groupPlayerTable tr:last').after(playerTableContents);
		$('#competitionSelection').hide();
		$('#competitionDetail').show();
	}

	function showFirstHole() {
		$('#competitionDetail').hide();
		loadHoleDetail(1,1);
	}

	function loadCourseList() {
		//ajax call to load the list of open competitions
		
		//load competition list into the UI
		for (var i=0;i<courseList.length;i++) {
			$('#mblscrdCourseSelectionDropdown').append('<option value='+i+'>'+courseList[i].courseName+'</option>');
		}
		//attachScoringHandler('live');
		//attachConnectivityListener();
		$('#competitionQuestion').hide();
		$('#courseSelection').show();
	}

	/**
	 * holeDetail
	 * holeNumber - the hole to be displayed
	 * previousHoleNumber - the hole that was previously displayed
	 */
	function loadHoleDetail(holeNumber, previousHoleNumber) {
		//get the competitionId from the selection drop down and pass it to the getCompetitionDetailFunction
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		if(holeNumber == 1) {
			//case1: holeToShow is 1 - do not show the "previous" button, if the currHoleNumber is > 1 (think)
			$('#mblscrdHoleDetailPrevHole').hide();
			$('#mblscrdHoleDetailNextHole').show();
			$('#mblscrdHoleDetailCompleteScorecard').hide();
		} else if(holeNumber == 18) {
			//case2: holeToShow is 18 - do not show the "next" button, score the previous hole
			$('#mblscrdHoleDetailPrevHole').show();
			$('#mblscrdHoleDetailNextHole').hide();
			$('#mblscrdHoleDetailCompleteScorecard').show();
			scoreHole(previousHoleNumber);
		} else {
			//case3: holeToShow is > 1 and < 18 - score the previousHole
			$('#mblscrdHoleDetailPrevHole').show();
			$('#mblscrdHoleDetailNextHole').show();
			$('#mblscrdHoleDetailCompleteScorecard').hide();
			scoreHole(previousHoleNumber);
		}
		clearPreviousHoleData();
		var course = leaderboard.home.course;
		$('#holeDetailNumber').append(holeNumber);		
		$('#holeDetailCourseSize').append('18'); //TODO fix this number with the correct course size!
		$('#holeDetailDistance').append(course.teeDistanceArray[holeNumber-1]);
		$('#holeDetailHolePar').append(course.holeParArray[holeNumber-1]);
		$('#holeDetailIndex').append(course.holeIndexArray[holeNumber-1]);
		for(var index = 0; index < golferArray.length; index++) {
			var scoreRow = "<tr><td>"+golferArray[index].firstName+" "+golferArray[index].lastName+
			"</td><td>"+golferArray[index].currentScore+
			"</td><td>"+golferArray[index].relativeToPar+"</td><td>" +
			"<input id=mblscrdGolfer"+golferArray[index].userId+"Hole"+holeNumber+"Score type=number min=1 max=12 />"+
			"</td></tr>";
			$('#holeDetailScoreTableBody').append(scoreRow);
			$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score').textinput();
			//if no score has been registered for that hole, leave blank, otherwise fill the value
			if((typeof golferArray[index].scorecard[holeNumber-1])!='undefined') {
				$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score').val(golferArray[index].scorecard[holeNumber-1]);
			} else {
				$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score').val(course.holeParArray[holeNumber-1]);
			}
		}

	}

	//** HELPER FUNCTIONS **
	function completeScorecard() {
		//get the competitionId from the selection drop down and pass it to the getCompetitionDetailFunction
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		scoreHole(18); //TODO should 18 or the last hole number of the course (9 perhaps)...
		setupScorecard(true);
		
		var course = leaderboard.home.course;
		for (var i = 0; i < golferArray[0].scorecard.length; i++) {
			var golfersScores = '';
			for (var golferIndex = 0; golferIndex < golferArray.length; golferIndex++ ) {
				golfersScores+=('<td>'+golferArray[golferIndex].scorecard[i]+'</td>');
			}
			$('#scorecardDetailTable').append(
						'<tr id=hole'+(i+1)+'><td>'
						+(i+1)+'</td><td>'
						+course.teeDistanceArray[i]+'</td><td>'
						+course.holeIndexArray[i]+'</td><td>'
						+course.holeParArray[i]+'</td>'
						+golfersScores+'</tr>');
		}
		$('#scorecardDetailTable').append('</tbody');
		$('#scoreDetailTableScoreHead').show();
		$('#holeDetail').hide();
		$('#mblscrdCourseOverviewButton').hide();
		$('#mblscrdSubmitScorecard').show();	
		$('#scorecardDetail').show();
	}

	function setupScorecard(showScore) {
		$('#scorecardDetailTable').empty();
		var scorecardHead = '<thead><th>Hole</th><th>Distance</th><th>Index</th><th>Par</th>';
		if(showScore) {
			for (var golferIndex = 0; golferIndex < golferArray.length; golferIndex++ ) {
				scorecardHead+=('<th>'+golferArray[golferIndex].firstName+''+golferArray[golferIndex].lastName+'</th>');
			}
		}
		scorecardHead += '</thead>';
		$('#scorecardDetailTable').append(scorecardHead);
		$('#scorecardDetailTable').append('<tbody>');
	}
	
	function scoreHole(holeNumber) {
		//mblscrdGolfer"+golferArray[index].userId+"Hole"+index
		for(var golferIndex = 0; golferIndex < golferArray.length; golferIndex++) {
			golferArray[golferIndex].scorecard[holeNumber-1] =
				Number($('#mblscrdGolfer'+golferArray[golferIndex].userId+'Hole'+(holeNumber)+'Score').val());
			golferArray[golferIndex].currentScore = 0;
			for (var scorecardIndex = 0; scorecardIndex < 18; scorecardIndex++){
				if(typeof(golferArray[golferIndex].scorecard[scorecardIndex])!='undefined') {
					golferArray[golferIndex].currentScore+=golferArray[golferIndex].scorecard[scorecardIndex];
				}
			}
		}
	};
	
	function clearPreviousHoleData() {
		$('#holeDetailScoreTableBody').empty();
		$('#holeDetailNumber').empty();		
		$('#holeDetailCourseSize').empty();
		$('#holeDetailDistance').empty();
		$('#holeDetailHolePar').empty();
		$('#holeDetailIndex').empty();
		$('#holeDetailScore').empty();
		$('#holeDetailScoreToPar').empty();
	}
	
	function getScorecardForSubmission() {
		var scorecardSubmission = new Object();
		scorecardSubmission.golfer = new Array();
		scorecardSubmission.golferCount = golferArray.length;
		scorecardSubmission.competitionRoundId = scorecardRoundId; 
		for( var i = 0; i < golferArray.length; i++ ) {
			scorecardSubmission.golfer[i] = new Object();
			scorecardSubmission.golfer[i].userId = golferArray[i].userId;
			scorecardSubmission.golfer[i].strokes = golferArray[i].currentScore;
			scorecardSubmission.golfer[i].scorecard = golferArray[i].scorecard; 
		}
		
		return scorecardSubmission;
	}
	
	function getCompetitionRoundDetail(competitionRoundId) {
		//for the moment we will return a dummy list of courses, but otherwise we will query a RESTful web service get a meaningful object!
		//we may probably have this already in memory depending on how the getUserCompetitionList works
		var competitionRound = null;
		// use JQuery to parse the competition list for values of "competitionRoundId
		$(leaderboard.home.competitionList).each(function (count, competition){
			$(competition.competitionRoundList).each(function (count, round){				
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
		$(competitionRound.groupList).each(function (count, golfGroup){
			//inspect each member in each group
			$(golfGroup.golferList).each(function (playerCount, golfer){
				//if the user id is in this group, set it as the return
				if(golfer.userId==userId) {
					returnGroup = golfGroup;
				}
			});
		});
		return returnGroup;
	}

	//NAVIGATION HANDLING!
	function attachNavHandlers() {
		$('#landingCompetition').bind('click', function (e) {showCompetitionSelection(); });
		//3a competition selection
		$('#mblscrdCompetitionSelectionButton').bind('click', function (e) {showCompetitionDetail(); });
		//3a1  screen - competition detail
		$('#mblscrdCompetitionDetailButton').bind('click', function (e) {showFirstHole(); });
		//3a2 screen - course detail
		$('#mblscrdCourseOverviewButton').bind('click', function (e) {loadHoleDetail(holePointerIndex, 1); });
		//3b screen - course selection
		$('#mblscrdCourseSelectionButton').bind('click', function (e) {loadPreCourseDetail(); });
																					  // loadHoleDetail (holeNumber, previousHoleNumber) 
		$('#mblscrdHoleDetailPrevHole').bind('click', function (e) { holePointerIndex--; loadHoleDetail(holePointerIndex, (holePointerIndex + 1)); });
		$('#mblscrdHoleDetailNextHole').bind('click', function (e) { holePointerIndex++; loadHoleDetail(holePointerIndex, (holePointerIndex - 1)); });
		$('#mblscrdHoleDetailCompleteScorecard').bind('click', function (e) {completeScorecard(); });
		$('#mblscrdSubmitScorecard').bind('click', function (e) {asynchScorecardSubmitRequest(); });
		//$('#').bind('click', function (e) {functionName();});
	}
	
	return {
		init: function () {
			attachNavHandlers();
		}
	};

}();

$(document).ready(function () {
	leaderboard.home.init();
});