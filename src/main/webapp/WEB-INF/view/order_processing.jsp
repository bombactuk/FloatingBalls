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

                            <h2><spring:message code="order_processing.text.order.id" /> ${order.idOrder}</h2>

                            <p><spring:message code="order_processing.text.order.name" /> ${order.name} ${order.surname}</p>
                            <p><spring:message code="order_processing.text.order.telephone" /> ${order.telephone}</p>
                            <p><spring:message code="order_processing.text.order.status" /> ${order.status}</p>
                            <p><spring:message code="order_processing.text.order.date" />${order.datePost}</p>

                            <h3><spring:message code="order_processing.text.order.products" /></h3>

                            <ul>

                                <c:forEach var="product" items="${order.products}">

                                    <li>

                                        ${product.title} - ${product.price} р.

                                    </li>

                                </c:forEach>

                            </ul>

                            <button type="button" class="btn-category" data-toggle="modal" data-target="#addOrderProcessingModal"
                            data-id="${order.idOrder}">

                                <spring:message code="order_processing.button.processing.order" />

                            </button>

                        </div>

                    </c:forEach>

                </c:if>

            </div>

            <c:if test="${empty orders}">

                    <p><spring:message code="order_processing.text.order.no" /></p>

            </c:if>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

        <div class="modal fade" id="addOrderProcessingModal" tabindex="-1"
            role="dialog" aria-labelledby="addOrderProcessingModalLabel" aria-hidden="true">

            <div class="modal-dialog" role="document">

                <div class="modal-content">

                    <div class="modal-header">

                        <h5 class="modal-title" id="addOrderProcessingModalLabel"><spring:message code="order_processing.button.processing.order" /></h5>

                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                            <span aria-hidden="true">&times;</span>

                        </button>

                    </div>

                    <div class="modal-body">

                        <form:form id="processingOrderForm" action="${pageContext.request.contextPath}/processingOrder" modelAttribute="processingOrder" >

                            <input type="hidden" name="idOrder" id="order-id-hidden">

                            <div class="form-group">

                                <label for="country"><spring:message code="order_processing.text.country" /></label>
                                <form:input type="text" class="form-control" id="country" path="country" />

                            </div>

                            <div class="form-group">

                                <label for="address"><spring:message code="order_processing.text.address" /></label>
                                <form:input type="text" class="form-control" id="address" path="address" />

                            </div>

                            <div class="form-group">

                                <label for="postalCode"><spring:message code="order_processing.text.postal.code" /></label>
                                <form:input type="text" class="form-control" id="postalCode" path="postalCode" />

                            </div>

                            <button type="submit" class="btn btn-primary"><spring:message code="order_processing.text.process" /></button>

                        </form:form>

                    </div>

                </div>

            </div>

        </div>

        <script>

            <c:if test="${not empty successful}">

                Swal.fire({

                    title: '<spring:message code="message.text" />',
                    text: '${successful}',
                    confirmButtonText: 'OK'

                });

            </c:if>

        </script>

        <script>

            $(document).ready(function () {

                $('#addOrderProcessingModal').on('show.bs.modal', function (event) {

                    var button = $(event.relatedTarget);
                    var idOrder = button.data('id');

                    var modal = $(this);
                    var actionUrl = '${pageContext.request.contextPath}/processingOrder?idOrder=' + encodeURIComponent(idOrder);

                    modal.find('form').attr('action', actionUrl);

                });

            });

        </script>

    </body>

</html>