<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<fieldset>
    <legend>Derniers messages publiés tout le monde</legend>
    <!--pour chaque message de tout le monde-->
    <form action="/partage" method="post">
        <fieldset>
            <legend><a href="/profilDeLaPersonne">Nom de la personne</a>, date</legend>
            Texte de la gazouille
        </fieldset>
        <input type="hidden" name="idGazouille" value=""/>
        <button type="submit" class="btn btn-default">Partager</button>
    </form>
    <form action="/plus">
        <button type="submit" class="btn btn-default">Voir plus</button>
    </form>
</fieldset>
<fieldset>
    <legend>Derniers messages publiés personnes suivies</legend>
    <!--pour chaque message des personnes suivies -->
    <form action="/partage" method="post">
        <fieldset>
            <legend><a href="/profilDeLaPersonne">Nom de la personne</a>, date</legend>
            Texte de la gazouille
        </fieldset>
        <input type="hidden" name="idGazouille" value=""/>
        <button type="submit" class="btn btn-default">Partager</button>
    </form>
    <form action="/plus">
        <button type="submit" class="btn btn-default">Voir plus</button>
    </form>
</fieldset>
</body>
</html>
