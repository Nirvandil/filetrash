<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload finished</title>
    <style type="text/css">
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
            color: red;
        }
        #toIndex {
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
            <a href="${url}">${url}</a> <br>
        </p>
        <p id="toIndex">
            <a href="${pageContext.request.contextPath}/index.html">Go to main page</a>
        </p>
    </div>
</body>
</html>