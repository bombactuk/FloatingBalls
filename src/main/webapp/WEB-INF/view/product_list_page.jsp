<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="ProductList" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="product-list-container">

            <h1><spring:message code="product_list_page.text.list.of.products" /></h1>

                <div class="sort-panel">

                    <form method="get" action="${pageContext.request.contextPath}/sortProductList">

                        <label for="sort-by"><spring:message code="product_list_page.text.list.sort" /></label>

                        <input type="hidden" name="idCategories" value="${idCategories}" />

                        <select id="sort-by" name="sortBy" onchange="this.form.submit()">

                            <option value="all"><spring:message code="product_list_page.text.select.choose" /></option>
                            <option value="title_asc"><spring:message code="product_list_page.text.select.name.az" /></option>
                            <option value="title_desc"><spring:message code="product_list_page.text.select.name.za" /></option>
                            <option value="price_asc"><spring:message code="product_list_page.text.select.price.age" /></option>
                            <option value="price_desc"><spring:message code="product_list_page.text.select.price.ub" /></option>

                        </select>

                    </form>

                </div>

                <div class="search-panel">

                    <form method="get" action="${pageContext.request.contextPath}/searchProductsList" id="searchForm">

                        <input type="hidden" name="idCategories" value="${idCategories}" />
                        <label for="search-query"><spring:message code="product_list_page.text.input.search" /></label>
                        <input type="text" id="search-query" name="query" placeholder="<spring:message code="product_list_page.text.input.search" />" oninput="submitSearchForm()"/>

                    </form>

                </div>

            <div class="product-list">

                <c:forEach var="product" items="${products}">

                    <a href="${pageContext.request.contextPath}/showProductInfoPage?idProduct=${product.idProduct}" class="product-card-link">

                        <div class="product-card">

                            <img src="${product.images[0].link}" alt="${product.title}" class="product-image">

                            <div class="product-list-info">

                                <h2 class="product-title">${product.title}</h2>
                                <p class="product-price">${product.price} руб.</p>

                            </div>
                        </div>

                    </a>

                </c:forEach>

            </div>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

        <script>

            function submitSearchForm(event) {

                if (event.key === "Enter") {

                    document.getElementById("searchForm").submit();

                }

            }

        </script>

    </body>

</html>