<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mainLayout>

    <div class="container mt-md-4 bg-white">

        <div class="form-input">
            <form action="" method="post">
                <div class="form-group">
                    <input type="text" class="form-control col-3" name="login" id="login" placeholder="Логин">
                        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                </div>
                <div class="form-group mt-1">
                    <input type="email" class="form-control col-3" name="email" id="email" placeholder="E-mail">
                        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control col-3" name="password" id="password"
                           placeholder="Пароль">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control col-3" name="password" id="password2"
                           placeholder="Повторите пароль">
                </div>
                <div class="form-group">
                    <input type="checkbox" class="mr-1" name="accept"><span>I agree with <a href="">Terms & Privacy</a></span>
                </div>

                <button type="submit" class="btn btn-primary">Зарегистрироватся</button>

            </form>
        </div>
    </div>

</t:mainLayout>
