<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name=viewport content=width=device-width,initial-scale=1,maximum-scale=1>
		<title>Leaderboard - Mobile Scorecard</title>
		<!--[if lt IE 9]><script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script><![endif]-->
		<!--[if lt IE 9]><script src=http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js></script><![endif]-->
		<link href=css/styles.css rel=stylesheet />
		<link rel=icon type=image/ico href=http://eaps-www.mit.edu/paoc/sites/all/themes/paoc/favicon.ico>
		<script type=text/javascript src=https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js></script>
		<script type=text/javascript src=../js/mobileScorecard.js></script>
	</head>
	<body>
		<header>
			<h1>Leaderboard</h1>
			<h2>Mobile Scorecard</h2>
		</header>
		<!--
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
		 -->
		<!-- competition? -->
		<section id=competitionQuestion>
			<h3>Competition</h3>
			<input id=mblscrdCompetitionQuestionYes
				type=submit
				value=Yes />
			<input id=mblscrdCompetitionQuestionNo
				type=submit
				value=No />
		</section>
		<!-- competition selection -->
		<section id=competitionSelection hidden=true>
			<h3>Competition</h3>
			<select id=mblscrdCompetitionSelectionDropdown>
			</select><br />
			<input id=mblscrdCompetitionSelectionButton
				type=submit
				value=Select />
		</section>
		<!-- competition detail -->
		<section id=competitionDetail hidden=true>
			<h3 id=competitionName></h3>
			Course Name: <span id=competitionCourseName></span><br />
			Course Location: <span id=competitionCourseLocation></span><br />
			Course Par: <span id=competitionCoursePar></span><br />
			<input id=mblscrdCompetitionDetailButton
				type=submit
				value=Continue />
		</section>
		<!-- course selection -->
		<section id=courseSelection hidden=true>
			<h3>Course</h3>
			<select id=mblscrdCourseSelectionDropdown>
			</select><br />
			<input id=mblscrdCourseSelectionButton
				type=submit
				value=Select />
		</section>
		<!-- hole details -->
		<section id=holeDetail hidden=true>
			<h3>Hole Details</h3>
			Hole <span id=holeDetailNumber></span> of <span id=holeDetailCourseSize></span><br />
			Distance <span id=holeDetailDistance></span><br />
			Par <span id=holeDetailHolePar></span><br />
			Index <span id=holeDetailIndex></span><br />
			<h3>Score Details</h3>
			Score <span id=holeDetailScore></span><br />
			To Par <span id=holeDetailScoreToPar></span><br />
			<h3>Hole Score</h3>
			<input name=mblscrdHoleDetailScore
				id=mblscrdHoleDetailScore
				type=number
				min=1
				max=12 /><br />
			<input name=mblscrdHoleDetailPrevHole
				id=mblscrdHoleDetailPrevHole 
				type=submit
				value='Previous Hole' />
			<input name=mblscrdHoleDetailNextHole
				id=mblscrdHoleDetailNextHole
				type=submit
				value='Next Hole'  />
			<input name=mblscrdHoleDetailCompleteScorecard
				id=mblscrdHoleDetailCompleteScorecard
				type=submit
				value='Complete Scorecard'  />
		</section>
		<!-- scorecard details -->
		<section id=scorecardDetail hidden=true>
			<h3>Scorecard Overview</h3>
			<table id=scorecardDetailTable>
				<!--
					Hole # | Distance | Index | Par | Score
				 -->
			</table>
			<input name=mblscrdSubmitScorecard
				id=mblscrdSubmitScorecard
				type=submit
				value='Submit Scorecard'
				hidden=true />
			<input name=mblscrdCourseOverviewButton
				id=mblscrdCourseOverviewButton
				type=submit
				value='Continue to First Hole'
				hidden=true />
		</section>
		<!-- error page -->
	</body>
</html>