<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Update news</title>
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
                    <a href="${pageContext.request.contextPath}/news"
                       class=" btn btn-primary" aria-pressed="true" role="button">News</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:form action="${pageContext.request.contextPath}/news/news.update/${news.id}"
                               modelAttribute="news"
                               method="post">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">news id</th>
                                <th scope="col">news title</th>
                                <th scope="col">news content</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>${news.id}</th>
                                <th>${news.title}</th>
                                <th>${news.content}</th>
                            </tr>
                            </tbody>
                        </table>
                        <label class="my-1 mr-2">Title</label>
                        <div class="form-group">
                            <form:input path="title" class="form-control" placeholder="title" required="true"/>
                        </div>
                        <label class="my-1 mr-2">Content</label>
                        <div class="form-group">
                            <form:input path="content" class="form-control" placeholder="content" required="true"/>
                        </div>
                        <button type="submit" class="btn btn-primary my-1">Update news</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>