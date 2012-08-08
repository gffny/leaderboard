var leaderboard = {};

leaderboard.home = function() {

	function attachNavHandlers() {
		$('#mblscrdCompetitionQuestionYes').bind('click', function(e) {handleNavEvent(e, 'Blah');});

	}

	function handleNavEvent(event, showArticleSelector) {
		alert('shamon!');
		event.preventDefault();
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