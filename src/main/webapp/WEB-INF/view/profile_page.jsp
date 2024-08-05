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

                    <p><strong><spring:message code="profile_page.text.profile.login" /></strong> ${user.login}</p>
                    <p><strong><spring:message code="profile_page.text.profile.name" /></strong> ${user.infoUser.name}</p>
                    <p><strong><spring:message code="profile_page.text.profile.birthday" /></strong> ${user.infoUser.birthday}</p>
                    <p><strong><spring:message code="profile_page.text.profile.telephone" /></strong> ${user.infoUser.telephone}</p>

                </div>

                <form action="${pageContext.request.contextPath}/logout" method="post">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                    <button type="submit" class="logout-button"><spring:message code="profile_page.button.profile.exit" /></button>

                </form>

            </div>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>