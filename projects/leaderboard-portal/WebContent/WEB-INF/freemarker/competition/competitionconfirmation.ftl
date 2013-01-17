<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form action=rounds method=post>
			<label for=competitionName>Competition Name:</label>
			${competition.competitionName} <br />
			<label for=competitionVisibilty>Competition Visibility:</label>
			${competition.competitionVisibility} <br />
			<label for=competitionScoringSystem>Competition Scoring System:</label>
			${competition.competitionScoringSystem}	<br />
			<#list competition.competitionRoundList as competitionRound>
				${competitionRound.roundName}<br />
			</#list>
			<#if competitionUrl??>Competition URL <a href=${competitionUrl}>(${competitionUrl})</a></#if>
		</form>
	</body>
</html>