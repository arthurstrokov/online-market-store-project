<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Update profile</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <a href="${pageContext.request.contextPath}/logout" class=" btn btn-primary"
                       aria-pressed="true" role="button">Log Out</a>
                    <a href="${pageContext.request.contextPath}/profile"
                       class=" btn btn-primary" aria-pressed="true" role="button">Profile</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:form action="${pageContext.request.contextPath}/profile/profile.update/${user.id}"
                               modelAttribute="user"
                               method="post">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">user id</th>
                                <th scope="col">user name</th>
                                <th scope="col">user surname</th>
                                <th scope="col">user phone</th>
                                <th scope="col">user address</th>
                                <th scope="col">user email</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>${user.id}</th>
                                <th>${user.name}</th>
                                <th>${user.surname}</th>
                                <th>${user.profile.phone}</th>
                                <th>${user.profile.address}</th>
                                <th>${user.email}</th>
                            </tr>
                            </tbody>
                        </table>
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
                            <form:input path="profile.address" class="form-control" placeholder="Address"
                                        required="true"/>
                        </div>
                        <div class="form-group">
                            <form:label path="password">Password</form:label>
                            <form:input path="password" class="form-control" placeholder="Password"
                                        type="password" required="true"/>
                        </div>
                        <button type="submit" class="btn btn-primary my-1">Update profile</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>