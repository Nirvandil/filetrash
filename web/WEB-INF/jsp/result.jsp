<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload finished</title>
    <style type="text/css">
        body {
            background-image: url('https://crunchify.com/bg.png');
        }
        div {
            margin: auto;
            padding-top: 5em;
            text-align: center;
        }
        a {
            text-decoration: none;
            color: black;
        }
        a:visited {
            color: black;
        }
        button {
            margin-left: 1em;
        }
        #toIndex {
            color: red;
        }
        #toIndex a:visited {
            color: red;
        }
    </style>
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
<script src="${pageContext.request.contextPath}/resources/clipboard.js"></script>
</body>
</html>