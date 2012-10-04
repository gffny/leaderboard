${competitionRound.roundName} <br />
${competitionRound.roundNumber} <br />
${competitionRound.dateAsString} <br />
<#list competitionRound.groupList as group>
	${group.groupName}<br/>
	<#list group.golferList as golfer>
		${golfer.firstName} ${golfer.lastName} <br />
	</#list>
</#list>
