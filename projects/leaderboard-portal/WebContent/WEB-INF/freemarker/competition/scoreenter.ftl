<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
				<td></td><td colspan=${competitionRound.holeListLength}>Hole</td>
				<td>Golfer</td>
	
				<#list 1..${competitionRound.holeListLength} as x> 
					<td>${x}</td>
				</#list>
		</thead>
	</table>

</body>
</html>