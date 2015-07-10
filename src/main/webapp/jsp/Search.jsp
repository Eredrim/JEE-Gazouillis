<%@ page import="main.java.model.Person" %>
<%@ page import="main.java.model.Message" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person me = (Person)request.getAttribute("connectedPerson");
%>
<html>
<head>
    <%@include file="HeadCSS.jsp"%>
    <title>Recherche</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="body container-fluid" style="margin-top: 50px;">
    <div class="row col-md-12">
        <div class="col-md-6" id="persons">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h2 class="panel-title">Résultat d'utilisateur pour <span class="badge"><%= request.getAttribute("recherche") %></span></h2>
                </div>
                <div class="panel-body">
                    <%
                        Person person = (Person)request.getAttribute("person");
                        if (person != null) {
                    %>
                        <div class="list-group">
                            <a href="#" class="list-group-item"><%= person.getUsername() %></a>
                        </div>
                    <%
                        } else {
                    %>
                        <p>Aucun résultat</p>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        <div class="col-md-6" id="messages">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h2 class="panel-title">Résultats de messages pour le mot-clé <span class="badge"><%= request.getAttribute("recherche") %></span></h2>
                </div>
                <div class="panel-body">
                    <%
                        List<Message> messages = (List<Message>)request.getAttribute("messages");
                        if (messages.size() == 0) {
                    %>
                        <p>Aucun résultat</p>
                    <%
                        } else {
                            for (Message message : messages) {
                    %>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="publishedMessage<%= message.getId() %>" class="control-label"><a href="/profile?user=<%= message.getPerson().getUsername() %>"><%= message.getPerson().getUsername() %></a>&nbsp;&nbsp;<small style="color: #cccccc"><%= message.getUpdatedAt().toLocaleString() %></small></label>
                                    <textarea class="form-control" id="publishedMessage<%= message.getId() %>" cols="50" rows="3" style="resize: none" readonly><%= message.getContent() %></textarea>
                                </div>
                                <%
                                    if (!me.getMessagesShared().contains(message)){
                                %>
                                <form action="/share" method="post" class="form-horizontal">
                                    <input type="hidden" name="idGazouille" value="<%= message.getId()%>"/>
                                    <button type="submit" class="btn btn-info pull-right">Partager</button>
                                </form>
                                <%
                                    }
                                %>
                            </div>
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
<script type="text/javascript">
    $(function() {
         $('#searchInput').val(<%= request.getAttribute("recherche") %>)
    });
</script>
</body>
</html>
