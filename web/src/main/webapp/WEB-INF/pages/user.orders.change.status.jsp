<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Change status</title>
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
                    <a href="${pageContext.request.contextPath}/users"
                       class=" btn btn-primary" aria-pressed="true" role="button">Users</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form action="${pageContext.request.contextPath}/orders/user.orders.change.status/${order.id}"
                          method="post">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">order id</th>
                                <th scope="col">user id</th>
                                <th scope="col">user name</th>
                                <th scope="col">item id</th>
                                <th scope="col">item name</th>
                                <th scope="col">status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>${order.id}</th>
                                <th>${order.user.id}</th>
                                <th>${order.user.name}</th>
                                <th>${order.item.id}</th>
                                <th>${order.item.name}</th>
                                <th>${order.status}</th>
                            </tr>
                            </tbody>
                        </table>
                        <label class="my-1 mr-2" for="inlineFormCustomSelectPref">Status</label>
                        <select name="status" class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                            <option value="NEW" hidden>NEW</option>
                            <option value="REVIEWING">REVIEWING</option>
                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                            <option value="DELIVERED">DELIVERED</option>
                        </select>
                        <button type="submit" class="btn btn-primary my-1">Change status</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>