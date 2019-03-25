<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>News page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}/items" method="get">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout"
                           class=" btn btn-primary" aria-pressed="true" role="button">Log Out</a>
                        <securiry:authorize access="hasAuthority('CUSTOMER')">
                            <button type="submit" class="btn btn-primary">Items</button>
                        </securiry:authorize>
                        <securiry:authorize access="hasAuthority('VIEW_STORE')">
                            <a href="${pageContext.request.contextPath}/store"
                               class=" btn btn-primary"
                               aria-pressed="true" role="button">Store</a>
                        </securiry:authorize>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">id</th>
                                <th scope="col">title</th>
                                <th scope="col">content</th>
                                <th scope="col">created</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${news}" var="news">
                                <tr>
                                    <th>${news.id}</th>
                                    <th>${news.title}</th>
                                    <th>${news.content}</th>
                                    <th>${news.created}</th>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/news/news.read.comments/${news.id}"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Read comments</a>
                                    </td>
                                    <securiry:authorize access="hasAuthority('CREATE_COMMENTS')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/news/news.create.comments/${news.id}"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Create comments</a>
                                        </td>
                                    </securiry:authorize>
                                    <td>
                                        <securiry:authorize access="hasAuthority('UPDATE_NEWS')">
                                        <a href="${pageContext.request.contextPath}/news/news.update/${news.id}"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Uptate news</a>
                                        </securiry:authorize>
                                        <securiry:authorize access="hasAuthority('DELETE_NEWS')">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/news/${news.id}/delete"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Delete news</a>
                                    </td>
                                    </securiry:authorize>
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
                                           href="${pageContext.request.contextPath}/news?page=${page}" }>${page}</a>
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