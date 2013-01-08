<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<#list competitionList as competition>
			Competition Name: <a href=http://localhost:8080/leaderboard/competition/score/competition?competitionId=${competition.competitionId}>${competition.competitionName}</a><br />
			<#list competition.competitionRoundList as competitionRound>
				<a href=http://localhost:8080/leaderboard/competition/score/round?competitionRoundId=${competitionRound.roundId}>${competitionRound.roundName}</a></br>
			</#list>
		</#list>
	</body>
</html>