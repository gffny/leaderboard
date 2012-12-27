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
						//$('#mblscrdCompetitionSelectionDropdown option:last').option();
					}
				}
				$('#mblscrdCompetitionSelectionDropdown').selectmenu('refresh', true);
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
		$('#holeDetail').show();
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
			$('#mblscrdHoleDetailPrevHole').button();
			$('#mblscrdHoleDetailPrevHole').css({ height:'300px'});
			$('#mblscrdHoleDetailPrevHole').addClass('ui-disabled');
			$('#mblscrdHoleDetailNextHole').button();
			$('#mblscrdHoleDetailNextHole').css({ height:'300px'});
			$('#mblscrdHoleDetailNextHoleSpan').show();
			$('#mblscrdHoleDetailCompleteScorecard').button();
			$('#mblscrdHoleDetailCompleteScorecardSpan').hide();
		} else if(holeNumber == 18) {
			//case2: holeToShow is 18 - do not show the "next" button, score the previous hole
			$('#mblscrdHoleDetailPrevHole').button();
			$('#mblscrdHoleDetailPrevHole').removeClass('ui-disabled');
			$('#mblscrdHoleDetailPrevHole').css({ height:'300px'});
			$('#mblscrdHoleDetailNextHole').button();
			$('#mblscrdHoleDetailNextHoleSpan').hide();
			$('#mblscrdHoleDetailNextHole').css({ height:'300px'});
			$('#mblscrdHoleDetailCompleteScorecard').button();
			$('#mblscrdHoleDetailCompleteScorecard').css({ height:'300px'});
			$('#mblscrdHoleDetailCompleteScorecardSpan').show();
			scoreHole(previousHoleNumber);
		} else {
			//case3: holeToShow is > 1 and < 18 - score the previousHole
			$('#mblscrdHoleDetailPrevHole').button();
			$('#mblscrdHoleDetailPrevHole').removeClass('ui-disabled');
			$('#mblscrdHoleDetailNextHole').button();
			$('#mblscrdHoleDetailNextHole').css({ height:'300px'});
			$('#mblscrdHoleDetailNextHoleSpan').show();
			$('#mblscrdHoleDetailCompleteScorecard').button();
			$('#mblscrdHoleDetailCompleteScorecardSpan').hide();
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
			//create the + button for the hole score
			$('#holeDetailScoreTable').append('<tr><td colspan=4>'+createHoleScoreButton('mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score', true)+'</td></tr>');
			$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'ScoreIncrease').button();
			$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'ScoreIncrease').bind('click', '#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score', function (e) {
				$(e.data).val(parseInt($(e.data).val())+1);
			});
			//create the golfer/hole detail for the hole
			var scoreRow = "<tr><td>"+golferArray[index].firstName+" "+golferArray[index].lastName+
			"</td><td>"+golferArray[index].currentScore+
			"</td><td>"+golferArray[index].relativeToPar+"</td><td>" +
			"<input id=mblscrdGolfer"+golferArray[index].userId+"Hole"+holeNumber+"Score type=number min=1 max=12 />"+
			"</td></tr>";
			$('#holeDetailScoreTableBody').append(scoreRow);
			$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score').textinput();
			//create the - button for the hole score
			$('#holeDetailScoreTable').append('<tr><td colspan=4>'+createHoleScoreButton('mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score', false)+'</td></tr>');
			$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'ScoreDecrease').button();
			$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'ScoreDecrease').bind('click', '#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score', function (e) {
				$(e.data).val(parseInt($(e.data).val())-1);
			});
			//if no score has been registered for that hole, leave blank, otherwise fill the value
			if((typeof golferArray[index].scorecard[holeNumber-1])!='undefined') {
				$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score').val(golferArray[index].scorecard[holeNumber-1]);
			} else {
				$('#mblscrdGolfer'+golferArray[index].userId+'Hole'+holeNumber+'Score').val(course.holeParArray[holeNumber-1]);
			}
		}		
	}

	//** HELPER FUNCTIONS **
	function createHoleScoreButton(selector, increase) {
		var button;
		if(increase) {
			button = '<a id='+selector+'Increase data-role=button data-corners=true data-shadow=true data-iconshadow=true data-wrapperels=span data-theme=c>+</a>';
		} else {
			button = '<a id='+selector+'Decrease data-role=button data-corners=true data-shadow=true data-iconshadow=true data-wrapperels=span data-theme=c>-</a>';
		}
		return button;
	}
	
	function buttonClick() {
		Window.alert('hello');
	}

	function loadCompleteScorecard() {
		//get the competitionId from the selection drop down and pass it to the getCompetitionDetailFunction
		//possible amendment; load all competition data during getUserCompetitionList to save overhead of second ajax call!
		scoreHole(18); //TODO should 18 or the last hole number of the course (9 perhaps)...
		$('#scorecardDetailTable').append(showScorecardPortrait(false));
		$('#scoreDetailTableScoreHead').show();
		$('#holeDetail').hide();
		$('#mblscrdCourseOverviewButton').hide();
		$('#mblscrdSubmitScorecard').show();
		$('#mblscrdSubmitScorecard').button();
		$('#scorecardDetail').show();
	}

	function showScorecardPortrait(asPortrait) {
		$('#scorecardDetailTable').empty();
		var scorecardDetail = '<thead><th>Hole</th>';
		if(asPortrait == true) {
			//showScorecardPortrait(true)
			//Hole# | Plyr 1 | Plyr 2 | Plyr 3 | Plyr 4
			for (var golferIndex = 0; golferIndex < golferArray.length; golferIndex++ ) {
				scorecardDetail+=('<th>'+golferArray[golferIndex].firstName+'<br/>'+golferArray[golferIndex].lastName+'</th>');
			}
			scorecardDetail += '</thead>';
			$('#scorecardDetailTable').append(scorecardDetail);
			scorecardDetail = '<tbody>';
			for (var scorecardIndex = 0; scorecardIndex < golferArray[0].scorecard.length; scorecardIndex++) {
				scorecardDetail+='<tr><td>'+(scorecardIndex+1)+'</td>';
				for (var golferIndex = 0; golferIndex < golferArray.length; golferIndex++ ) {
					scorecardDetail+=('<td>'+golferArray[golferIndex].scorecard[scorecardIndex]+'</td>');
				}
				scorecardDetail+='</tr></tbody>';
			}
		} else {
			//showScorecardPortrait(false)
			//		Hl1 | Hl2 | Hl3 | Hl4 | Hl5 ...
			//Plyr1
			//Plyr2
			//Plyr3
			//Plyr4	
			for (var scorecardIndex = 0; scorecardIndex < golferArray[0].scorecard.length; scorecardIndex++) {
				scorecardDetail+=('<th>'+(scorecardIndex+1)+'</th>');
			}
			scorecardDetail+='</thead><tbody>';
			for (var golferIndex = 0; golferIndex < golferArray.length; golferIndex++) {
				scorecardDetail+=('<tr><td>'+golferArray[golferIndex].firstName+'<br />'+golferArray[golferIndex].lastName+'</td>');
				for (var scorecardIndex = 0; scorecardIndex < golferArray[0].scorecard.length; scorecardIndex++) {
					scorecardDetail+=('<td>'+(golferArray[golferIndex].scorecard[scorecardIndex])+'</td>');
				}
				scorecardDetail+='</tr>';
			}
			scorecardDetail+='</tbody>';
		}
		return scorecardDetail;
	}

	function scoreHole(holeNumber) {
		//mblscrdGolfer"+golferArray[index].userId+"Hole"+index
		for(var golferIndex = 0; golferIndex < golferArray.length; golferIndex++) {
			golferArray[golferIndex].scorecard[holeNumber-1] =
				Number($('#mblscrdGolfer'+golferArray[golferIndex].userId+'Hole'+(holeNumber)+'Score').val());
			golferArray[golferIndex].currentScore = 0;
			golferArray[golferIndex].relativeToPar = 0;
			for (var scorecardIndex = 0; scorecardIndex < 18; scorecardIndex++){
				if(typeof(golferArray[golferIndex].scorecard[scorecardIndex])!='undefined') {
					golferArray[golferIndex].currentScore+=golferArray[golferIndex].scorecard[scorecardIndex];
					golferArray[golferIndex].relativeToPar+=(golferArray[golferIndex].scorecard[scorecardIndex]-leaderboard.home.course.holeParArray[scorecardIndex]);
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
		$('#mblscrdHoleDetailCompleteScorecard').bind('click', function (e) {loadCompleteScorecard(); });
		$('#mblscrdSubmitScorecard').bind('click', function (e) {asynchScorecardSubmitRequest(); });
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