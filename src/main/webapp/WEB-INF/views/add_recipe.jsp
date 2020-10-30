<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mainLayout>
    <div class="form-input">
        <form method="post">
            <div class="input-group mb-3">
                <input type="text" class="form-control col-3" placeholder="Название рецепта">
            </div>
            <p>Ингрeдиенты</p>
            <div id="items">

                <div class="input-group mb-2"><input class="col-2 mr-2" type="text" name="item1"><input class="col-1"
                                                                                                        type="text"
                                                                                                        name="amount1">
                </div>
                <div><input type="text" name="item2" size="45"><input type="text" name="amount2" size="15"></div>
                <div><input type="text" name="item3" size="45"><input type="text" name="amount3" size="15"></div>
                <input type="button" value="+" id="add" size="45">
            </div>
            <label for="description">Рецепт</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
            <input hidden type="number" name="amount" id="amount" value="3">
            <button type="submit" class="btn btn-primary">Добавить</button>
        </form>


    </div>
</t:mainLayout>