<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Products page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}/orders/user.ordered.items" method="get">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout"
                           class=" btn btn-primary" aria-pressed="true" role="button">Log Out</a>
                        <button type="submit" class="btn btn-primary">Ordered Items</button>
                        <a href="${pageContext.request.contextPath}/news"
                           class=" btn btn-primary" aria-pressed="true" role="button">News</a>
                        <a href="${pageContext.request.contextPath}/profile"
                           class=" btn btn-primary" aria-pressed="true" role="button">Profile</a>
                        <a href="${pageContext.request.contextPath}/buisness/user.cards"
                           class=" btn btn-primary" aria-pressed="true" role="button">Buisness Cards</a>
                        <a href="${pageContext.request.contextPath}/buisness/card.create"
                           class=" btn btn-primary" aria-pressed="true" role="button">Create Buisness Cards</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Price</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="items" scope="request" type="java.util.List"/>
                            <c:forEach items="${items}" var="items">
                                <tr>
                                    <th scope="row"><label>
                                        <input type="checkbox" name="ids" value="${items.id}">
                                    </label></th>
                                    <th>${items.name}</th>
                                    <th>${items.description}</th>
                                    <th>${items.price}</th>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/items/${items.id}/order"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Make an order</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Navigation">
                            <ul class="pagination">
                                <c:forEach var="page" begin="1" end="${pages}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/items?page=${page}" }>${page}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>