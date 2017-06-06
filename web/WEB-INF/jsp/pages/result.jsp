<div class="mdl-grid">
    <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--8-col mdl-cell--4-col-tablet mdl-cell--2-offset">
        <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Your URL is: </h2>
        </div>
        <div class="mdl-card__supporting-text">
            <a href="${url}" class="url">${url}</a>
            <button
                    title="To clipboard" id="copyUrl"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">Copy
            </button>
            <br>
        </div>
        <div id="toIndex">
            <a href="${pageContext.request.contextPath}/index.html">Go to main page</a>
        </div>
    </div>
    </div>
<script src="${pageContext.request.contextPath}/resources/js/clipboard.js"></script>