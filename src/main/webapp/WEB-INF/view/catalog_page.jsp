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

            <jsp:param name="title" value="Catalog" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="container-category">

            <div class="categories-block">

                <sec:authorize access="hasRole('ADMIN')">

                    <button type="button" class="btn-category" data-toggle="modal" data-target="#addProductCategoriesModal">

                        <spring:message code="catalog_page.text.add.categories" />

                    </button>

                </sec:authorize>

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

        <div class="modal fade" id="addProductCategoriesModal" tabindex="-1"
         role="dialog" aria-labelledby="addProductCategoriesModalLabel" aria-hidden="true">

                <div class="modal-dialog" role="document">

                    <div class="modal-content">

                        <div class="modal-header">

                            <h5 class="modal-title" id="addProductCategoriesModalLabel"><spring:message code="catalog_page.text.add.new.categories" /></h5>

                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                                <span aria-hidden="true">&times;</span>

                            </button>

                        </div>

                        <div class="modal-body">

                            <form:form action="${pageContext.request.contextPath}/addCategories" modelAttribute="addCategories" >

                                <div class="form-group">

                                    <label for="productName"><spring:message code="catalog_page.text.title" /></label>
                                    <form:input type="text" class="form-control" id="type" path="type" />

                                </div>

                                <div class="form-group">

                                    <label for="productImage"><spring:message code="catalog_page.text.link.image" /></label>
                                    <form:input type="text" class="form-control" id="productImage" path="image" />

                                </div>

                                <button type="submit" class="btn btn-primary"><spring:message code="catalog_page.button.add.categories" /></button>

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

    </body>

</html>