<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Error" />

        </jsp:include>

    </head>

    <div class="error-container-page">

        <div class="error-content-page">

            <h1><spring:message code="error_page.text.error" /></h1>
            <p>${message}</p>
            <a href="<c:url value='/showMainPage' />" class="error-page-button"><spring:message code="error_page.button.main" /></a>

        </div>

    </div>

</html>