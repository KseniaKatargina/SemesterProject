<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Profile" jsFiles="menu.js,deleteAccount.js">
    <div class="mainProfile">
        <div class="profile">
            <p>Username: ${user.username}</p>
            <p>Возраст: ${user.years} лет</p>
            <p>Email: ${user.email}</p>
            <form action="<c:url value="/editProfile"/>" method="get">
                <input type="submit" value="Изменить профиль">
            </form>
            <br>
            <div class="exit">
                <a href="<c:url value="/mainPage"/>"><i class="fa fa-home fa-1x"></i>  На главную</a>
            </div>
        </div>
    </div>
</t:mainLayout>