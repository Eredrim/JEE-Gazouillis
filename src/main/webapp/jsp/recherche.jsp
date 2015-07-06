<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recherche</title>
</head>
<body>
<fieldset>
    <legend>Résultats d'utilisateur pour : nomUtilisateur</legend>
    <!-- liste des utilisateurs -->
    <a href="/profilDeLaPersonne">Nom de la personne</a>
</fieldset>
<fieldset>
    <legend>Résultats de mots-clés pour : nomUtilisateur</legend>
    <!-- pour chaque message -->
    <form action="/partage" method="post">
        <fieldset>
            <legend><a href="/profilDeLaPersonne">Nom de la personne</a>, date</legend>
            Texte de la gazouille
        </fieldset>
        <input type="hidden" name="idGazouille" value=""/>
        <button type="submit" class="btn btn-default">Partager</button>
    </form>
</fieldset>
</body>
</html>
