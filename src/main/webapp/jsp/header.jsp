<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String recherche;
    if (request.getAttribute("recherche") != null) {
        recherche = request.getAttribute("recherche").toString();
    } else {
        recherche = "";
    }
%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 col-md-offset-3" style="margin-top: 10px;">
                <div class="navbar-header">
                    <img alt="Brand" src="${pageContext.request.contextPath}/resources/images/bird.png" width="100">
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="/Accueil" >Accueil</a></li>
                        <li><a href="/myProfile" >Mon compte</a></li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search" id="searchForm" method="get" action="/search">
                        <input type="text" id="searchInput" class="form-control" name="recherche" placeholder="Mot-clé / nom d'utilisateur" value="<%= recherche %>">
                        <button class="btn btn-success  " type="submit"><span class="glyphicon glyphicon-search"></span></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</nav>
