<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="head.jsp"%>

<body>

    <jsp:include page = "menu.jsp">
        <jsp:param name = "pageName" value = "About"/>
    </jsp:include>

    <h1>Информация о нашем Магазине!!!</h1>

    <%@include file="tailScript.jsp"%>
</body>

</html>