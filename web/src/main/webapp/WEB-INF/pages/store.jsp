<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Store page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}" method="post"
                  enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Log out</a>
                        <a href="${pageContext.request.contextPath}/users"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Users</a>
                        <a href="${pageContext.request.contextPath}/item.create"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Create Item</a>
                        <a href="${pageContext.request.contextPath}/upload"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Upload Items</a>
                        <a href="${pageContext.request.contextPath}/news/news.create"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Create news</a>
                        <a href="${pageContext.request.contextPath}/news"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">News</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">id</th>
                                <th scope="col">name</th>
                                <th scope="col">description</th>
                                <th scope="col">unique number</th>
                                <th scope="col">price</th>
                                <th scope="col">isAlive</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${items}" var="item">
                                <tr>
                                    <th scope="row"><input type="checkbox" name="ids" value="${item.id}"></th>
                                    <th>${item.id}</th>
                                    <th>${item.name}</th>
                                    <th>${item.description}</th>
                                    <th>${item.uniqueNumber}</th>
                                    <th>${item.price}</th>
                                    <th>${item.alive}</th>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/store/${item.id}/softdelete"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Soft delete</a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/store/${item.id}/restore"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Restore item</a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/store/${item.id}/copy"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Copy</a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/store/${item.id}/delete"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Delete</a>
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
                                           href="${pageContext.request.contextPath}/store?page=${page}" }>${page}</a>
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