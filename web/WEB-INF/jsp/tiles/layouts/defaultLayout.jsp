<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/resources/css/style.css' />"  rel="stylesheet"/>
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>
<header id="header">
    <tiles:insertAttribute name="header" />
</header>

<section id="site-content">
    <tiles:insertAttribute name="body" />
</section>

<footer id="footer">
    <tiles:insertAttribute name="footer" />
</footer>
</body>
</html>