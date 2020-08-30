<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="head.jsp"%>

<body>

<jsp:include page = "menu.jsp">
    <jsp:param name = "pageName" value = "Basket"/>
</jsp:include>

<div class="container">
    <div class="row py-2">

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Number</th>
                    <th scope="col">Cost</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="prod" items="${requestScope.productsInBasket}">
                    <tr>
                        <th scope="row">
                            <c:out value="${prod.idInBasket}"/>
                        </th>

                        <td>
                            <c:out value="${prod.name}"/>
                        </td>

                        <td>
                            <c:out value="${prod.description}"/>
                        </td>

                        <td>
                            <c:out value="${prod.price}"/>
                        </td>

                        <td>
                            <c:out value="${prod.numberOfProduct}"/>
                        </td>

                        <td>
                            <c:out value="${prod.getCost()}"/>
                        </td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="tailScript.jsp"%>

</body>