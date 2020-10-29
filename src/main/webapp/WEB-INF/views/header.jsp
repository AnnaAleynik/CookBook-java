<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.10.2020
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Книга рецептов</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css" />">
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">--%>
    <%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"--%>
    <%--          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="<c:url value="/static/css/main-page-style.css" />">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <a class="navbar-brand" href="#">
        <img src="<c:url value="/static/img/icon-cooking.png" />" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
        Книга рецептов
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Рецепты
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Завтраки</a>
                    <a class="dropdown-item" href="#">Закуски</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Супы</a>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Советы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Меры и веса</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Найти в книге</button>
        </form>
    </div>
</nav>