<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>Buddy recommender</title>
<meta name="description" content="Buddy recommender">
<meta name="keywords" content="">

<!-- Mobile viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">

<!-- CSS-->
<!-- Google web fonts. You can get your own bundle at http://www.google.com/fonts. Don't forget to update the CSS accordingly!-->
<link href='http://fonts.googleapis.com/css?family=Droid+Serif|Ubuntu' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="js/flexslider/flexslider.css">
<link rel="stylesheet" href="css/basic-style.css">
<link href="http://hayageek.github.io/jQuery-Upload-File/uploadfile.min.css" rel="stylesheet">
<style>
#sendMail label.error {
	color: red;
}

#sendMail input.error {
	border: 1px solid red;
}
#sendMail textarea.error {
	border: 1px solid red;
}
#to, #cc, #subject {
	width: 400px;
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
            <li><a href="index.jsp">Recommender</a></li>
            <li class="current"><a href="settings.jsp">Settings</a></li>
		</ul>     
		</nav><!-- #topnav -->
  
    </header><!-- end header -->
 
 
<section id="page-header" class="clearfix">    
<!-- responsive FlexSlider image slideshow -->
<div class="wrapper">
	<h1>Settings</h1>
    </div>

</section>


<!-- main content area -->   
<div class="wrapper" id="main"> 
    
<!-- content area -->    
	<section id="content">

			<span id="importEmployeeDetails">
				<h3>Import employee details</h3>
				<br />

				<p>Upload csv file:</p>
				<div id="uploaderCell">
					<div id="fileuploader">Upload</div>
				</div>
				<div id="eventsmessage"></div>
			</span>
			
			<span id="configureAssigneeTemplate">
				<h3>Configure assignee email template</h3>
				<br />
				
				<form name="sendMail" id="sendMail" action="emailTemplate.do" method="post">
					<table>
						<tr>
							<td><label>Default CC:</label></td>
							<td><input type="text" name="cc" id="cc" /></td>
						</tr>
						<tr>
							<td><label>Default Subject:</label></td>
							<td><input type="text" name="subject" id="subject" /></td>
						</tr>
					</table>
					<p>
						<textarea name="mailContent" id="mailContent" rows=8 cols=50></textarea>
					</p>
					<p>
						<input type="submit" id="submit" name="submit" value="Save template" />
						<input type="hidden" id="formType"
							name="formType" value="assigneeTemplate" />
					</p>
				</form>
				<div id="templateResponse"></div>
			</span>

		</section><!-- #end content area -->
      
      
    <!-- sidebar -->    
    <aside>
        <h2>Options</h2>
            <nav id="secondary-navigation">
                    <ul>
                        <li class="current" name="importEmployeeDetails"><a href="#">Import employee details</a></li>
                        <li name="configureAssigneeTemplate"><a href="#">Configure assignee template</a></li>
                        <li><a href="#">Manage preferences(Under construction)</a></li>
                    </ul>
             </nav>
      </aside><!-- #end sidebar -->
   
  </div><!-- #end div #main .wrapper -->
    


<!-- footer area -->    
<footer>
	<div id="colophon" class="wrapper clearfix">
    	Buddy recommender app
    </div>
    
    <!--You can NOT remove this attribution statement from any page, unless you get the permission from prowebdesign.ro--><div id="attribution" class="wrapper clearfix" style="color:#666; font-size:11px;">Site built with <a href="http://www.prowebdesign.ro/simple-responsive-template/" target="_blank" title="Simple Responsive Template is a free software by www.prowebdesign.ro" style="color:#777;">Simple Responsive Template</a></div><!--end attribution-->
    
</footer><!-- #end footer area --> 


<!-- jQuery -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>
<script>window.jQuery || document.write('<script src="js/libs/jquery-1.9.0.min.js">\x3C/script>')</script>
<script src="http://hayageek.github.io/jQuery-Upload-File/jquery.uploadfile.min.js"></script>
<script defer src="js/flexslider/jquery.flexslider-min.js"></script>

<!-- fire ups - read this file!  -->   
<script src="js/main.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#fileuploader").uploadFile({
			url : "uploader.do",
			fileName : "fileName",
			allowedTypes: "csv",
			onSuccess:function(files,data,xhr)
			{
				$("#eventsmessage").html("");
				$("#eventsmessage").html($("#eventsmessage").html()+"<br/>Successfully imported data.");
				
			},
			onError: function(files,status,errMsg)
			{
				$("#eventsmessage").html("");
				$("#eventsmessage").html($("#eventsmessage").html()+"<br/>Error occurred while uploading the following files: "+JSON.stringify(files));
			}
		});
		
		function switchDiv(divId){
			$("#content span").each(function(index, item){
				if($(item).prop("id") === divId){
					$(item).show();
					$("li[name='"+divId+"']").addClass("current");
				}
				else{
					$(item).hide();
					$("li[name='"+$(item).prop("id")+"']").removeClass("current");
				}
			});
		}
		
		switchDiv("importEmployeeDetails");
		$("li[name='importEmployeeDetails']").click(function(){
			switchDiv("importEmployeeDetails")
		});
		
		$("li[name='configureAssigneeTemplate']").click(function(){
			switchDiv("configureAssigneeTemplate");
			$.ajax({
				type : "POST",
				url : "emailTemplate.do",
				data : {formType:"getAssigneeTemplate"},
				success : function(email) {
					$("#cc").val(email.cc);
					$("#subject").val(email.subject);
					$("#mailContent").val(email.emailContent);
				}
			});
		});
		
		$("#sendMail").validate({
			rules : {
				cc : {
					email : true
				},
				subject : {
					required : true
				},
				mailContent : {
					required : true
				}
			},
			messages : {
				subject : {
					required : "Subject required"
				},
				mailContent : {
					required : "Please enter mail content"
				}
			}
		});

		function submitForm(url){
			if($("#sendMail").valid())
			{
				$("#submit").val("Saving template..");
				$.ajax({
					type : "POST",
					url : url,
					data : $("#sendMail").serialize(),
					success : function(data) {
						$("#templateResponse").html(data);
						$("#submit").val("Save template");
					}
				});
			}
		}
		
		$("#sendMail").submit(
			function() {
				var url = "emailTemplate.do";
				submitForm(url);
				return false;
		});
		
		
	});
</script>
</body>
</html>