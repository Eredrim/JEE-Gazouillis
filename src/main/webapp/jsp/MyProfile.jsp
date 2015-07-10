<%@ page import="main.java.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.model.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Person person = (Person) request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="HeadCSS.jsp"%>
    <title>Mon Profil</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="body container-fluid" style="margin-top: 50px;">
    <div class="row col-md-12">
        <div class="col-md-4" id="infos">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h2 class="panel-title">Informations</h2>
                </div>
                <div class="panel-body">
                    <h3 class="text-center"><%= person.getUsername() %>, <%= person.getCity() %></h3>
                    <br/>
                    <h5>Nombre d'abonnées : <span class="badge"><%= request.getAttribute("followerNumber") %></span></h5>
                    <h5>Nombre de messages publiés : <span class="badge"><%= request.getAttribute("publishedMessageNumber") %></span></h5>
                    <br/>
                    <%
                        if ((Integer)request.getAttribute("followerNumber") != 0) {
                    %>
                        <h4>Abonnés</h4>
                    <%
                        }
                    %>
                    <div class="list-group">
                        <%
                            for (Person following : (List<Person>)request.getAttribute("followings")) {
                        %>
                            <a href="#" class="list-group-item"><%= following.getUsername() %></a>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4" id="gazouillis">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h2 class="panel-title">Gazouillis</h2>
                </div>
                <div class="panel-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="/myProfile" method="post" class="form-horizontal">
                                    <div class="form-group ">
                                        <textarea name="messageContent" id="nouveauGazouilli" class="form-control" cols="50" rows="3" placeholder="Nouveau gazouilli" style="resize: none"></textarea>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="isMessageDraft" id="checkBrouillon"> Est-ce un brouillon ?
                                        </label>
                                    </div>
                                    <div class="col-md-3 col-md-offset-9">
                                        <button type="submit" class="btn btn-primary" name="saveMessage">Enregistrer</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <hr/>
                        <%
                            List<Message> draftMessages = (List<Message>)request.getAttribute("draftMessages");

                            if (draftMessages.size() != 0) {
                        %>
                            <h4 class="text-center">Brouillons</h4>
                        <%
                            }
                            for (Message draftMessage : draftMessages) {
                        %>
                            <div class="row">
                                <div class="col-md-12">
                                    <form action="/myProfile" method="post" class="form-horizontal">
                                        <div class="form-group">
                                            <textarea name="messageContent" class="form-control" cols="50" rows="3" style="resize: none"><%= draftMessage.getContent() %></textarea>
                                        </div>
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="isMessageDraft" checked> Est-ce un brouillon ?
                                            </label>
                                        </div>
                                        <input type="hidden" name="idMessage" value="<%= draftMessage.getId() %>">
                                        <div class="col-md-3 col-md-offset-9">
                                            <button type="submit" class="btn btn-primary" name="updateMessage">Sauvegarder</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <hr/>
                        <%
                            }
                        %>
                        <%
                            List<Message> publishedMessages = (List<Message>)request.getAttribute("publishedMessages");
                            if (publishedMessages.size() != 0) {
                        %>
                            <h4 class="text-center">Gazouillis publiés</h4>
                        <%
                            }
                            for (Message publishedMessage : publishedMessages) {
                        %>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="publishedMessage<%= publishedMessage.getId() %>" class="control-label"><small><%= publishedMessage.getUpdatedAt().toLocaleString() %></small></label>
                                        <textarea class="form-control" id="publishedMessage<%= publishedMessage.getId() %>" cols="50" rows="3" style="resize: none" readonly><%= publishedMessage.getContent() %></textarea>
                                    </div>
                                </div>
                            </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4" id="edit">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h2 class="panel-title">Modifier mes informations personnelles</h2>
                </div>
                <div class="panel-body" style="padding: 30px;">
                    <form class="form-horizontal" action="/myProfile" method="post" id="formEditInfos">
                        <div class="form-group">
                            <label for="formUsername" class="control-label">Nom d'utilisateur</label>
                            <input type="text" class="form-control" maxlength="25" name="username" id="formUsername" value="<%= person.getUsername() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="formNom" class="control-label">Nom</label>
                            <input type="text" class="form-control" maxlength="25" name="name" id="formNom" value="<%= person.getName() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="formPrenom" class="control-label">Prénom</label>
                            <input type="text" class="form-control" maxlength="25" name="firstName" id="formPrenom" value="<%= person.getFirstName() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="formVille" class="control-label">Ville</label>
                            <input type="text" class="form-control" name="city" id="formVille" value="<%= person.getCity() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="formEmail" class="control-label">Email</label>
                            <input type="email" class="form-control" name="mail" id="formEmail" value="<%= person.getMail() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="formMdp" class="control-label">Mot de passe</label>
                            <input type="password" class="form-control" name="password" id="formMdp"/>
                        </div>
                        <div class="form-group">
                            <label for="formVerification" class="control-label">Vérification</label>
                            <input type="password" class="form-control" id="formVerification"/>
                        </div>

                        <button type="submit" class="btn btn-primary pull-right" name="updateProfile">Enregistrer</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="FooterJS.jsp"%>
<script type="text/javascript">
    $(function() {
        $('#formEditInfos>div').removeClass("has-error");

        $('#formEditInfos').submit(function(event) {
            var valid = true;

            if ($('#formUsername').val() == '') {
                valid = false;
                $('#formUsername').parent().addClass("has-error");
            }

            if ($('#formNom').val() == '') {
                valid = false;
                $('#formNom').parent().addClass("has-error");
            }

            if ($('#formPrenom').val() == '') {
                valid = false;
                $('#formPrenom').parent().addClass("has-error");
            }

            if ($('#formVille').val() == '') {
                valid = false;
                $('#formVille').parent().addClass("has-error");
            }

            if ($('#formEmail').val() == '') {
                valid = false;
                $('#formEmail').parent().addClass("has-error");
            }

            if($('#formMdp').val() != $('#formVerification').val()) {
                valid = false;
                $('#formMdp').parent().addClass("has-error");
                $('#formVerification').parent().addClass("has-error");
            }

            if (valid == false) {
                event.preventDefault();
            }
        });
    });
</script>
</body>
</html>
