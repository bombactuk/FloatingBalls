<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>

<html>

    <head>

        <jsp:include page="/WEB-INF/view/blocks/head.jsp">

            <jsp:param name="title" value="Catalog" />

        </jsp:include>

    </head>


    <body>

        <jsp:include page="/WEB-INF/view/blocks/header.jsp" />

        <jsp:include page="/WEB-INF/view/blocks/footer.jsp" />

    </body>

</html>