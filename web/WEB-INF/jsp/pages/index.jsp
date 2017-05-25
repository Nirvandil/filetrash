<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
	<h3>
		Just put your file and get direct link to it!
	</h3>
	<h4>
		Max upload file size is ${maxUploadSize/(1024*1024)} MegaBytes.
	</h4>
</div>
	<div style="font-family: verdana,sans-serif; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
	<form:form modelAttribute="uploadedFile" method="POST" action="upload.html" enctype="multipart/form-data">
		<input type=file name="file">
        <div class="g-recaptcha" data-sitekey="${dataSiteKey}"></div>
		<input type=submit value="Upload">
	</form:form>
	</div>