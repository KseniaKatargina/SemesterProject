<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Wishlist" jsFiles="menu.js,deleteAccount.js">
    <div class="main">
        <p>${title}</p>
        <div class="productCards">
            <c:forEach var="product" items="${products}">
                <div class="productCard">
                    <img class="imgCard" src=${pageContext.request.contextPath}${product.getImg()}>
                    <p class="imgText">${product.text}</p>
                    <form action="<c:url value="/removeProduct"/>" method="post">
                        <input type="hidden" name="prodID" value="${product.id}">
                        <input type="hidden" name="listID" value="${listID}">
                        <input type="submit" value="Удалить из листа">
                    </form>
                </div>
            </c:forEach>

        </div>
        <form action="<c:url value="/removeWishlist"/>" method="post">
            <input type="hidden" name="listID" value="${listID}">
            <input type="submit" value="Удалить весь лист">
        </form>
        <form action="<c:url value="/renameWishlist"/>" method="get">
            <input type="hidden" name="listID" value="${listID}">
            <input type="hidden" name="title" value="${title}">
            <input type="submit" value="Редактировать название">
        </form>

        <a href="<c:url value="/myWishlists"/>" class="remove"><i class="fa fa-reply fa-1x"></i>  К моим листам</a>
    </div>
</t:mainLayout>
