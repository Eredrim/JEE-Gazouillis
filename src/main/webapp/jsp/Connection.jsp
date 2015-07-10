<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="HeadCSS.jsp"%>
    <title>Mon Profil</title>
</head>
<body>
<div class="container-fluid" style="margin-top: 50px;">
    <div class="col-md-offset-1 col-md-4">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h2 class="panel-title">Inscription</h2>
            </div>
            <div class="panel-body" style="padding: 30px;">
                <form class="form-horizontal" action="/connection" method="post" id="formSubscribe">
                    <div class="form-group">
                        <label for="formSubscribeUsername" class="control-label">Nom d'utilisateur</label>
                        <input type="text" class="form-control" maxlength="25" name="username" id="formSubscribeUsername"/>
                    </div>
                    <div class="form-group">
                        <label for="formSubscribeNom" class="control-label">Nom</label>
                        <input type="text" class="form-control" maxlength="25" name="name" id="formSubscribeNom"/>
                    </div>
                    <div class="form-group">
                        <label for="formSubscribePrenom" class="control-label">Prénom</label>
                        <input type="text" class="form-control" maxlength="25" name="firstName" id="formSubscribePrenom"/>
                    </div>
                    <div class="form-group">
                        <label for="formSubscribeVille" class="control-label">Ville</label>
                        <input type="text" class="form-control" name="city" id="formSubscribeVille"/>
                    </div>
                    <div class="form-group">
                        <label for="formSubscribeEmail" class="control-label">Email</label>
                        <input type="email" class="form-control" name="mail" id="formSubscribeEmail"/>
                    </div>
                    <div class="form-group">
                        <label for="formSubscribeMdp" class="control-label">Mot de passe</label>
                        <input type="password" class="form-control" name="password" id="formSubscribeMdp"/>
                    </div>
                    <div class="form-group">
                        <label for="formSubscribeVerification" class="control-label">Vérification</label>
                        <input type="password" class="form-control" id="formSubscribeVerification"/>
                    </div>

                    <button type="submit" class="btn btn-primary pull-right" name="subscribe">Enregistrer</button>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-offset-2 col-md-4">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h2 class="panel-title">Connexion</h2>
            </div>
            <div class="panel-body" style="padding: 30px;">
                <form class="form-horizontal" action="/connection" method="post" id="formConnect">
                    <div class="form-group">
                        <label for="formConnectUsername" class="control-label">Nom d'utilisateur</label>
                        <input type="text" class="form-control" maxlength="25" name="username" id="formConnectUsername"/>
                    </div>
                    <div class="form-group">
                        <label for="formConnectMdp" class="control-label">Mot de passe</label>
                        <input type="password" class="form-control" name="password" id="formConnectMdp"/>
                    </div>

                    <button type="submit" class="btn btn-primary pull-right" name="connection">Connexion</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
