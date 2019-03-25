<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Login page</title>
</head>
<body class="text-center">
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <form:errors path="*" cssClass="error" element="div"/>
                <div class="form-group">
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" name="email" value="${email}" id="inputEmail" class="form-control"
                           placeholder="Email address"
                           required
                           autofocus>
                    <div>
                        <div class="form-group">
                            <input type="password" name="password" value="${password}" id="inputPassword"
                                   class="form-control"
                                   placeholder="Password" required>
                            <div class="checkbox mb-3">
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
                            </div>
                            <input type="button" class="btn btn-lg btn-secondary btn-block" value="Sign in"
                                   onclick='location.href="/"'>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>
