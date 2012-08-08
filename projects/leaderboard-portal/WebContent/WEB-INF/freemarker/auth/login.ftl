<h1>Leaderboard Login</h1>
<form action="/j_spring_security_check" method="POST">
	<label for="username">User Name:</label>
	<input id="username" name="j_username" type="text"/>
	<label for="password">Password:</label>
	<input id="password" name="j_password" type="password"/>
	<input type="submit" value="log in"/>
</form>

<form action=j_spring_openid_security_check id=googleOpenId method=post target=_top>
    <input id=openid_identifier name=openid_identifier type=hidden value=https://www.google.com/accounts/o8/id />
    <img src=/img/social/google.png onClick=submitForm('googleOpenId') />
</form>