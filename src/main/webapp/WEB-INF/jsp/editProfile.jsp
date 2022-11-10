<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Edit Profile" jsFiles="menu.js,deleteAccount.js,showPassword.js">
    <form method="post" action="<c:url value="/editProfile"/>">
        <div class="mainProfile">
            <div class="profile">
                <label>Изменить имя:
                    <input value="${user.username}" type="text" name="username" placeholder="имя пользователя"
                           maxlength="30" minlength="3" required>
                </label>
                <br>
                <label>Изменить дату рождения:
                    <input value="${user.birthday}" type="date" name="birthday" min="1922-01-01" max="2010-12-31"
                           placeholder="дата рождения" required>
                </label>
                <br>
                <label>Изменить email:
                    <input value="${user.email}" type="text" name="email" placeholder="email" maxlength="30" minlength="5" required>
                </label>
                <br>
                <label>Введите старый пароль:
                    <div>
                        <input class="password_input" type="password" name="oldPassword" placeholder="старый пароль"
                               maxlength="30" minlength="8">
                        <a href="#" class="showPass" style="color: #1c1b1b">
                            <i id="i" class="far fa-eye"></i>
                        </a>
                    </div>
                </label>
                <br>
                <label>Введите новый пароль:
                    <div>
                        <input class="new_password_input" type="password" name="newPassword" placeholder="новый пароль"
                               maxlength="30" minlength="8">
                        <a href="#" class="showNewPass" style="color: #1c1b1b">
                            <i id="i3" class="far fa-eye"></i>
                        </a>
                    </div>
                </label>
                <label>Введите пароль еще раз:
                    <div>
                        <input class="re_password_input" type="password" name="rePassword"
                               placeholder="введите новый пароль еще раз" maxlength="30" minlength="8">
                        <a href="#" class="showRePass" style="color: #1c1b1b">
                            <i id="i2" class="far fa-eye"></i>
                        </a>
                    </div>
                </label>
                <br>
                <br>
                <div class="edit">
                    <input value="Изменить" type="submit" name="изменить">
                </div>
                <div class="exit">
                    <a href="<c:url value="/profile"/>"><i class="fa fa-reply fa-1x"></i>  Назад</a>
                </div>
            </div>
        </div>
    </form>
</t:mainLayout>
