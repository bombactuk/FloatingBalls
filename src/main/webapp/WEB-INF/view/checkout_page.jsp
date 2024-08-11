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

            <jsp:param name="title" value="Checkout" />

        </jsp:include>

    </head>

    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="checkout-container">

            <h2><spring:message code="checkout_page.text.payment" /></h2>

            <div class="checkout-form-group">

                <label for="cardNumber"><spring:message code="checkout_page.text.card.number" /></label>
                <input type="text" id="cardNumber" name="cardNumber" required maxlength="16" />

            </div>

            <div class="checkout-form-group">

                <label for="expDate"><spring:message code="checkout_page.text.expiry.date" /></label>
                <input type="text" id="expDate" name="expDate" required placeholder="MM/YY" maxlength="5" />

            </div>

            <div class="checkout-form-group">

                <label for="cvv"><spring:message code="checkout_page.text.cvv" /></label>
                <input type="text" id="cvv" name="cvv" required maxlength="3" />

            </div>

            <a href="${pageContext.request.contextPath}/processPayment" class="checkout-btn">

                <spring:message code="checkout_page.text.pay.now" /> ${sumPriceProduct} р.

            </a>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

        <script>

            document.querySelector('form').addEventListener('submit', function(event) {

                const cardNumber = document.getElementById('cardNumber').value;
                const expDate = document.getElementById('expDate').value;
                const cvv = document.getElementById('cvv').value;
                let valid = true;

                if (!/^\d{16}$/.test(cardNumber)) {

                    valid = false;
                    document.getElementById('cardNumber').classList.add('error');

                }

                if (!/^\d{2}\/\d{2}$/.test(expDate)) {

                    valid = false;
                    document.getElementById('expDate').classList.add('error');

                }

                if (!/^\d{3}$/.test(cvv)) {

                    valid = false;
                    document.getElementById('cvv').classList.add('error');

                }

                if (!valid) {

                    event.preventDefault();

                }

            });

        </script>

    </body>

</html>