<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>News</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}/news" method="get">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Log out</a>
                        <button type="submit" class="btn btn-primary">News</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">comment id</th>
                                <th scope="col">content</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${comments}" var="comment">
                                <tr>
                                    <th scope="row"><label>
                                        <input type="checkbox" name="ids" value="${comment.id}">
                                    </label></th>
                                    <th>${comment.id}</th>
                                    <th>${comment.content}</th>
                                    <securiry:authorize access="hasAuthority('DELETE_COMMENTS')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/news/news.read.comments/${comment.id}/delete"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Delete comment</a>
                                        </td>
                                    </securiry:authorize>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Navigation">
                            <ul class="pagination">
                                <c:forEach var="page" begin="1" end="${pages}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/news/news.read.comments/${id}?page=${page}"
                                           }>${page}</a>
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