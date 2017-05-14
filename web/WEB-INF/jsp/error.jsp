<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Something wrong</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
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
