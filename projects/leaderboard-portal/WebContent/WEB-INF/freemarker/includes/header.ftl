<!DOCTYPE html>
	<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ --> 
	<!--[if lt IE 7 ]>
		<html class="no-js ie6 ie">
	<![endif]-->
	<!--[if IE 7 ]>
		<html class="no-js ie7 ie">
	<![endif]-->
	<!--[if IE 8 ]>
		<html class="no-js ie8 ie">
	<![endif]-->
	<!--[if IE 9 ]>
		<html class="no-js ie9 ie">
	<![endif]-->
	<!--[if (gte IE 10)|!(IE)]><!-->
		<html class="no-js">
	<!--<![endif]-->
<head>
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

	<title>${pageTitle!"LeaderBoard Golf Application"}</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-language" content="en" />

	<!--  Mobile viewport optimized: j.mp/bplateviewport -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

	$staticAsset.css("/css/global/main.css")
	$staticAsset.css("/css/global/buttons.css")
	$staticAsset.css("/css/global/icons.css")
	$staticAsset.css("/css/plugins/jquery.cleditor/jquery.cleditor.css")
	$staticAsset.css($portalConfiguration.getTheme().getCssPath())
	<link rel = "stylesheet" href = "$staticAsset.staticUrl("/css/global/print.css")" type = "text/css" media = "print" />
	
	$staticAsset.js("/js/lib/jquery-1.5.2.min.js")
	$staticAsset.js("/js/application/plugins.js")
	$staticAsset.js("/js/application/healthways.js")
</head>
<body class="$!staticContentManager.getLocale()">