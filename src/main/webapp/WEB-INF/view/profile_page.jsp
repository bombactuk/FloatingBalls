<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Profile" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="profile-container">

            <div class="profile-card">

                <div class="profile-info">

                    <h2 class="profile-title"><spring:message code="profile_page.text.profile.user" /></h2>

                    <p><strong><spring:message code="profile_page.text.profile.login" /></strong> user123</p>
                    <p><strong><spring:message code="profile_page.text.profile.name" /></strong> Иван Иванов</p>
                    <p><strong><spring:message code="profile_page.text.profile.birthday" /></strong> 01.01.1990</p>
                    <p><strong><spring:message code="profile_page.text.profile.telephone" /></strong> +7 123 456 7890</p>

                </div>

                <button class="logout-button"><spring:message code="profile_page.button.profile.exit" /></button>

            </div>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>