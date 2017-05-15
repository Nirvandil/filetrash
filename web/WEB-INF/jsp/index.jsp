<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Simple file exchanger</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
	<div>
		<H3>
			Just put your file and get direct link to it!
		</H3>
		<h4>
			Max upload file size is ${maxUploadSize/(1024*1024)} MegaBytes.
		</h4>
	</div>
	<div style="font-family: verdana,sans-serif; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
	<form method="POST" action="upload.html" enctype="multipart/form-data">
		<input type=file name="file">
        <div class="g-recaptcha" data-sitekey="${dataSiteKey}"></div>
		<input type=submit value="Upload">
	</form>
	</div>
</body>
</html>