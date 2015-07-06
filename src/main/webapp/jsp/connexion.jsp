<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
<div class="headerNonConnecte">
    <h1>Bienvenue sur Gazouillis</h1>
    <!-- insérer image -->
</div>
<fieldset>
    <legend>Inscription</legend>
    <form class="form-inline" action="/inscription" method="post">
        <label for="formUsername">Nom d'utilisateur</label><input type="text" name="formUsername" id="formUsername"/>
        <label for="formNom">Nom</label><input type="text" name="formNom" id="formNom"/>
        <label for="formPrenom">Prénom</label><input type="text" name="formPrenom" id="formPrenom"/>
        <label for="formVille">Ville</label><input type="text" name="formVille" id="formVille"/>
        <label for="formEmail">Email</label><input type="text" name="formEmail" id="formEmail"/>
        <label for="formMdp">Mot de passe</label><input type="text" name="formMdp" id="formMdp"/>
        <label for="formVerification">Vérification</label><input type="text" name="formVerification"
                                                                 id="formVerification"/>
        <button type="submit" class="btn btn-default">S'inscrire</button>
    </form>
</fieldset>
<fieldset>
    <legend>Connexion</legend>
    <form class="form-inline" action="/connexion" method="post">
        <label for="username">Nom d'utilisateur</label><input type="text" name="username" id="username"/>
        <label for="mdp">Nom</label><input type="text" name="mdp" id="mdp"/>
        <button type="submit" class="btn btn-default">Se connecter</button>
    </form>
</fieldset>
</body>
</html>
