<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="My wishlists" jsFiles="menu.js,deleteAccount.js">
    <div class="mainLists">
        <div class="list">
            <div class="add">
                <a class="addList" href="<c:url value="/addWishlist"/>">
                    <i class="fas fa-plus fa-3x"></i>
                </a>
            </div>
        </div>
        <c:forEach var="wishlist" items="${wishlists}">
            <div class="list">
                <div class="open">
                    <form action="<c:url value="/wishlist"/>" method="get">
                        <input type="hidden" name="listID" value="${wishlist.id}">
                        <input type="submit" value="${wishlist.title}">
                    </form>
                    </a>
                </div>
            </div>
        </c:forEach>
        <a href="<c:url value="/mainPage"/>" class="remove"><i class="fa fa-reply fa-1x"></i> На главную</a>
    </div>
</t:mainLayout>
