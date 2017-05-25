
    <div>
        <H4>
            Your URL is <br> <br>
        </H4>
        <p>
            <a href="${url}" class="url">${url}</a> <button title="To clipboard" id="copyUrl">Copy</button> <br>
        </p>
        <p id="toIndex">
            <a href="${pageContext.request.contextPath}/index.html">Go to main page</a>
        </p>
    </div>
<script src="${pageContext.request.contextPath}/resources/js/clipboard.js"></script>