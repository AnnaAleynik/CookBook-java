<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout>
    <div class="container mt-md-4 bg-white">
        <article class="recipe-page py-2">
            <header>
                <h2 class="post-title text-center text-md-left"><c:out value="${recipe.getTitle()}"/></h2>
                <hr class="m-0 d-none d-md-block">
                <div class="recipe-author d-flex justify-content-between py-2 text-right">
                    <div>
                        <small>Автор рецепта</small>
                        <a href="#"><c:out value="${recipe.getAuthor().getLogin()}"/></a>
                    </div>
                    <c:if test="${user.getId() == recipe.getAuthor().getId()}">
                        <div>
                            <small><a href="<c:url value="/recipe/${recipe.getId()}/edit"/>">Редактировать</a></small>
                            <small><a href="<c:url value="/recipe/${recipe.getId()}/delete"/>">Удалить</a></small>
                        </div>
                    </c:if>
                    <c:if test="${!(user.getId() == recipe.getAuthor().getId()) && (user != null)}">
                        <c:if test="${}">
                            <div>
                                <small><a href="<c:url value="/recipe/${recipe.getId()}/delete-fav"/>">Удалить из
                                    избранного</a></small>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${user == null}">
                        <div>
                            <small><a href="<c:url value="/signin"/>">Войдите</a>, чтобы сохранить рецепт!</small>
                        </div>
                    </c:if>
                </div>
            </header>

            <div class="card" style="width: 18rem;">
                <ul class="list-group list-group-flush">
                    <c:set var="amount" value="${0}"/>
                    <c:forEach var="item" items="${recipe.getIngredients()}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <c:out value="${item.getTitle()}"/>
                                <%--                                <span class="badge badge-info">${item.getAmount()}</span>--%>
                            <span>${amount = amount + 1}</span>
                            <span><c:out value="${item.getAmount()}"/></span>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="card">
                <p class="text-left"><c:out value="${recipe.getDescription()}"/></p>
            </div>
        </article>

    </div>
</t:mainLayout>
