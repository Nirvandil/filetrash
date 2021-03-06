<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="mdl-grid">
    <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--8-col mdl-cell--4-col-tablet mdl-cell--2-offset">
        <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Put your file and get direct link to it!</h2>
        </div>
        <div class="mdl-card__supporting-text">
            <p><strong>Max upload file size is
                <span id="maxFileSize"> ${maxUploadSize/(1024*1024)}</span> MegaBytes.</strong> </p>
            <form:form modelAttribute="uploadedFile" method="POST" action="upload.html" enctype="multipart/form-data">
                <input type=file name="file" required
                       class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                <br>
                <div id="captcha" class="g-recaptcha" data-sitekey="${dataSiteKey}"></div>
                <div>
                    <small>By clicking "Upload" you confirm that your read and accept
                        <a href="${pageContext.request.contextPath}/terms.html" target="_blank">terms of use</a></small>
                </div>
                <br>
                <input type=submit value="Upload" class="mdl-button mdl-js-button mdl-button--raised
                mdl-button--colored mdl-js-ripple-effect">
            </form:form>
        </div>
    </div>
</div>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>