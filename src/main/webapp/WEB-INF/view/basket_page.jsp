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

            <jsp:param name="title" value="Basket" />

        </jsp:include>

    </head>

    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="product-basket-container">

            <h1><spring:message code="basket_page.text.shopping.basket" /></h1>

            <c:set var="basketSize" value="${fn:length(basket)}"/>

            <c:if test="${basketSize == 0}">

                <h2><spring:message code="basket_page.text.cart.empty" /></h2>

            </c:if>

            <c:if test="${basketSize > 0}">

                <h2><spring:message code="basket_page.text.number.goods" /> ${basketSize}<h2>

                <div class="product-basket">

                    <c:forEach var="product" items="${basket}">

                        <a href="${pageContext.request.contextPath}/showProductInfoPage?idProduct=${product.idProduct}" class="product-basket-link">

                            <div class="product-card-basket">

                                <img src="${product.images[0].link}" alt="${product.title}" class="product-basket-image">

                                <div class="product-basket-info">

                                    <h2 class="product-basket-title">${product.title}</h2>
                                    <p class="product-basket-price">${product.price} руб.</p>

                                </div>

                                <a href="${pageContext.request.contextPath}/removeProductBasket?idProduct=${product.idProduct}" class="product-basket-button">

                                    <spring:message code="basket_page.text.remove.basket" />

                                </a>

                            </div>

                        </a>

                    </c:forEach>

                </div>

                <h3>Create Your Order</h3>

                <form:form method="post" action="${pageContext.request.contextPath}/checkoutPage" modelAttribute="order" class="order-form-basket">

                    <div class="form-group-basket">

                        <label for="name">Name:</label>
                        <form:input path="name" id="name" class="form-control-basket" />
                        <form:errors path="name" cssClass="error" />

                    </div>

                    <div class="form-group-basket">

                        <label for="surname">Surname:</label>
                        <form:input path="surname" id="surname" class="form-control-basket" />
                        <form:errors path="surname" cssClass="error" />

                    </div>

                    <div class="form-group-basket">

                        <label for="telephone">Phone Number:</label>
                        <form:input path="telephone" id="telephone" class="form-control-basket" />
                        <form:errors path="telephone" cssClass="error" />

                    </div>

                    <div class="form-group-basket">

                        <button type="submit" class="product-basket-button">

                            <spring:message code="basket_page.text.buy" /> ${sumPriceProduct} р.

                        </button>

                    </div>

                </form:form>

            </c:if>

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