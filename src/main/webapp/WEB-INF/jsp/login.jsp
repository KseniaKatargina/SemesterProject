<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Login" jsFiles="showPassword.js">
    <br>
    <div class="form">
        <div class="loginForm">
            <form action="<c:url value="/login"/>" method="post">
                <label>Email
                    <div>
                        <input type="text" name="email" placeholder="email" required maxlength="40" minlength="10">
                    </div>
                </label>
                <br>
                <label>Пароль
                    <div>
                        <input class="password_input" type="password" name="password" placeholder="пароль" required maxlength="30" minlength="8">
                        <a href="#" class="showPass" style="color: #1c1b1b">
                            <i id="i" class="far fa-eye"></i>
                        </a>
                    </div>
                </label>
                <br>
                <br>
                <input value="Войти" type="submit" name="Войти">
            </form>
            <p>Не зарегистрированы? <a class="links" href="<c:url value="/register"/>"> зарегистрироваться</a></p>
            <a  class="links exit" href="<c:url value="/welcome"/>"><i class="fa fa-reply fa-1x"></i>
                Назад
            </a>
            <br>
        </div>
    </div>
    <br>
</t:mainLayout>
