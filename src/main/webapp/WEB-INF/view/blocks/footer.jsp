<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<footer class="hidden-footer">

    <div class="container">

        <div class="footer-section">

            <h3><spring:message code="footer.label.social.media" /></h3>

            <div class="social-media">

                <c:forEach var="social" items="${socialMediaList}">

                    <a href="${social.link}" target="_blank">

                        <img src="${social.image}"/>

                    </a>

                </c:forEach>

            </div>

        </div>

        <div class="footer-section">

            <h3><spring:message code="footer.label.language" /></h3>

            <select id="language-select" onchange="changeLanguage()">

                <option value="" selected><spring:message code="footer.text.language" /></option>
                <option value="en"><spring:message code="footer.text.language.english" /></option>
                <option value="ru"><spring:message code="footer.text.language.russian" /></option>

            </select>

        </div>

        <div id="right">

            <p id="right">Copyright &copy; Your Website</p>

        </div>

    </div>

</footer>

<script>

    function changeLanguage() {

        const language = document.getElementById('language-select').value;
        const currentUrl = window.location.href;
        const url = new URL(currentUrl);
        url.searchParams.set('lang', language);
        window.location.href = url.toString();

    }

</script>

<script>

    document.getElementById('right').innerHTML = '<spring:message code="footer.text.all.rights" />'
    + new Date().getFullYear() + '';

</script>