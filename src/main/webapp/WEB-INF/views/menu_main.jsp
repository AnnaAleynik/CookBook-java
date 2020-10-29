<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout>
    <div>
        <h1 class="text-center mt-1">Добро пожаловать!</h1>
    </div>
    <div>
        <p class="text-center mt-1">Книга рецептов - сайт, где ты можешь найти рецепт </p>
    </div>
    <div class="container">
        <div class="row justify-content-center">
            <button type="button" class="btn btn-outline-info col-2 align-self-start mr-1 mb-2">по настроению</button>
            <button type="button" class="btn btn-outline-info col-2 align-self-start ml-1 mb-2">по списку продуктов</button>
        </div>
    </div>


    <div>
        <p class="text-center mt-1">... или воспользоваться нашим рандомайзером и найти</p>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <button type="button" class="btn btn-outline-info col-2 align-self-start mr-1 mb-2">рецепт дня</button>
            <button type="button" class="btn btn-outline-info col-2 align-self-start ml-1 mb-2">меню дня</button>
        </div>
    </div>

    <div>
        <p class="text-center mt-1">А может быть Вы хотите добавит свой рецепт?</p>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <a href="<c:url value="/add-recipe"/>" class="btn btn-outline-info col-2 align-self-start">добавить</a>
        </div>
    </div>
</t:mainLayout>