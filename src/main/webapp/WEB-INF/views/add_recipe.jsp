<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout>
    <div class="container mt-md-4 bg-white">
        <!-- <%--        <article class="recipe-page col-10">--%> -->
        <div class="form-input">
            <form method="post">
                <div class="input-group">
                    <input type="text" class="form-control" name="title" placeholder="Название рецепта">
                </div>
                <div class="form-group">
                        <%--                    <label for="exampleFormControlSelect2">Example multiple select</label>--%>
                    <input type="text" placeholder="Search.." id="tagInput" onkeyup="filterFunction()">
                    <select multiple class="form-control" id="tagChoice" name="tags">
                        <c:forEach var="item" items="${allTags}">
                            <option><c:out value="${item.getTitle()}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div id="items">
                    <div class="input-group mb-2 mt-2">
                        <input class="form-control col-4 mr-3 form-control" type="text" name="item1"
                               placeholder="Ингредиент">
                        <input
                                class="col-2 form-control"
                                type="text"
                                name="amount1" placeholder="Количество">
                    </div>
                    <input type="button" class="btn btn-outline-success btn-sm" value="+" id="add">
                </div>
                <textarea class="form-control mt-3" id="description" name="description" rows="3"
                          placeholder="Вводите рецепт..."></textarea>
                <input hidden type="number" name="amount" id="amount" value="1">

                <button type="submit" class="btn btn-outline-success mt-5">Добавить</button>
            </form>


        </div>
        <!-- <%--        </article>--%> -->
    </div>
</t:mainLayout>
