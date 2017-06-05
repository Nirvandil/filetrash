<div class="mdl-layout mdl-js-layout">
    <main class="mdl-layout__content">
        <H4>
            Your URL is <br> <br>
        </H4>
        <div>
            <a href="${url}" class="url">${url}</a>
            <button
                    title="To clipboard" id="copyUrl"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Copy
            </button>
            <br>
        </div>
        <div id="toIndex">
            <a href="${pageContext.request.contextPath}/index.html">Go to main page</a>
        </div>
    </main>
    </div>
<script src="${pageContext.request.contextPath}/resources/js/clipboard.js"></script>