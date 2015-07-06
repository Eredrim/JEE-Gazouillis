<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mon Profil</title>
</head>
<body>
<fieldset>
    <legend>Infos</legend>
    <h2>Nom d'utilisateur, Ville</h2>
    <h3>Nombre d'abonnés : </h3>
    <h3>Nombre de messages publiés : </h3>

    <h3>Abonnés</h3>
    <!-- liste des abonnés -->
    <a href="/profilDeLaPersonne">Nom de la personne</a>
</fieldset>
<fieldset>
    <legend>Gazouillis</legend>
    <form action="/nouveauGazouilli">
        <textarea name="nouveauGazouilli" cols="30" rows="10"></textarea>
        <checkbox name="checkBrouillon">Ceci est un brouillon</checkbox>
        <button type="submit" class="btn btn-default">Enregistrer</button>
    </form>
    <!-- pour chaque brouillon -->
    <form action="/nouveauGazouilli">
        <textarea name="nouveauGazouilli" cols="30" rows="10">Texte du brouillon</textarea>
        <button type="submit" class="btn btn-default">Publier</button>
    </form>
    <!-- pour les derniers messages publiés -->
    <div class="cadre">
        <div class="alignDroite">Date</div>
        Texte du gazouilli
    </div>
</fieldset>
<fieldset>
    <legend>Modifier mes informations personnelles</legend>
    <form class="form-inline" action="/modifierInformations" method="post">
        <label for="formUsername">Nom d'utilisateur</label><input type="text" name="formUsername" id="formUsername" value=""/>
        <label for="formNom">Nom</label><input type="text" name="formNom" id="formNom" value=""/>
        <label for="formPrenom">Prénom</label><input type="text" name="formPrenom" id="formPrenom" value=""/>
        <label for="formVille">Ville</label><input type="text" name="formVille" id="formVille" value=""/>
        <label for="formEmail">Email</label><input type="text" name="formEmail" id="formEmail" value=""/>
        <label for="formMdp">Mot de passe</label><input type="text" name="formMdp" id="formMdp" value=""/>
        <label for="formVerification">Vérification</label><input type="text" name="formVerification"
                                                                 id="formVerification" value=""/>
        <button type="submit" class="btn btn-default">Enregistrer</button>
    </form>
</fieldset>
</body>
</html>
