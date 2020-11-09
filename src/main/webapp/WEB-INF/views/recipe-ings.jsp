<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout>

    <div class="container mt-md-4 bg-white">

        <div class="form-input">
            <form action="" method="post">
                <div class="form-group">
                    <input type="text" class="form-control col-6" name="t" id="t" placeholder="Вводите ингредиенты через запятую">
                </div>
                <button type="submit" class="btn btn-primary">Найти</button>
            </form>
        </div>

    </div>

</t:mainLayout>
