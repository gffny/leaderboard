 <!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<!-- <meta name=viewport content=width=device-width,initial-scale=1,maximum-scale=1> -->
		<title>Leaderboard - Mobile Scorecard</title>
		<!--[if lt IE 9]><script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script><![endif]-->
		<!--[if lt IE 9]><script src=http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js></script><![endif]-->
		<link href=css/styles.css rel=stylesheet />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
		<link rel=icon type=image/ico href=http://eaps-www.mit.edu/paoc/sites/all/themes/paoc/favicon.ico>
		<script type=text/javascript src=https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js></script>
		<script type=text/javascript src=http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js></script>
		<script type=text/javascript src=../js/mobilescorecard/mobileApp.js></script>
	</head>
	<body>
		<!-- course details -->
		<div data-role=page id=courseListChoice>
			<h2>Select Course</h2>
			<table>
				<tr>
					<td colspan=2>
						<select>
							<#list courseList as course>
								<option id=${course.courseId}>${course.name}</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<button name=selectCourse id=selectCourse>Select Course</button>
					</td>
					<td>
						<button name=otherCourse id=otherCourse>Other Course</button>
					</td>
				</tr>
			<table>
		</div>
		<div data-role=page id=countryListChoice>
			<table>
				<tr>
					<td>
						<select>
							<#list countryList as country>
								<option id=${country.code}>${country.name}</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<#list countryList as country>
							<table>
								<tr>
									<td>
										${country.name}
									</td>
								</tr>
								<tr>
									<td>
										<select>
											<#list country.stateList as state>
												<option id=${state.code}>${state.name}</option>
											</#list>
										</select>
									</td>
								</tr>
							</table>
						</#list>
					</td>
				</tr>
				<tr>
					<td>
						<button name=showCourses id=showCourses>Show Courses</button>
					</td>
				</tr>
			</table>
		</div>
		<div data-role=page id=courseOverview>
			<h2>Course Overview</h2>
			<table>
				<tr>
					<td>Hole</td><td>Par</td><td>Index</td><td>Distance</td>
				</tr>
			</table>
		</div>
		<!-- error page -->
		<div data-role=page id=errorPage>
		</div>
	</body>
</html>