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

        <div class="order-container-sending">

            <div class="order-header-sending">

                <h1><spring:message code="sending_order_page.text.sending.order" /></h1>

            </div>

            <div class="order-cards-sending">

                <c:if test="${not empty orders}">

                    <c:forEach var="order" items="${orders}">

                        <div class="order-card-sending">

                            <h2><spring:message code="sending_order_page.text.order.id" /> ${order.idOrder}</h2>

                            <p><spring:message code="sending_order_page.text.order.name" /> ${order.name} ${order.surname}</p>
                            <p><spring:message code="sending_order_page.text.order.telephone" /> ${order.telephone}</p>
                            <p><spring:message code="sending_order_page.text.order.status" /> ${order.status}</p>
                            <p><spring:message code="sending_order_page.text.order.date" />${order.datePost}</p>

                            <h3><spring:message code="sending_order_page.text.order.products" /></h3>

                            <ul>

                                <c:forEach var="product" items="${order.products}">

                                    <li>

                                        ${product.title} - ${product.price} р.

                                    </li>

                                </c:forEach>

                            </ul>

                            <h4><spring:message code="sending_order_page.text.sending.data" /></h4>

                            <c:forEach var="shipping" items="${order.orderShippings}">

                                <p><spring:message code="sending_order_page.text.country" /> ${shipping.country}</p>
                                <p><spring:message code="sending_order_page.text.address" /> ${shipping.address}</p>
                                <p><spring:message code="sending_order_page.text.postal.code" /> ${shipping.postalCode}</p>

                            </c:forEach>

                            <button type="button" class="btn-category" data-toggle="modal" data-target="#addSendingOrderModal"
                            data-id="${order.idOrder}">

                                <spring:message code="sending_order_page.button.sending.order" />

                            </button>

                        </div>

                    </c:forEach>

                </c:if>

            </div>

            <c:if test="${empty orders}">

                    <p><spring:message code="sending_order_page.text.order.no" /></p>

            </c:if>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

        <div class="modal fade" id="addSendingOrderModal" tabindex="-1"
            role="dialog" aria-labelledby="addSendingOrderModalLabel" aria-hidden="true">

            <div class="modal-dialog" role="document">

                <div class="modal-content">

                    <div class="modal-header">

                        <h5 class="modal-title" id="addSendingOrderModalLabel"><spring:message code="sending_order_page.text.dispatch.goods" /></h5>

                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                            <span aria-hidden="true">&times;</span>

                        </button>

                    </div>

                    <div class="modal-body">

                        <form id="sendingOrderForm">

                            <input type="hidden" name="idOrder" id="order-id-hidden">

                            <div class="form-group">

                                <label for="country"><spring:message code="sending_order_page.text.tracking.index" /></label>
                                <input type="text" class="form-control" id="trackingIndex" name="trackingIndex"/>

                            </div>

                            <button type="submit" class="btn btn-primary"><spring:message code="sending_order_page.text.send" /></button>

                        </form>

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

            $(document).ready(function () {

                $('#addSendingOrderModal').on('show.bs.modal', function (event) {

                    var button = $(event.relatedTarget);
                    var idOrder = button.data('id');

                    var modal = $(this);

                    modal.find('#order-id-hidden').val(idOrder);

                    modal.find('form').attr('action', '${pageContext.request.contextPath}/sendingOrder');

                });

            });

        </script>

    </body>

</html>