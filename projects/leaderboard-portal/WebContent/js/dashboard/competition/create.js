var leaderboard = {};

leaderboard.competitionCreate = function () {

	//var APPLICATION_HOST='http://localhost:8080/leaderboard';
	var APPLICATION_HOST='/leaderboard';
	var userId = '11';

	//** ASYCHRONOUS FUNCTIONS **
	function asychGetPotentialCompetitorList(userId) {
		$.ajax({
			type: 'POST',
			url: APPLICATION_HOST+'/dashboard/asynch/competitorlist',
			data: { userId : userId },
			success: function success(data) {
				leaderboard.competitionCreate.competitorList =  data;
				//TODO load competitor list into the UI
				loadCompetitorList();
			},
			dataType: "json"
			});
	}

	//** EVENT HANDLING FUNCTIONS **
	function textInputCallback(e, elementToHandle) {
		if(!isTextInputEmpty($(e.currentTarget))) {
			$('#'+elementToHandle).removeAttr('disabled');
		} else {
			$('#'+elementToHandle).prop('disabled', true);
		}
	}
	
	function selectionInputCallback(e, elementToHandle) {
		if(!$(e.currentTarget).val()=="indInstructions") {
			$('#'+elementToHandle).prop('disabled', true);			
		} else {
			$(e.currentTarget).find('option[value=invalid]').remove();
			$('#'+elementToHandle).removeAttr('disabled');
		}
	}
	
	function loadCompetitorList() {
/*		var elementToLoad;
		if(isNonMatchplayEvent()) {
			elementToLoad = $('#competitionNonMatchplayCompetitorList');
		} else {
			elementToLoad = $('#competitionMatchplayCompetitorList');
		}*/
		for(var golferIndex=0; golferIndex < leaderboard.competitionCreate.competitorList.length; golferIndex++) {
			//elementToLoad.
			var tempGolfer = leaderboard.competitionCreate.competitorList[golferIndex];
			var optionString = '<option value="' + tempGolfer.userId + '">' + tempGolfer.firstName + ' ' + tempGolfer.lastName + '</option>';
			$('#competitionPotentialCompetitorList').append(optionString);
		}
	}

	//** HELPER FUNCTIONS **
	function isTextInputEmpty(element) {
		if(element != null) {
			return ($.trim((element).val()) == "");
		} else {
			return false;
		}
	}

	function isNonMatchplayEvent() {
		var competitionFormat = $('#competitionFormat').val();
		if(competitionFormat.substring(competitionFormat.length-9, competitionFormat.length)=='Matchplay') {
			return false;
		}
		return true;
	}

	//NAVIGATION HANDLING!
	function attachNavHandlers() {
		//Screen One
		$('#competitionName').bind('keyup', function (e) { textInputCallback(e, 'competitionFormat'); });
		$('#competitionFormat').bind('change', function (e) { selectionInputCallback(e, 'competitionAudience'); });
		$('#competitionAudience').bind('change', function (e) { selectionInputCallback(e, 'competitionGroupSize'); });
		$('#competitionAudience').bind('change', function (e) { selectionInputCallback(e, 'competitionCompetitorNumber'); });
		$('#competitionAudience').bind('change', function (e) { 
			if(!($(e.currentTarget).val()=="public")) {
				asychGetPotentialCompetitorList(userId);
			}
		});
		//Screen Two Matchplay
		$('#competitionCompetitorNumber').bind('keyup', function (e) { textInputCallback(e, 'competitionMatchplayCompetitorList'); });
		$('#competitionMatchplayCompetitorList').bind('keyup', function (e) { textInputCallback(e, 'competitionCompetitorListHandicap'); });
		//Screen Two Non Matchplay
		$('#competitionGroupSize').bind('change', function (e) { selectionInputCallback(e, 'competitionNonMatchplayCompetitorList'); });
		$('#competitionNonMatchplayCompetitorList').bind('keyup', function (e) { textInputCallback(e, 'competitionCompetitorNumber'); });
	}

	function attachDisplayHandlers() {
		$('#competitionAudience').bind('change', function (e) { 
			if(isNonMatchplayEvent()) {
				$('#competitionCreateScrnTwoNonMatchplay').removeAttr('hidden');
			} else {
				$('#competitionCreateScrnTwoMatchplay').removeAttr('hidden');
			}
		});
		$('#competitionNonMatchplayCompetitorList').bind('keyup', function (e) {
			$('#competitionCreateScrnThree').removeAttr('hidden');
		});
		$('#competitionMatchplayCompetitorList').bind('keyup', function (e) { 
			$('#competitionCreateScrnThree').removeAttr('hidden'); 
		});
		//TODO handle how to show the third screen
		//TODO validate the competitor count, and put in a reasonable competitor list function; time to start AJAXing!
	}
	
	function attachCompetitorListHandlers() {
		$('#competitorListSelectAll').bind('click', function (e) {
			$('#competitionPotentialCompetitorList option').each(function(index) {
				$(this).remove();
				$('#competitionSelectedCompetitorList').append($('<option>', { 
					value: $(this).val(), 
					text : $(this).text() 
				}));
			});
		});
		$('#competitorListSelect').bind('click', function (e) {
			$("#competitionPotentialCompetitorList option:selected").each(function () {
				$(this).remove();
				$('#competitionSelectedCompetitorList').append($('<option>', { 
					value: $(this).val(), 
					text : $(this).text() 
				}));
			});
		});
		$('#competitorListRemove').bind('click', function (e) {
			$("#competitionSelectedCompetitorList option:selected").each(function () {
				$(this).remove();
				$('#competitionPotentialCompetitorList').append($('<option>', { 
					value: $(this).val(), 
					text : $(this).text() 
				}));
			});
		});
		$('#competitorListRemoveAll').bind('click', function (e) {
			$('#competitionSelectedCompetitorList option').each(function(index) {
				$(this).remove();
				$('#competitionPotentialCompetitorList').append($('<option>', { 
					value: $(this).val(), 
					text : $(this).text() 
				}));
			});
		});
	}

	return {
		init: function () {
			attachNavHandlers();
			attachDisplayHandlers();
			attachCompetitorListHandlers();
		}
	};

}();

$(document).ready(function () {
	leaderboard.competitionCreate.init();
});