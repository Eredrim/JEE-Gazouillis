<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="HeadCSS.jsp"%>
    <title>Bienvenue sur Gazouillis</title>
</head>
<body>
<div class="body container-fluid" style="margin-top: 50px;">
    <div class="row col-md-12 text-center">
        <h1>Bienvenue sur Gazouillis</h1>
        <br/>
        <h3>Veuillez vous connecter pour profiter de toutes nos super fonctionnalités</h3>
        <br/>
        <a href="/connection" class="btn btn-primary">Se connecter</a>
    </div>
    <div class="row col-md-offset-4">
        <br/>
        <img src="${pageContext.request.contextPath}/resources/images/bird.png">
    </div>
</div>
<%@include file="FooterJS.jsp"%>
</body>
</html>