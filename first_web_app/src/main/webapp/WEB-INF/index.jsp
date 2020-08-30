<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="head.jsp"%>

<body>

<jsp:include page = "menu.jsp">
    <jsp:param name = "pageName" value = "Products"/>
</jsp:include>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/new" var="newEditUrl"/>
            <a class="btn btn-primary" href="${newEditUrl}">Add product</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="prod" items="${requestScope.products}">
                    <tr>
                        <th scope="row">
                            <c:out value="${prod.id}"/>
                        </th>

                        <td>
                            <c:url value="/select" var="productSelectUrl">
                                <c:param name="id" value="${prod.id}"/>
                            </c:url>
                            <a href = "${productSelectUrl}">
                                <c:out value="${prod.name}"/>
                            </a>
                        </td>

                        <td>
                            <c:out value="${prod.description}"/>
                        </td>

                        <td>
                            <c:out value="${prod.price}"/>
                        </td>

                        <td>

                            <c:url value="/edit" var="productEditUrl">
                                <c:param name="id" value="${prod.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${productEditUrl}">
                                <i class="fas fa-edit"></i>
                            </a>

                            <c:url value="/addInBasket" var="productAddInBasketUrl">
                                <c:param name="id" value="${prod.id}"/>
                            </c:url>
                            <a class="btn btn-danger" href="${productAddInBasketUrl}"><i class="far fa-trash-alt"></i></a>

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

</html>