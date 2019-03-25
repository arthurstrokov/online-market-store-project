<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Upload file page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <form method="post" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
                <a href="${pageContext.request.contextPath}/logout"
                   class=" btn btn-primary" aria-pressed="true" role="button">Log out</a>
                <a href="${pageContext.request.contextPath}/store"
                   class=" btn btn-primary" aria-pressed="true" role="button">Store</a>
                <table>
                    <tr>
                        <td><label for="file">Select a file to upload</label></td>
                        <td><input type="file" id="file" name="file"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Upload"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>