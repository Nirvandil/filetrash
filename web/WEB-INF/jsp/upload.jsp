<!DOCTYPE html>
<html>
<head>
<title>Simple file exchanger</title>
<style type="text/css">
body {
	background-image: url('https://crunchify.com/bg.png');
}
div {
	text-align: center;
	margin: auto;
	padding: 1em;
}
</style>
</head>
<body>${message}
	<div style="font-family: verdana,sans-serif; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
	<form method="POST" action="upload.html" enctype="multipart/form-data">
		<input type=file name="file">
		<input type=submit value="Upload">
	</form>
	</div>
</body>
</html>