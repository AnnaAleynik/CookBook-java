<%@tag description="Default Layout Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Книга рецептов</title>
    <meta charset="UTF-8">
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main-page-style.css"/>
    <script src="${pageContext.request.contextPath}/static/js/magic.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
    <a class="navbar-brand" href="<c:url value="/home"/>">
        <img src="${pageContext.request.contextPath}/static/img/icon-cooking.png" width="30" height="30"
             class="d-inline-block align-top" alt="" loading="lazy">
        Книга рецептов
    </a>

    <!-- Toggler/collapsibe Button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Рецепты</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Завтраки</a>
                    <a class="dropdown-item" href="#">Обеды</a>
                    <a class="dropdown-item" href="#">Десерты</a>
                </div>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="#">Советы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Меры и веса</a>
            </li>
        </ul>
    </div>

    <ul class="navbar-nav ml-auto">
        <c:if test="${user == null}">
            <li class="nav-item">
                <a class="btn btn-outline-info ml-sm-1" href="<c:url value="/signin"/>">Войти</a>
            </li>
        </c:if>
        <c:if test="${!(user == null)}">
            <a class="btn btn-outline-info ml-sm-1" href="<c:url value="/profile"/>">Профиль</a>
            <a class="btn btn-outline-info ml-sm-1" href="<c:url value="/logout"/>">Выйти</a>
        </c:if>
        <li class="nav-item">
            <form class="form-inline my-2 my-lg-0  ml-sm-2">
                <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-sm-0" type="submit">Найти в книге</button>
            </form>
        </li>
    </ul>
</nav>


<jsp:doBody/>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>