<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Failed Edit Profile" jsFiles="menu.js,deleteAccount.js">
    <br>
    <form action="<c:url value="/editProfile"/>" method="get">
        <div align="center">
            <h2>${message}</h2>
            <input type="submit" value="Назад">
        </div>
    </form>
    <br>
</t:mainLayout>
