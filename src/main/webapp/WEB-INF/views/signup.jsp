<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mainLayout>
    <div>Hello</div>
    <div class="form-input">
        <form action="" method="post">
            <div><span>Login</span><input type="text" name="login" id="login" placeholder="LOGIN"></div>
            <div><span>Password</span><input type="password" name="password" id="password" placeholder="PASSWORD"></div>
            <div><span>Repeat password</span><input type="password" name="password2" id="password2"
                                                    placeholder="REPEAT PASSWORD"></div>
            <div><span>Email</span><input type="text" name="mail" id="mail" placeholder="E-MAIL"></div>
            <div><input type="checkbox" name="accept"><span>I agree with <a href="">Terms & Privacy</a></span></div>
            <div><input type="submit"></div>
        </form>
    </div>

</t:mainLayout>