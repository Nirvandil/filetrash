<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload finished</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">
</head>
<body>
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
</body>
</html>