<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

            <div class="product-list-admin-function">

                <sec:authorize access="hasRole('ADMIN')">

                    <a href="${pageContext.request.contextPath}/deleteCategories?idCategories=${idCategories}"
                    class="product-list-button" onclick="return confirm('<spring:message code="product_list_page.delete.message" />');">

                        <spring:message code="product_list_page.add.delete" />

                    </a>

                    <button type="button" class="btn-category" data-toggle="modal" data-target="#addProductModal">

                        <spring:message code="product_list_page.add.product" />

                    </button>

                </sec:authorize>

            </div>

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

                    <a href="${pageContext.request.contextPath}/showProductInfoPage?idProduct=${product.idProduct}&idCategories=${idCategories}"
                     class="product-card-link">

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

        <div class="modal fade" id="addProductModal" tabindex="-1"
         role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">

                <div class="modal-dialog" role="document">

                    <div class="modal-content">

                        <div class="modal-header">

                            <h5 class="modal-title" id="addProductModalLabel"><spring:message code="product_list_page.add.product" /></h5>

                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                                <span aria-hidden="true">&times;</span>

                            </button>

                        </div>

                        <div class="modal-body">

                            <form:form action="${pageContext.request.contextPath}/addProduct?idCategories=${idCategories}"
                            modelAttribute="product">

                                <label for="title"><spring:message code="product_list_page.text.name" /></label>
                                <form:input path="title" />
                                <br/>

                                <label for="content"><spring:message code="product_list_page.text.content" /></label>
                                <form:input path="productInfo.content" />
                                <br/>

                                <label for="price"><spring:message code="product_list_page.text.price" /></label>
                                <form:input path="price" />
                                <br/>

                                <label><spring:message code="product_list_page.text.images" /><label>
                                <textarea type="text" name="images" placeholder="Введите ссылки через запятую" rows="3"></textarea>
                                <br/>

                                <button type="submit" class="btn btn-primary"><spring:message code="product_list_page.add.product" /></button>

                            </form:form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <script>

            // Check if there's a message to display

            <c:if test="${not empty successful}">

                Swal.fire({

                    title: '<spring:message code="message.text" />',
                    text: '${successful}',
                    confirmButtonText: 'OK'

                });

            </c:if>

        </script>

        <script>

            function submitSearchForm(event) {

                if (event.key === "Enter") {

                    document.getElementById("searchForm").submit();

                }

            }

        </script>

    </body>

</html>