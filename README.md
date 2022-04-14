# new_webDysgraphie
Projet de e-santé visant à créer une application d'aide à la détection de dysgraphie chez les jeunes. Pour réaliser cette analyse, le patient doit passer un test de type BHK.

# Web service
## Lien: GET /analyse
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
Returns:
l'objet analyse en entier format json

## Lien : GET /resultat/vitesse/inscription
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
Returns:
Renvoie un objet au format json de type {liste_x:[],liste_y:[],liste_Gauss_x:[],liste_Gauss_y:[]}. 
Il permet de tracer la courbe de Gauss et la la verticale correspondant à la valeur patient.

## Lien : GET /resultat/vitesse
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
Returns:
Renvoie un objet au format json de type {liste_x:[],liste_y:[]}. Il permet de tracer la courbe V(t).

## Lien:GET /resultat/acceleration
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
Returns:
Renvoie un ibjet au format json de type {liste_x:[],liste_y:[],valeurPatient:number}. 
Il permet de tracer la courbe A(t).

## Lien: GET /resultat/jerk
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
Returns:Renvoie un objet au format json de type {liste_x:[],liste_y:[],valeurPatient:number}. 
Il permet de tracer la courbe J(t).

## Lien: GET /resultat/download
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
Returns:L'objet analyse à l'intant t.

## Lien: POST /analyse
Parameters:
typeAnalyse - Type d'examen passé. Il faut envoyer au serveur {typeAnalyse:BHK,BHKADO ou CINEMATIQUE}.
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
Returns:L'objet analyse à l'instant t.

## Lien : POST /materiel
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
materiel - Il faut envoyer au serveur materiel : {materielType:TABLETTEGRAPHIQUE,IPAD ou ECRANTACTILE}
Returns:L'objet analyse à l'instant t.

## Lien: POST /autorisation
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
autorisation - Il faut envoyer au serveur {autorisation:true}.
Returns:L'objet analyse à l'instant t.

## Lien : POST /patient
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
patient - Il faut envoyer un objet Patient.
Returns:L'objet analyse à l'instant t.

## Lien : POST /ecriture
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
body - Il faut juste envoyer au serveur une liste comme {liste_point: {x:1,y:2,...},...}
Returns:L'objet analyse à l'instant t.

## Lien: POST resultat/import
Parameters 
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"
body - excelfile Document format xls, xlsx ou csv.
Returns :
L'ensemble des listes de point nécessaire à la construction des graphs.

## Lien: DELETE /remove
Parameters:
request - Il faut dans chaque requête envoyer un token d'accès dans le header comme : "token":"HGSNSLQUHAB"