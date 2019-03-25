<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>News create page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-12">
            <form:form action="${pageContext.request.contextPath}/news/news.create" modelAttribute="news" method="post">
            <form:errors path="*" cssClass="error" element="div"/>
            <div class="form-group">
                <a href="${pageContext.request.contextPath}/logout"
                   class=" btn btn-primary" aria-pressed="true" role="button">Log out</a>
                <a href="${pageContext.request.contextPath}/store"
                   class=" btn btn-primary" aria-pressed="true" role="button">Store</a>
                <br>
                <div class="form-group">
                    <form:input path="title" type="text" class="form-control" placeholder="title" required="true"/>
                </div>
                <br>
                <textarea type="text" name="content" class="form-control"
                          id="content" placeholder="content" required rows="10" cols="30"></textarea>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Create news</button>
                </div>
                </form:form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</div>
</body>
</html>