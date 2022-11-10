<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Rename Wishlist" jsFiles="menu.js,deleteAccount.js">
    <br>
    <div class="form">
        <div class="loginForm">
            <br>
            <br>
            <br>
            <form action="<c:url value="/renameWishlist"/>" method="post">
                <label>Введите название:
                    <div>
                        <input type="text" name="title" placeholder="Введите название" value="${title}">
                        <input type="hidden" name="listID" value="${listID}">
                    </div>
                </label>
                <br>
                <br>
                <input value="Изменить название" type="submit" name="Изменить название">
            </form>
            <a href="<c:url value="/myWishlists"/>" class="remove"><i class="fa fa-reply fa-1x"></i>  К моим листам</a>
        </div>
    </div>
    <br>
</t:mainLayout>
