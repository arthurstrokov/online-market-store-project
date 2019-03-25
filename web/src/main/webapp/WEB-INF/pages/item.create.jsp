<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Item create page</title>
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
                <a href="${pageContext.request.contextPath}/store" class=" btn btn-primary"
                   aria-pressed="true" role="button">Store</a>
            </div>
            <form:form action="${pageContext.request.contextPath}" modelAttribute="item" method="post">
                <form:errors path="*" cssClass="error" element="div"/>
                <div class="form-group">
                    <form:input path="name" class="form-control" placeholder="Name" required="true"/>
                </div>
                <div class="form-group">
                    <form:input path="description" class="form-control" placeholder="Description" required="true"/>
                </div>
                <div class="form-group">
                    <form:input path="price" class="form-control" pattern="[0-9]{0,10}$" placeholder="Price"
                                required="true"/>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>