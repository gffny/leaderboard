<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form action=rounds method=post>
			<label for=competitionName>Competition Name</label>
			<input type=text name=competitionName />
			<br />
			<label for=competitionVisibilty>Competition Visibility</label>
			<select name=competitionVisibility>
				<option name=private value=private>Private</option>
				<option name=public value=public>Public</option>
				<option name=society value=society>Society</option>
			</select>
			<br />
			<label for=competitionScoringSystem>Competition Scoring System</label>
			<select name=competitionScoringSystem>
				<#list competitionScoringSystemList as competitionScoringSystem>
				<option name=competitionScoringSystem value=${competitionScoringSystem.name}>${competitionScoringSystem.name}</option>
				</#list>
			</select>
			<br />
			<label for=competitionRounds>Number of Competition Rounds</label>
			<input name=competitionRounds />
			<br />
			<input type=submit value=next />
		</form>
	</body>
</html>