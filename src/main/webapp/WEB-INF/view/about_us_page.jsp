<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="About us" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="about-container">

            <h1><spring:message code="about_us.text.about.our.store" /></h1>

            <p><spring:message code="about_us.text.title.welcome" /></p>

            <h2><spring:message code="about_us.text.title.our.history" /></h2>

            <p><spring:message code="about_us.text.description" /></p>

            <h2><spring:message code="about_us.text.our.values" /></h2>

            <ul>
                <li><spring:message code="about_us.text.product.quality" /></li>
                <li><spring:message code="about_us.text.customer.service" /></li>
                <li><spring:message code="about_us.text.innovation.creativity" /></li>
                <li><spring:message code="about_us.text.honesty.transparency" /></li>
            </ul>

            <h2><spring:message code="about_us.text.our.stores" /></h2>

            <div class="container-shop">

                <c:forEach var="shop" items="${shops}">

                    <div class="shop-item">

                        <h2><spring:message code="about_us.text.address" /></h2>
                        <p>${shop.address}</p><br>

                        <h2><spring:message code="about_us.text.contacts" /></h2>
                        <p><strong><spring:message code="about_us.text.telephone" /></strong> ${shop.contacts}</p><br>

                        <h2><spring:message code="about_us.text.operating" /></h2>
                        <p>${shop.operatingMode}</p>

                    </div>

                </c:forEach>

            </div>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>