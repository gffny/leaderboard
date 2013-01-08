<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<#list competitionList as competition>
			Competition Name: ${competition.competitionName} <br />
			<#list competition.competitionRoundList as competitionRound>
				<a href=http://localhost:8080/leaderboard/competition/scorecard/enter?userId=123&competitionRoundId=${competitionRound.roundId?c}>${competitionRound.roundName}</a><br />
			</#list>
		</#list>
	</body>
</html>