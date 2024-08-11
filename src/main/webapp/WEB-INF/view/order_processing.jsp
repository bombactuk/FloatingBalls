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

            <jsp:param name="title" value="Order processing" />

        </jsp:include>

    </head>

    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="order-container-processing">

            <div class="order-header-processing">

                <h1><spring:message code="order_processing.text.processing.order" /></h1>

            </div>

            <div class="order-cards-processing">

                <c:if test="${not empty orders}">

                    <c:forEach var="order" items="${orders}">

                        <div class="order-card-processing">

                            <h2><spring:message code="purchase_history.text.order.id" /> ${order.idOrder}</h2>

                            <p><spring:message code="purchase_history.text.order.name" /> ${order.name} ${order.surname}</p>
                            <p><spring:message code="purchase_history.text.order.telephone" /> ${order.telephone}</p>
                            <p><spring:message code="purchase_history.text.order.status" /> ${order.status}</p>
                            <p><spring:message code="purchase_history.text.order.date" />${order.datePost}</p>

                            <h3><spring:message code="purchase_history.text.order.products" /></h3>

                            <ul>

                                <c:forEach var="product" items="${order.products}">

                                    <li>

                                        ${product.title} - ${product.price} р.

                                    </li>

                                </c:forEach>

                            </ul>

                        </div>

                    </c:forEach>

                </c:if>

            </div>

            <c:if test="${empty orders}">

                    <p><spring:message code="purchase_history.text.order.no" /></p>

            </c:if>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>