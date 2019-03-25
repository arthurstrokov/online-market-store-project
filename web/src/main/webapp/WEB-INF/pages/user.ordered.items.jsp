<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Ordered products page</title>
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
                                <th scope="col">order id</th>
                                <th scope="col">user id</th>
                                <th scope="col">item price</th>
                                <th scope="col">item name</th>
                                <th scope="col">created</th>
                                <th scope="col">quantity</th>
                                <th scope="col">status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <th scope="row"><label>
                                        <input type="checkbox" name="ids" value="${order.id}">
                                    </label></th>
                                    <th>${order.id}</th>
                                    <th>${order.user.id}</th>
                                    <th>${order.item.price}</th>
                                    <th>${order.item.name}</th>
                                    <th>${order.created}</th>
                                    <th>${order.quantity}</th>
                                    <th>${order.status}</th>
                                    <securiry:authorize access="hasAuthority('SECURITY')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/orders/user.ordered.items/${order.id}/delete"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Remove order</a>
                                        </td>
                                    </securiry:authorize>
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