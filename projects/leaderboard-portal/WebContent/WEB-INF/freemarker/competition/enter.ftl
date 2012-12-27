<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<table width=100%>
			<thead>
				<#assign numberOfHoles = competitionRound.holeListLength>
				<tr><td></td><td colspan=${numberOfHoles}>Hole</td></tr>
				<tr><td>Golfer</td>
					<#list 1..competitionRound.holeListLength as holeNumber> 
					<td>${holeNumber}</td>
					</#list>
				</tr>
			</thead>
			<tbody>
				<form action=/leaderboard/competition/scorecard/save autocomplete=off method=POST>
					<input type=hidden name=userId value=123 />
					<input type=hidden name=competitionRoundId value=123 />
					<#list competitorList as competitor>
						<tr>
							<td>${competitor.firstName} ${competitor.lastName}</td>
							<#list 1..numberOfHoles as holeNumber>
							<td>
								<input name=${competitor.userId}.${competitionRound.roundId}.${holeNumber} />
							</td>
							</#list>
						</tr>
					</#list>
					<input type=submit value=Submit />
				</form>
			</tbody>
		</table>
	</body>
</html>