<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Simple file exchanger</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
	<div>
		<H3>
			Just put your file and get direct link to it!
		</H3>
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