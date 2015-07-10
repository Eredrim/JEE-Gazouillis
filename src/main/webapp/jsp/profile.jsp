<%@page import="main.java.model.Message"%>
<%@page import="main.java.model.Person"%>
<%@ page import="main.java.dao.PersonDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person p = (Person) request.getAttribute("profile");
    Person me =(new PersonDAO()).findByUsername((String)session.getAttribute("connectedPerson"));
%>
<html>
    <head>
        <%@include file="HeadCSS.jsp"%>
        <title>Profil de <% out.print(p.getUsername()); %></title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="body container-fluid" style="margin-top: 50px;">
            <div class="row col-md-12">
                <div class="col-md-6" id="infos">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h2 class="panel-title"><%= p.getUsername()%>, <%= p.getCity() %></h2>
                        </div>
                        <div class="panel-body">
                            <p>Nombre d'abonnés : <span class="badge"><%= p.getFollowers().size() %></span></p>
                            <p>Nombre de messages publiés : <span class="badge"><%= p.getPublishedMessages().size() %></span></p>
                            <form action="/profile?user=<%= p.getUsername() %>" method="post" class="form-horizontal">
                                <input type="hidden" name="userToFollow" value="<%= p.getId() %>"/>
                                <button type="submit" class="btn btn-primary pull-right">
                                    <%
                                        if(me.isFollowing(p)){
                                            out.print("Se désabonner");
                                        }
                                        else{
                                            out.print("S'abonner");
                                        }
                                    %>
                                </button>
                            </form>
                            <%
                                if ((Integer)p.getFollowers().size() != 0) {
                            %>
                            <h4>Abonnés</h4>
                            <%
                                }
                            %>
                            <div class="list-group">
                                <%
                                    for (Person following : p.getFollowers()) {
                                %>
                                <a href="/profile?user=<%= following.getUsername() %>" class="list-group-item"><%= following.getUsername() %></a>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6" id="messages">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title">Messages</h2>
                        </div>
                        <div class="panel-body">
                            <%
                                if (p.getPublishedMessages().size() == 0){
                            %>
                            <p>Aucun résultat</p>
                            <%
                                } else {
                                    for (Message message : p.getPublishedMessages()) {
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
    </body>
</html>
