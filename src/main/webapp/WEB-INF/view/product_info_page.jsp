<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Product Info" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <div class="product-info-container">

            <div class="product-info">

                <div class="product-image-container">

                    <div class="main-image-container">

                        <img id="mainImage" src="${images[0].link}" alt="${product.title}" class="product-info-image"/>

                    </div>

                    <c:if test="${fn:length(images) > 1}">

                        <div class="thumbnail-container">

                            <c:forEach var="image" items="${images}" varStatus="status">

                                <img src="${image.link}" alt="${product.title}" class="thumbnail"
                                    onclick="setMainImage('mainImage', 'thumbnail', '${image.link}')"/>

                            </c:forEach>

                        </div>

                    </c:if>

                    <div class="product-info-details">

                        <h1 class="product-info-title">${product.title}</h1>
                        <p class="product-info-description">${product.productInfo.content}</p>
                        <p class="product-info-price"><spring:message code="product_info_page.text.price" /> ${product.price} руб.</p>
                        <p class="product-info-date"><spring:message code="product_info_page.text.date" /> ${product.productInfo.datePost}</p>

                    </div>

                    <a href="${pageContext.request.contextPath}/addProductFeatured?idProduct=${product.idProduct}" class="product-info-button">

                        <spring:message code="product_info_page.text.add.favorites" />

                    </a>

                    <a href="${pageContext.request.contextPath}/addProductBasket?idProduct=${product.idProduct}" class="product-info-button">

                        <spring:message code="product_info_page.text.add.basket" />

                    </a>

                </div>

            </div>

            <div class="reviews-section">

                <div class="review-form">

                    <h3><spring:message code="product_info_page.text.leave" /></h3>

                    <form:form modelAttribute="addReviews" action="${pageContext.request.contextPath}/addReview?idProduct=${product.idProduct}">

                        <label for="reviewContent"><spring:message code="product_info_page.text.review" /></label>
                        <form:textarea id="reviewContent" path="content" rows="4" />

                        <button type="submit"><spring:message code="product_info_page.text.review.post" /></button>

                    </form:form>

                </div>

                <div class="reviews-block">

                    <h2 class="reviews-title"><spring:message code="product_info_page.text.review.customer" /></h2>

                    <c:forEach var="review" items="${reviews}">

                        <div class="review">

                            <div class="reviewer-info">

                                <span class="reviewer-name">${review.user.infoUser.name}</span>

                                <span class="review-date">${review.datePost}</span>

                            </div>

                            <p class="review-content">${review.content}</p>

                        </div>

                    </c:forEach>

                </div>

            </div>

        </div>

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

     <script>
            function setMainImage(mainImageId, thumbnailsClass, src) {
                var mainImage = document.getElementById(mainImageId);
                var thumbnails = document.getElementsByClassName(thumbnailsClass);
                var oldSrc = mainImage.src;

                // Установить новое главное изображение
                mainImage.src = src;

                // Обновить стили для миниатюр
                for (var i = 0; i < thumbnails.length; i++) {
                    if (thumbnails[i].src === src) {
                        thumbnails[i].classList.add("selected");
                    } else if (thumbnails[i].src === oldSrc) {
                        thumbnails[i].classList.remove("selected");
                    }
                }
            }

            function initializeThumbnails(mainImageId, thumbnailsClass) {
                var mainImage = document.getElementById(mainImageId);
                var thumbnails = document.getElementsByClassName(thumbnailsClass);
                var currentSrc = mainImage.src;

                for (var i = 0; i < thumbnails.length; i++) {
                    if (thumbnails[i].src === currentSrc) {
                        thumbnails[i].classList.add("selected");
                    } else {
                        thumbnails[i].classList.remove("selected");
                    }
                }
            }
        </script>

        <script>

            // Check if there's a message to display

                <c:if test="${not empty successful}">

                    Swal.fire({

                        title: '<spring:message code="message.text" />',
                        text: '${successful}',
                        confirmButtonText: 'OK'

                    });

                </c:if>

        </script>

    </body>

</html>