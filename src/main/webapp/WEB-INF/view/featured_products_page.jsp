<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Featured Product" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

            <div class="product-feature-container">

                <h1><spring:message code="featured_products.text.featured.products" /></h1>

                <div class="product-feature">

                    <c:forEach var="product" items="${featuredProducts}">

                        <a href="${pageContext.request.contextPath}/showProductInfoPage?idProduct=${product.idProduct}" class="product-feature-link">

                            <div class="product-card-feature">

                                <img src="${product.images[0].link}" alt="${product.title}" class="product-feature-image">

                                <div class="product-feature-info">

                                    <h2 class="product-feature-title">${product.title}</h2>
                                    <p class="product-feature-price">${product.price} руб.</p>

                                </div>

                                <a href="${pageContext.request.contextPath}/removeProductFeatured?idProduct=${product.idProduct}" class="product-feature-button">

                                    <spring:message code="featured_products.text.remove.from" />

                                </a>

                            </div>

                        </a>

                    </c:forEach>

                </div>

            </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

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