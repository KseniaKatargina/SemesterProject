<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="Registration" jsFiles="showPassword.js">
    <br>
    <div class="form">
        <div class="registerForm">
            <form action="<c:url value="/register"/>" method="post">
                <label>Имя
                    <div>
                        <input type="text" name="username" placeholder="имя пользователя" required maxlength="30" minlength="3">
                    </div>
                </label>
                <br>
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
                    <br>
                </label>
                <br>
                <label>Повторите пароль
                    <div>
                        <input class="re_password_input" type="password" name="rePassword" placeholder="пароль" required maxlength="30" minlength="8">
                        <a href="#" class="showRePass" style="color: #1c1b1b">
                            <i id="i2" class="far fa-eye"></i>
                        </a>
                    </div>
                </label>
                <br>
                <p class="small">Пароль должен содержать заглавные,строчные буквы и цифры</p>
                <label>Дата рождения
                    <div>
                        <input type="date" name="birthday" min="1922-01-01" max="2010-12-31"
                               placeholder="дата рождения" required>
                    </div>
                </label>
                <br>
                <br>
                <input value="Зарегистрироваться" type="submit" name="Зарегистрироваться">
            </form>
            <br>
            <p>Уже зарегистрированы? <a href="<c:url value="/login"/>" class="links"> войти</a></p>
            <a href="<c:url value="/welcome"/>" class="links exit"><i class="fa fa-reply fa-1x"></i>  Назад </a>
            <br>
        </div>
    </div>
    <br>
</t:mainLayout>
