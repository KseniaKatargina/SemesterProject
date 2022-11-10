<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Welcome">
    <div class="row ">
        <div class="main">
            <div class="main_text">
                <p> Чтобы пользоваться сайтом, необходимо <a class="links" href="<c:url value="/login"/>">войти</a> или <a class="links" href="<c:url value="/register"/>">зарегистрироваться</a> </p>
            </div>
        </div>
    </div>
</t:mainLayout>

