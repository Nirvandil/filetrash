<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="mdl-grid">
    <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--8-col mdl-cell--4-col-tablet mdl-cell--2-offset">
        <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Put your file and get direct link to it!</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Max upload file size is ${maxUploadSize/(1024*1024)} MegaBytes.
        </div>
        <form:form modelAttribute="uploadedFile" method="POST" action="upload.html" enctype="multipart/form-data">
            <input type=file name="file" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
            <div class="g-recaptcha" data-sitekey="${dataSiteKey}"></div>
            <input type=submit value="Upload" class="mdl-button mdl-js-button mdl-button--raised
                mdl-button--colored">
        </form:form>
    </div>
</div>