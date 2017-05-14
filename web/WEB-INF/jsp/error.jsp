<%--
  Created by IntelliJ IDEA.
  User: nirvana
  Date: 13.05.17
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        Sorry to inform you, but an error occurred and we unable to process you request. An error was:<br> ${errorMessage}
    </p>
    <p id="toIndex">
        <a href="${pageContext.request.contextPath}/index.html">Go to main page</a>
    </p>
</div>
</body>
</html>
