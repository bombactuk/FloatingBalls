<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Authorization" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="login-container">

            <h2><spring:message code="authorization.text.login" /></h2>

            <form action="${pageContext.request.contextPath}/login" method="post">

                <c:if test="${not empty successfulRegistration}">

                    <span class="success">${successfulRegistration}</span>

                </c:if>

                <div class="input-container-authorization">

                    <label for="username"><spring:message code="authorization.text.email" /></label>
                    <input type="text" id="username" name="login" required>

                </div>

                <div class="input-container-authorization">

                    <label for="password"><spring:message code="authorization.text.password" /></label>
                    <input type="password" id="password" name="password" required>

                </div>

                <button class="button-authorization" type="submit"><spring:message code="authorization.button.login" /></button>

            </form>

            <a href="${pageContext.request.contextPath}/showRegistrationPage" class="register-button-authorization"><spring:message code="authorization.button.register" /></a>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>