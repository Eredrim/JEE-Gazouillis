<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
</head>
<body>
    <div>
        <div>Nom d'utilisateur, Ville</div>
        <div>Nombre d'abonnés :</div>
        <div>Nombre de messages publiés :</div>
    </div>
    <div>
        <form action="/abonnement" method="post">
            <input type="hidden" name="idUtilisateur" value=""/>
            <button type="submit" class="btn btn-default">S'abonner / Se désabonner</button>
        </form>
    </div>
    <div>
        <!-- pour chaque message -->
        <form action="/partage" method="post">
            <fieldset>
                <legend><a href="/profilDeLaPersonne">Nom de la personne</a>, date</legend>
                Texte de la gazouille
            </fieldset>
            <input type="hidden" name="idGazouille" value=""/>
            <button type="submit" class="btn btn-default">Partager</button>
        </form>
    </div>
</body>
</html>
