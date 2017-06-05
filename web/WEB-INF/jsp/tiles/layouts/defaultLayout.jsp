<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title"/></title>
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet"/>
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.grey-indigo.min.css"/>
    <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>
<div class="mdl-layout mdl-js-layout">
    <header id="header" class="mdl-layout__header mdl-layout__header--waterfall">
        <tiles:insertAttribute name="header"/>
    </header>

    <main id="site-content" class="mdl-layout__content">
        <tiles:insertAttribute name="body"/>
    </main>

    <footer id="footer" class="mdl-mini-footer">
        <tiles:insertAttribute name="footer"/>
    </footer>
</div>
</body>
</html>