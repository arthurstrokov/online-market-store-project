<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Change role</title>
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
                    <a href="${pageContext.request.contextPath}/users"
                       class=" btn btn-primary" aria-pressed="true" role="button">Users</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form action="${pageContext.request.contextPath}/users/user.role/${user.id}"
                          method="post">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">user id</th>
                                <th scope="col">user name</th>
                                <th scope="col">role</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>${user.id}</th>
                                <th>${user.name}</th>
                                <th>${user.role.name}</th>
                            </tr>
                            </tbody>
                        </table>
                        <securiry:authorize access="hasAuthority('VIEW_USERS')">
                            <label class="my-1 mr-2" for="inlineFormCustomSelectPref">Role</label>
                            <select name="role" class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                                <option value="1">SECURITY_USER</option>
                                <option value="2">SALE_USER</option>
                                <option value="3">CUSTOMER_USER</option>
                                <option value="4">API_USER</option>
                            </select>
                            <button type="submit" class="btn btn-primary my-1">Change role</button>
                        </securiry:authorize>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>
