<%@page import="main.java.model.Message"%>
<%@page import="main.java.model.Person"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person p = (Person) request.getAttribute("profile");
    Person me = (Person) session.getAttribute("connectedPerson");
%>
<html>
    <head>
        <title>Profil de <% out.print(p.getUsername()); %></title>
    </head>
    <body>
        <div>
            <div><% out.print(p.getUsername() + ", " + p.getCity()); %></div>
            <div>Nombre d'abonnés : <% out.print(p.getFollowers().size()); %></div>
            <div>Nombre de messages publiés : <% out.print(p.getPublishedMessages().size()); %></div>
        </div>
        <div>
            <form action="/profile" method="post">
                <input type="hidden" name="userToFollow" value="<% out.print(p.getId()); %>"/>
                <button type="submit" class="btn btn-default">
                <% if(me.isFollowing(p)){
                    out.print("Se désabonner");
                }
                else
                {
                    out.print("S'abonner");
                }%>
                </button>
            </form>
        </div>
        <div>
            <% for (Message msg : p.getPublishedMessages()) { %>
            <!-- pour chaque message -->
            <form action="/share" method="post">
                <fieldset>
                    <legend><a href=""><% out.print(p.getUsername());%></a>, <% out.print(msg.getUpdatedAt());%></legend>
                    <% out.print(msg.getContent());%>
                </fieldset>
                <input type="hidden" name="idGazouille" value="<% out.print(msg.getId());%>"/>
                <button type="submit" class="btn btn-default">Partager</button>
            </form>
            <%}%>
        </div>
    </body>
</html>
