<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    <header>

        <div class="container-header">

            <h1>FloatingBalls</h1>

            <nav>

                    <ul>

                        <sec:authorize access="isAuthenticated()">

                            <li><a href="${pageContext.request.contextPath}/showMainPage"><spring:message code="header.text.home" /></a></li>
                            <li><a href="${pageContext.request.contextPath}/showAboutUsPage"><spring:message code="header.text.about" /></a></li>
                            <li><a href="${pageContext.request.contextPath}/showCatalogPage"><spring:message code="header.text.catalog" /></a></li>
                            <li><button onclick="togglePanel()"><spring:message code="header.text.menu" /></button></li>

                        </sec:authorize>

                        <sec:authorize access="!isAuthenticated()">

                            <div class="auth-links">

                                <a href="${pageContext.request.contextPath}/showAuthorizationPage"><spring:message code="header.text.login" /></a>
                                <a href="${pageContext.request.contextPath}/showRegistrationPage"><spring:message code="header.text.register" /></a>

                            </div>

                        </sec:authorize>

                    </ul>

                    <div class="side-panel" id="sidePanel">

                            <span class="close-btn" onclick="togglePanel()">×</span>

                            <ul>

                                <li><a href="${pageContext.request.contextPath}/showProfileUserPage"><spring:message code="header.text.profile" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/showProductFeaturedPage"><spring:message code="header.text.favorites" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/showBasketPage"><spring:message code="header.text.basket" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/showPurchaseHistory"><spring:message code="header.text.purchase.history" /></a></li>

                                <sec:authorize access="hasRole('ADMIN')">

                                    <li><a href="${pageContext.request.contextPath}/showOrderProcessing"><spring:message code="header.text.order.processing" /></a></li>
                                    <li><a href="${pageContext.request.contextPath}/showOrderSending"><spring:message code="header.text.order.sending" /></a></li>

                                </sec:authorize>

                            </ul>

                    </div>

                    <script>

                        function togglePanel() {

                            var panel = document.getElementById('sidePanel');

                                if (panel.classList.contains('open')) {

                                    panel.classList.remove('open');

                                } else {

                                    panel.classList.add('open');

                                }

                        }

                    </script>

            </nav>

        </div>

    </header>