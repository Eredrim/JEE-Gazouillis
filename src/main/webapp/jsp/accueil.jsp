<%@ page import="main.java.model.Message" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="HeadCSS.jsp"%>
    <title>Accueil</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="body container-fluid" style="margin-top: 50px;">
    <div class="row col-md-12">
        <div class="col-md-6" id="monde">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h2 class="panel-title">Derniers messages de la communauté</h2>
                </div>
                <div class="panel-body">
                    <%
                        List<Message> messagesMonde = (List<Message>)request.getAttribute("messagesMonde");
                        if (messagesMonde.size() == 0) {
                    %>
                    <p>Aucun résultat</p>
                    <%
                    } else {
                        for (Message messageMonde : messagesMonde) {
                    %>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="publishedMessage<%= messageMonde.getId() %>" class="control-label"><a href="/profile?user=<%= messageMonde.getPerson().getUsername() %>"><%= messageMonde.getPerson().getUsername() %></a>&nbsp;&nbsp;<small style="color: #cccccc"><%= messageMonde.getUpdatedAt().toLocaleString() %></small></label>
                                    <textarea class="form-control" id="publishedMessage<%= messageMonde.getId() %>" cols="50" rows="3" style="resize: none" readonly><%= messageMonde.getContent() %></textarea>
                                </div>
                                <%--Quand on aura le connecté : test si on a déjà partagé le message--%>
                                <form action="/share" method="post" class="form-horizontal">
                                    <input type="hidden" name="idGazouille" value="<%= messageMonde.getId()%>"/>
                                    <button type="submit" class="btn btn-info pull-right">Partager</button>
                                </form>
                            </div>
                        </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
        <div class="col-md-6" id="follows">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h2 class="panel-title">Derniers messages des personnes que je suis</h2>
                </div>
                <div class="panel-body">
                    <%
                        List<Message> messagesSuivi = (List<Message>)request.getAttribute("messagesSuivi");
                        if (messagesSuivi.size() == 0) {
                    %>
                    <p>Aucun résultat</p>
                    <%
                    } else {
                        for (Message messageSuivi : messagesSuivi) {
                    %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="publishedMessage<%= messageSuivi.getId() %>" class="control-label"><a href="/profile?user=<%= messageSuivi.getPerson().getUsername() %>"><%= messageSuivi.getPerson().getUsername() %></a>&nbsp;&nbsp;<small style="color: #cccccc"><%= messageSuivi.getUpdatedAt().toLocaleString() %></small></label>
                                <textarea class="form-control" id="publishedMessage<%= messageSuivi.getId() %>" cols="50" rows="3" style="resize: none" readonly><%= messageSuivi.getContent() %></textarea>
                            </div>
                        </div>
                        <%--Quand on aura le connecté : test si on a déjà partagé le message--%>
                        <form action="/share" method="post" class="form-horizontal">
                            <input type="hidden" name="idGazouille" value="<%= messageSuivi.getId()%>"/>
                            <button type="submit" class="btn btn-info pull-right">Partager</button>
                        </form>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="FooterJS.jsp"%>
</body>
</html>
