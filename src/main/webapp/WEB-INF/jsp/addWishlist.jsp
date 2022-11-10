<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Add Wishlist" jsFiles="menu.js,deleteAccount.js">
    <br>
    <div class="form">
        <div class="createForm">
            <form action="<c:url value="/addWishlist"/>" method="post">
                <label>Введите название:
                    <div>
                        <input type="text" name="title" placeholder="Введите название" required>
                    </div>
                </label>
                <br>
                <br>
                <input type="submit" value="Создать">
            </form>
            <a  class="links exit" href="<c:url value="/myWishlists"/>">
                Мои вишлисты
            </a>
            <br>
            <br>
            <a href="<c:url value="/mainPage"/>" class="links exit">На главную</a>
        </div>
    </div>
    <br>
</t:mainLayout>

