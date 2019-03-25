<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>We've got some trouble | 401 - Unauthorized</title>
    <title>Error page</title>
</head>
<body>
<h2>Application Error, please contact support!</h2>
<h3>Debug Information:</h3>
Requested URL=${url}<br><br>
Exception=${exception.message}<br><br>
<strong>Exception Stack Trace</strong><br>
<c:forEach items="${exception.stackTrace}" var="steck">
    ${steck}
</c:forEach>
</body>
</html>