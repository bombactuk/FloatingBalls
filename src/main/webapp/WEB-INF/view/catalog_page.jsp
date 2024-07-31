<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Catalog" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="container-category">

            <div class="categories-block">

                <h1><spring:message code="catalog_page.text.categories" /></h1>

                <ul class="categories-list">

                    <c:forEach var="category" items="${categories}">

                        <li class="category-item">

                            <a href="${pageContext.request.contextPath}/showProductListPage?idCategories=${category.idCategories}">

                                <img src="${category.image}" alt="${category.type}">

                                <h2>${category.type}</h2>
                                <p class="category-text-p">${category.productCount}<spring:message code="catalog_page.text.number.of.goods" /></p>

                            </a>

                        </li>

                    </c:forEach>

                </ul>

            </div>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>