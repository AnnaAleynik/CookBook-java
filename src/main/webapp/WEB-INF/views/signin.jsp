<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.10.2020
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout>

    <div class="container mt-md-4 bg-white">

        <div class="form-input">
            <form action="" method="post">
                <div class="form-group">
                    <input type="email" class="form-control col-3" name="email" id="email" placeholder="Enter email">
                        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control col-3" name="password" id="password"
                           placeholder="Enter password">
                </div>
                    <%--    <div><input type="submit"></div>--%>
                <button type="submit" class="btn btn-primary">Войти</button>
            </form>
        </div>
        <a href="<c:url value="/signup"/>" class="btn btn-secondary mt-2">Зарегистрироваться</a>
    </div>

</t:mainLayout>

