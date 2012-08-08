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
		<script type=text/javascript src=../../js/mobileScorecard.js></script>
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
		<section title=competitionQuestion>
			<h3>Competition</h3>
			<input id=mblscrdCompetitionQuestionYes
				title=mblscrdCompetitionQuestionYes
				type=submit
				value=Yes />
			<input id=mblscrdCompetitionQuestionNo 
				title=mblscrdCompetitionQuestionNo
				type=submit
				value=No />
		</section>
		<section title=competitionSelection hidden=true>
			<h3>Competition</h3>
			<select>
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
				<option>7</option>
				<option>8</option>
				<option>9</option>
				<option>10</option>
				<option>11</option>
				<option>12</option>
			</select>
			<input title=mblscrdCompetitionSelection
				type=text />
		</section>
		<section title=courseSelection hidden=true>
			<h3>Course</h3>
			<input type=text />
		</section>
		<section title=holeDetail hidden=true>
			Hole {x} of {y}<br />
			Distance<br />
			Par<br />
			Index<br />
			<br />
			Score<br />
			vs. Par<br />
			<br />
			<input name=mblscrdHoleDetailScore 
				type=number
				min=1
				max=12
				value=4 />
			<input name=mblscrdHoleDetailSubmit
				type=submit />
		</section>
		<section title=scorecardDetail hidden=true>
			<div title=holeReview>
				
			</div>
		</section>
	</body>
</html>