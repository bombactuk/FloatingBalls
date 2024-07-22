<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Registration" />

        </jsp:include>

    </head>

    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="register-container">

            <h2><spring:message code="registration.text.register" /></h2>

           <form:form action="${pageContext.request.contextPath}/registerUser" modelAttribute="user" >

                <c:if test="${not empty registrationError}">

                    <span class="error">${registrationError}</span>

                </c:if>

                <div class="input-container-registration">

                    <label for="email"><spring:message code="registration.label.email" /></label>
                    <form:input type="email" id="email" path="login" />
                    <form:errors path="login" cssClass="error" />

                    <c:if test="${not empty emailError}">

                        <span class="error">${emailError}</span>

                    </c:if>

                </div>

                <div class="input-container-registration">

                    <label for="password"><spring:message code="registration.label.password" /></label>
                    <form:input type="password" id="password" path="password" />
                    <form:errors path="password" cssClass="error" />

                </div>

                <div class="input-container-registration">

                    <label for="username"><spring:message code="registration.label.user.name" /></label>
                    <form:input type="text" path="infoUser.name" />
                    <form:errors path="infoUser.name" cssClass="error" />

                </div>

                <div class="input-container-registration">

                    <label for="confirm-birthday"><spring:message code="registration.label.birthday" /></label>
                    <form:input type="date" id="birthday" path="infoUser.birthday" />
                    <form:errors path="infoUser.birthday" cssClass="error" />

                </div>

                <div class="input-container-registration">

                    <label for="confirm-telephone"><spring:message code="registration.label.telephone" /></label>
                    <form:input type="tel" id="telephone" path="infoUser.telephone" />
                    <form:errors path="infoUser.telephone" cssClass="error" />

                </div>

                <button class="button-registration" type="submit"><spring:message code="registration.button.register" /></button>

            </form:form>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>