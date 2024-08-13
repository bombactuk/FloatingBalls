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

        <div class="order-container-ready">

            <div class="order-header-ready">

                <h1><spring:message code="ready_orders_page.text.ready.order" /></h1>

            </div>

            <div class="search-panel-ready-order">

                <form method="get" action="${pageContext.request.contextPath}/searchOrderReadyList" id="searchForm">

                    <label for="search-query"><spring:message code="ready_orders_page.text.search" /></label>
                    <input type="text" id="search-query" name="query" placeholder="<spring:message code="ready_orders_page.text.search.ready" />" oninput="submitSearchForm()"/>

                </form>

            </div>

            <div class="order-cards-ready">

                <c:if test="${not empty orders}">

                    <c:forEach var="order" items="${orders}">

                        <div class="order-card-ready">

                            <h2><spring:message code="ready_orders_page.text.order.id" /> ${order.idOrder}</h2>

                            <p><spring:message code="ready_orders_page.text.order.name" /> ${order.name} ${order.surname}</p>
                            <p><spring:message code="ready_orders_page.text.order.telephone" /> ${order.telephone}</p>
                            <p><spring:message code="ready_orders_page.text.order.status" /> ${order.status}</p>
                            <p><spring:message code="ready_orders_page.text.order.date" />${order.datePost}</p>

                            <h3><spring:message code="ready_orders_page.text.order.products" /></h3>

                            <ul>

                                <c:forEach var="product" items="${order.products}">

                                    <li>

                                        ${product.title} - ${product.price} р.

                                    </li>

                                </c:forEach>

                            </ul>

                            <h4><spring:message code="ready_orders_page.text.sending.data" /></h4>

                            <c:forEach var="shipping" items="${order.orderShippings}">

                                <p><spring:message code="ready_orders_page.text.country" /> ${shipping.country}</p>
                                <p><spring:message code="ready_orders_page.text.address" /> ${shipping.address}</p>
                                <p><spring:message code="ready_orders_page.text.postal.code" /> ${shipping.postalCode}</p>
                                <p><spring:message code="ready_orders_page.text.tracking.index" />${order.trackingIndex}</p>

                            </c:forEach>

                        </div>

                    </c:forEach>

                </c:if>

            </div>

            <c:if test="${empty orders}">

                    <p><spring:message code="ready_orders_page.text.order.no" /></p>

            </c:if>

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