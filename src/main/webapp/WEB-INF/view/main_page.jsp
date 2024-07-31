<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Main" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="container-update">

            <div class="upper-panel-update">

                <img src="resources/images/club-banner.jpg" alt="Top Shop Image" class="upper-panel-image-update">
                <div class="upper-panel-text-update"><spring:message code="main_page.text.paintball.store" /></div>

            </div>

            <div class="updates-container" id="updates-container">

                <c:forEach var="update" items="${updates}">

                    <div class="update-card">

                        <img src="${update.image}" alt="${update.title}" class="update-image"/>

                        <div class="update-content">

                            <h2 class="update-title">${update.title}</h2>
                            <p class="update-text">${update.content}</p>
                            <p class="update-date">${update.datePost}</p>

                        </div>

                    </div>

                </c:forEach>

                <button id="next-button-update" class="next-button-update"></button>

            </div>

            <div class="updates-container-top" id="updates-container-top">

                <c:forEach var="banner" items="${banners}">

                    <div class="top-panel-update">

                        <img src="${banner.image}" alt="Top Shop Image" class="top-panel-image-update">
                        <div class="top-panel-text-update">${banner.title}</div>

                    </div>

                </c:forEach>

                <button id="next-button-update-top" class="next-button-update-top"></button>

            </div>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

        <script>

            document.addEventListener('DOMContentLoaded', function() {
                const updates = document.querySelectorAll('.update-card');
                const nextButton = document.getElementById('next-button-update');
                let currentIndex = 0;
                let intervalId;

                function showUpdate(index) {
                    updates.forEach((update, i) => {
                        update.classList.remove('active');
                        if (i === index) {
                            update.classList.add('active');
                        }
                    });
                }

                function nextUpdate() {
                    currentIndex = (currentIndex + 1) % updates.length;
                    showUpdate(currentIndex);
                }

                // Показываем первую новость
                showUpdate(currentIndex);

                // Устанавливаем интервал для автопрокрутки
                intervalId = setInterval(nextUpdate, 5000); // Прокручиваем каждые 5 секунд

                // Обработчик нажатия на кнопку
                nextButton.addEventListener('click', function() {
                    clearInterval(intervalId); // Останавливаем автопрокрутку при ручном переключении
                    nextUpdate();
                    intervalId = setInterval(nextUpdate, 5000); // Перезапускаем автопрокрутку
                });
            });

        </script>

        <script>

            document.addEventListener('DOMContentLoaded', function() {

                const updatesTop = document.querySelectorAll('.top-panel-update');
                const nextButtonTop = document.getElementById('next-button-update-top');
                let currentIndexTop = 0;
                let intervalIdTop;

            function showUpdateTop(index) {

                updatesTop.forEach((update, i) => {

                    update.classList.remove('active');

                    if (i === index) {

                        update.classList.add('active');

                    }

                });

            }

            function nextUpdateTop() {

                currentIndexTop = (currentIndexTop + 1) % updatesTop.length;
                showUpdateTop(currentIndexTop);

            }

            // Показываем первый баннер
            showUpdateTop(currentIndexTop);

            // Устанавливаем интервал для автопрокрутки
            intervalIdTop = setInterval(nextUpdateTop, 5000); // Прокручиваем каждые 5 секунд

            // Обработчик нажатия на кнопку
            nextButtonTop.addEventListener('click', function() {

                clearInterval(intervalIdTop); // Останавливаем автопрокрутку при ручном переключении
                nextUpdateTop();
                intervalIdTop = setInterval(nextUpdateTop, 5000); // Перезапускаем автопрокрутку

            });

            });

        </script>

    </body>

</html>