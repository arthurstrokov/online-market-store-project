<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>User orders</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}/users" method="get">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Log out</a>
                        <button type="submit" class="btn btn-primary">Users</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">order id</th>
                                <th scope="col">user id</th>
                                <th scope="col">item id</th>
                                <th scope="col">item name</th>
                                <th scope="col">created</th>
                                <th scope="col">quantity</th>
                                <th scope="col">status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="orders" scope="request" type="java.util.List"/>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <th>${order.id}</th>
                                    <th>${order.user.id}</th>
                                    <th>${order.item.id}</th>
                                    <th>${order.item.name}</th>
                                    <th>${order.created}</th>
                                    <th>${order.quantity}</th>
                                    <th>${order.status}</th>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/orders/user.orders.change.status/${order.id}"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Change status</a>
                                    </td>
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

