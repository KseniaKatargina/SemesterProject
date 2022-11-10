<%@tag description="layout tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title"%>
<%@attribute name="jsFiles"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <c:forTokens items="${jsFiles}" delims="," var="file">
        <script defer src="<c:url value="/js/${file}"/>"></script>
    </c:forTokens>

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/index.css"/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
</head>
<body>
<%@include file="/WEB-INF/jsp/parts/header.jsp"%>
    <jsp:doBody/>
<%@include file="/WEB-INF/jsp/parts/footer.jsp"%>
</body>
</html>