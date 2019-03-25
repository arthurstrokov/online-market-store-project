<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Buisness Card page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}/items" method="get">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout" class=" btn btn-primary"
                           aria-pressed="true" role="button">Log Out</a>
                        <button type="submit" class="btn btn-primary">Items</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">buisnessCard id</th>
                                <th scope="col">buisnessCard title</th>
                                <th scope="col">buisnessCard full name</th>
                                <th scope="col">buisnessCard workingTelephone</th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="buisnessCards" scope="request" type="java.util.List"/>
                            <c:forEach items="${buisnessCards}" var="buisnessCard">
                                <tr>
                                    <th scope="row"><label>
                                        <input type="checkbox" name="ids" value="${buisnessCard.id}">
                                    </label></th>
                                    <th>${buisnessCard.id}</th>
                                    <th>${buisnessCard.title}</th>
                                    <th>${buisnessCard.fullName}</th>
                                    <th>${buisnessCard.workingTelephone}</th>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>