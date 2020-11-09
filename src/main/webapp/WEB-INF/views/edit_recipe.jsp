<%@ page import="java.util.LinkedList" %>
<%@ page import="ru.itis.aleynik.cookingbook.models.Ingredient" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<t:mainLayout>
    <div class="container mt-md-4 bg-white">
        <!-- <%--        <article class="recipe-page col-10">--%> -->
        <div class="form-input">
            <form method="post">
                <div class="input-group">
                    <input type="text" class="form-control" name="title" value="<c:out value="${recipe.getTitle()}"/>">
                </div>

                <div>
                    <a class="btn btn-outline-success btn-sm mt-2" href="<c:url value="/recipe/${recipe.getId()}/edit-ingredients"/>">Изменить ингредиенты</a>

                    <ul class="list-group list-group-horizontal mb-2">
                        <c:forEach var="item" items="${recipe.getTags()}">
                            <li class="list-group-item mr-2">${item.getTitle()}</li>
                        </c:forEach>
                    </ul>
                </div>

                <div id="items">
<%--                    <c:set var="amount" value="${0}"/>--%>
<%--                    <c:set var="item" value="${0}"/>--%>
                    <a class="btn btn-outline-success btn-sm" href="<c:url value="/recipe/${recipe.getId()}/edit-ingredients"/>">Изменить ингредиенты</a>
                    <c:forEach var="item" items="${recipe.getIngredients()}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <c:out value="${item.getTitle()}"/>
                            <span><c:out value="${item.getAmount()}"/></span>
                        </li>
                    </c:forEach>
                    <input type="button" class="btn btn-outline-success btn-sm" value="+" id="add">
                </div>
                <textarea class="form-control mt-3" id="description" name="description" rows="10"><c:out value="${recipe.getDescription()}"/></textarea>
                <input hidden type="number" name="amount" id="amount" value="${amount}">
                <input hidden type="number" name="r_id" value="${recipe.getId()}">

                <button type="submit" class="btn btn-outline-success mt-5">Изменить</button>
            </form>


        </div>
        <!-- <%--        </article>--%> -->
    </div>
</t:mainLayout>

