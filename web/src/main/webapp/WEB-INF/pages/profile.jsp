<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Profile page</title>
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
                    <a href="${pageContext.request.contextPath}/items"
                       class=" btn btn-primary" aria-pressed="true" role="button">Items</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <form action="${pageContext.request.contextPath}/profile/${user.id}"
                          method="post">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">user id</th>
                                <th scope="col">user email</th>
                                <th scope="col">user name</th>
                                <th scope="col">user surname</th>
                                <th scope="col">user address</th>
                                <th scope="col">user phone</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>${user.id}</th>
                                <th>${user.email}</th>
                                <th>${user.name}</th>
                                <th>${user.surname}</th>
                                <th>${user.profile.address}</th>
                                <th>${user.profile.phone}</th>
                                <td>
                                    <a href="${pageContext.request.contextPath}/profile/profile.update/${user.id}"
                                       class="btn btn-primary"
                                       aria-pressed="true" role="button">Update profile</a>
                                <td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>