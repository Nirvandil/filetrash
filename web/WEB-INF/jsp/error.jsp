<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Something wrong</title>
    <style type="text/css">
        body {
            background-image: url('https://crunchify.com/bg.png');
        }
        div, p {
            text-align: center;
            margin: auto;
            padding-top: 2em;
        }
        a {
            text-decoration: none;
            color: red;
        }
        a:visited {
            color: red;
        }
    </style>
</head>
<body>
<div>
    <p>
        Sorry to inform you, but an error occurred and we unable to process you request. An error was:<br>
    </p>
        <h4>
            ${errorMessage}
        </h4>
    <p id="toIndex">
        <a href="${pageContext.request.contextPath}/index.html">Go to main page</a>
    </p>
</div>
</body>
</html>
