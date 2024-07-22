<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

    <header>

        <div class="container-header">

            <h1>FloatingBalls</h1>

            <nav>

                <ul>

                    <li><a href="${pageContext.request.contextPath}/showMainPage"><spring:message code="header.text.home" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/showAboutUsPage"><spring:message code="header.text.about" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/showCatalogPage"><spring:message code="header.text.catalog" /></a></li>

                    <div class="auth-links">

                        <a href="${pageContext.request.contextPath}/showAuthorizationPage"><spring:message code="header.text.login" /></a>
                        <a href="${pageContext.request.contextPath}/showRegistrationPage"><spring:message code="header.text.register" /></a>

                    </div>

                </ul>

            </nav>

        </div>

    </header>