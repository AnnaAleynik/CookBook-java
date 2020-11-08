<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout>
    <div class="container">
        <h1 class="post-title text-center text-md-left">${login}</h1>
        <hr class="m-0 d-none d-md-block">
        <div class="recipe-author d-flex align-items-center py-2 text-right">
            <div>
                    ${email}
            </div>
        </div>
        <div><a href="<c:url value="/favorite"/>" class="btn btn-outline-secondary mt-md-2">Избранные рецепты</a></div>
        <div><a href="<c:url value="/add-recipe"/>" class="btn btn-outline-success  mt-md-2">Добавить свой рецепт</a>
        </div>
        <div><a href="<c:url value="/recipes/my"/>" class="btn btn-outline-secondary  mt-md-2">Мои рецепты</a></div>


    </div>
</t:mainLayout>
