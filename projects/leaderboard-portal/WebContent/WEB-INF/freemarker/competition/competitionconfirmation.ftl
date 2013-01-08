<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form action=rounds method=post>
			<label for=competitionName>Competition Name</label>
			${competition.competitionName}
			<br />
			<label for=competitionVisibilty>Competition Visibility</label>
			${competition.competitionVisibility}
			<br />
			<label for=competitionScoringSystem>Competition Scoring System</label>
			
			<br />
			<label for=competitionRounds>Number of Competition Rounds</label>
			<input name=competitionRounds />
			<br />
			<input type=submit value=next />
		</form>
	</body>
</html>