<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Card page</title>
</head>
<html lang="en">
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <div class="form-group">
                <a href="${pageContext.request.contextPath}/logout" class=" btn btn-primary"
                   aria-pressed="true" role="button">Log Out</a>
                <a href="${pageContext.request.contextPath}/items" class=" btn btn-primary"
                   aria-pressed="true" role="button">Items</a>
            </div>
            <form:form action="${pageContext.request.contextPath}" modelAttribute="buisnessCard" method="post">
                <form:errors path="*" cssClass="error" element="div"/>
                <div class="form-group">
                    <form:input path="title" class="form-control" placeholder="title" required="true"/>
                </div>
                <div class="form-group">
                    <form:input path="fullName" class="form-control" placeholder="fullName" required="true"/>
                </div>
                <div class="form-group">
                    <form:input path="workingTelephone" class="form-control" placeholder="37529xxxxxxx"
                                type="tel" pattern="375[0-9]{2}[0-9]{7}" required="true"/>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>