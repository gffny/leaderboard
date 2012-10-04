<form action="create/schedule" method=post>
	<table>
		<td>
			<table align=left>
         		<select size=12 name=golferList multiple>
				<#list golferList as golfer>
					<option value=${golfer.userId}>${golfer.firstName} ${golfer.lastName}</option> 
<!--				<tr><td><input type=checkbox name=${golfer.userId} value=${golfer.userId} /></td><td>${golfer.firstName}</td><td>${golfer.lastName}</td></tr>-->
					
				</#list>
				</select>
			</table>
		</td>
		<td>
			Country <br/>
			<input list=country name=country>
				<datalist id=country>
					<#list countryList as country>
						<option id=${country.code} value="${country.code}">${country.name}</option>
					</#list>
				</datalist>
			</input>
		</td>
		<td>
			State <br />
			<input list=state name=state>
				<datalist id=state>
					<option id=MA value=MA>Massachussetts</option>
					<option id=NH value=NH>New Hampshire</option>
					<option id=FL value=FL>Florida</option>
				</datalist>
			</input>
		</td>
		<td>
			Golf Course<br />
			<select name=golfCourse>
				<option id=123>Newton Commonwealth</option>
				<option id=234>Fresh Pond</option>
				<option id=345>Plymouth</option>
			</select>
		</td>
		<td>
			Competition Begin Date<br />
			<input type=date name=competitionBeginDate /><br />
			Competition End Date<br />
			<input type=date name=competitionEndDate />
		</td>
		<td>
			Competition Tee Time</br>
			<input type=text name=teeTime />
		</td>
		<td>
			<input type=submit value="Create Competition" />
		</td>
	</table>
	
</form>
