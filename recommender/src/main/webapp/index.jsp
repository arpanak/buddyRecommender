<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>Buddy recommender</title>
<meta name="description" content="Buddy recommender">
<meta name="keywords" content="">

<!-- Mobile viewport -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">


<!-- CSS-->
<!-- Google web fonts. You can get your own bundle at http://www.google.com/fonts. Don't forget to update the CSS accordingly!-->
<link href='http://fonts.googleapis.com/css?family=Droid+Serif|Ubuntu'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="js/flexslider/flexslider.css">
<link rel="stylesheet" href="css/basic-style.css">
<style>
#responseContent td {
	border-bottom: 1px solid #e2e2e2;
	border-top: 1px solid #e2e2e2;
	padding-right: 15px;
	padding-left: 15px;
}

#joineeDetailsForm label.error {
	color: red;
}

#joineeDetailsForm input.error {
	border: 1px solid red;
}

.suggestBuddies{
	  display: block;
  float: left;
  margin: 10px 15px 10px 0;
  padding: 10px;
  text-decoration: none;
}
</style>
<!-- end CSS-->

<!-- JS-->
<script src="js/libs/modernizr-2.6.2.min.js"></script>
<!-- end JS-->


</head>

<body id="home">

	<!-- header area -->
	<header class="wrapper clearfix">

		<div id="banner">
			<div id="logo">
				<a href="index.jsp"><img src="images/logo.png" alt="logo"></a>
			</div>
		</div>

		<!-- main navigation -->
		<nav id="topnav" role="navigation">
			<div class="menu-toggle">Menu</div>
			<ul class="srt-menu" id="menu-main-navigation">
				<li class="current"><a href="index.jsp">Home page</a></li>
				<li><a href="settings.jsp">Settings</a></li>
			</ul>
		</nav>
		<!-- end main navigation -->

	</header>
	<!-- end header -->



	<section id="page-header" class="clearfix">
		<div class="wrapper">
			<h1>Buddy recommender</h1>
		</div>
	</section>

	<!-- main content area -->
	<div id="main" class="wrapper">


		<br />
		<form action="recommender.do" method="post" id="joineeDetailsForm">
			<table>
				<tr>
					<td><label>Joinee's college:</label></td>
					<td><input type="text" name="college" /></td>
				</tr>
				<tr>
					<td><label>Joinee's year of graduation:</label></td>
					<td><input type="text" name="passoutyear" /></td>
				</tr>
				<tr>
					<td><label>Joinee's team:</label></td>
					<td><input type="text" name="team"  /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Suggest buddies" id="suggestBuddies" class="suggestBuddies"/></td>
				</tr>
			</table>
			<label></label>
		</form>
		<br />
		<div id="responseContent"></div>


	</div>
	<!-- #end div #main .wrapper -->


	<!-- footer area -->
	<footer>
		<div id="colophon" class="wrapper clearfix">Buddy recommender
			app</div>

		<!--You can NOT remove this attribution statement from any page, unless you get the permission from prowebdesign.ro-->
		<div id="attribution" class="wrapper clearfix"
			style="color: #666; font-size: 11px;">
			Site built with <a
				href="http://www.prowebdesign.ro/simple-responsive-template/"
				target="_blank"
				title="Simple Responsive Template is a free software by www.prowebdesign.ro"
				style="color: #777;">Simple Responsive Template</a>
		</div>
		<!--end attribution-->

	</footer>
	<!-- #end footer area -->


	<!-- jQuery -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"
type="text/javascript"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/libs/jquery-1.9.0.min.js">\x3C/script>')
	</script>

	<script defer src="js/flexslider/jquery.flexslider-min.js"></script>

	<!-- fire ups - read this file!  -->
	<script src="js/main.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {

					$("#joineeDetailsForm").validate(
						      {
						        rules: 
						        {
						          college: 
						          {
						            required: true
						          },
						          passoutyear: 
						          {
						            required: true
						          },
						          team: 
						          {
						            required: true
						          }
						        },
						        messages: 
						        {
						          college: 
						          {
						            required: "Please enter joinee's college"
						          },
						          passoutyear: 
						          {
						            required: "Please enter joinee's year of graduation"
						          }	,
						          team: 
						          {
						            required: "Please enter joinee's team"
						          }	
						        }
						      });   

					$("#joineeDetailsForm").submit(
							function() {
								var url = "recommender.do";
								if($("#joineeDetailsForm").valid())
								{
									$.ajax({
										type : "POST",
										url : url,
										data : $("#joineeDetailsForm").serialize(),
										success : function(data) {
											$("#responseContent").html(data);
											$("#responseContent tr:even").css(
													"background-color", "#f3f3f3");
										}
									});
								}
								
								return false;
								
							});
				})
	</script>
</body>
</html>

