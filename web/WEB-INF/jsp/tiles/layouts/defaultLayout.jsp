<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title"/></title>
    <link href="${pageContext.request.contextPath}resources/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}<tiles:getAsString name="customStyle"/>" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/material.grey-indigo.min.css' />" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/resources/js/material.min.js"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">
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