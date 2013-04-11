<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form action=persist method=post>
			<label>Competition Name: ${competition.competitionName}</label>
			<input type=hidden name=competitionId value=${competition.competitionId} />
			<br />
			<label>Competition Visibity: ${competition.competitionVisibility}</label>
			<br />
			<#list 1..numberOfRounds as roundNumber>
				<h3>Round ${roundNumber}</h3>
				<label for=${competition.competitionId}.${roundNumber?string.computer}.rndNm>Round Name</label>
				<input name="${competition.competitionId}.${roundNumber?string.computer}.rndNm" value="${competition.competitionName} Round ${roundNumber?string.computer}" />
				<br />
				<label for=${competition.competitionId}.${roundNumber?string.computer}.rndGrpSz>Round Group Size</label>
				<select name=${competition.competitionId}.${roundNumber?string.computer}.rndGrpSz>
					<option name=name=${competition.competitionId}.${roundNumber?string.computer}.rndGrpSz value=2>2 - Two</option>
					<option name=name=${competition.competitionId}.${roundNumber?string.computer}.rndGrpSz value=3>3 - Three</option>
					<option name=name=${competition.competitionId}.${roundNumber?string.computer}.rndGrpSz value=4>4 - Four</option>
				</select>
				<br />
				<label for=${competition.competitionId}.${roundNumber?string.computer}.rndDt>Round Date</label>
				<input name=${competition.competitionId}.${roundNumber?string.computer}.rndDt type=date />
				<br />
				<select name=${competition.competitionId}.${roundNumber?string.computer}.rndCrs>
				<label for=${competition.competitionId}.${roundNumber?string.computer}.rndCrs>Course</label>
				<#list courseList as course>
					<option name=${competition.competitionId}.${roundNumber?string.computer}.rndCrs value=${course.courseId}>${course.name} - ${course.teeColour?capitalize}</option>
				</#list>
				</select>
			</#list>
			<br />
			<input type=submit value=next />
		</form>
	</body>
</html>