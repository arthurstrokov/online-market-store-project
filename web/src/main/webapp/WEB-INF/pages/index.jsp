<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Register page</title>
</head>
<html lang="en">
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}" modelAttribute="user" method="post">
                <form:errors path="*" cssClass="error" element="div"/>
                <div class="form-group">
                    <form:label path="name">Name</form:label>
                    <form:input path="name" class="form-control" placeholder="Name" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="surname">Surname</form:label>
                    <form:input path="surname" class="form-control" placeholder="Surname" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="profile.phone">Phone</form:label>
                    <form:input path="profile.phone" class="form-control" placeholder="37529xxxxxxx"
                                type="tel" pattern="375[0-9]{2}[0-9]{7}" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="profile.address">Address</form:label>
                    <form:input path="profile.address" class="form-control" placeholder="Address" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="email">Email</form:label>
                    <form:input path="email" class="form-control" placeholder="email" required="true"
                                type="email" aria-describedby="emailHelp"/>
                    <small id="emailHelp" class="form-text text-muted">We will never share your email with anyone else.
                    </small>
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input path="password" class="form-control" placeholder="Password"
                                type="password" required="true"/>
                </div>
                <button type="submit" class="btn btn-primary">Sign In</button>
                <input type="button" class="btn btn--primary" value="Log In"
                       onclick='location.href="/login"'>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>