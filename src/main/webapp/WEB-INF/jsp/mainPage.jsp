<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="MainPage" jsFiles="menu.js,deleteAccount.js">
    <div class="main">
        <div class="productCards">
            <c:forEach var="product" items="${products}">
                <div class="productCard">
                    <img class="imgCard" src=${product.getImg()}>
                    <p class="imgText">${product.text}</p>
                    <div class="dropdownLists">
                        <button class="dropListsBtn">
                            <i class="far fa-heart fa-1x"></i>
                        </button>
                        <div class="dropdown-lists dropLists">
                            <form method="get" action="<c:url value="/addWishlist"/>">
                                <input class="wish" type="submit" value="+">
                            </form>
                            <c:forEach var="wishlist" items="${wishlists}">
                                <form action="<c:url value="/addProduct"/>" method="post">
                                    <input class="wish" type="submit" value=${wishlist.title}>
                                    <input type="hidden" name="listID" value="${wishlist.id}">
                                    <input type="hidden" name="prodID" value="${product.id}">
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</t:mainLayout>
