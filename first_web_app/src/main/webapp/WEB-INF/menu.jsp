<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">${param.pageName}</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <c:url value = "/" var = "productsInMenuUrl"/>
                        <a class="nav-link" href="${productsInMenuUrl}">Products</a>
                    </li>
                </ul>
            </div>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <c:url value = "/showBasket" var = "basketInMenuUrl"/>
                        <a class="nav-link" href="${basketInMenuUrl}">Basket</a>
                    </li>
                </ul>
            </div>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <c:url value = "/about" var = "aboutInMenuUrl"/>
                        <a class="nav-link" href="${aboutInMenuUrl}">About</a>
                    </li>
                </ul>
            </div>

        </nav>
