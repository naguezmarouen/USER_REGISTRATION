# USER_REGISTRATION
# Introduction

Ceci est la documentation de l'API d'enregistrement d'utilisateurs.

# Pourquoi ?

Cette API permet d'enregistrer et de voir les détails des utilisateurs. Le code applicatif backend est écrit en Java 11 et utilise Spring Boot 2.7 avec des tests unitaires Junit 5 et Mockito.

# Structure du code

- [application]
  - packages
    - controllers
    - entities
	- enums
    - exceptions
    - repositories
    - services
      - Services
      - implementation
 - dependencies management
   - pom.xml

# Comment builder ce projet ?

Pour builder ce projet (obligatoire après un premier git clone ou une génération)

	Si vous souhaitez cloner ce projet, vous pouvez utiliser la commande suivante :

	git clone https://github.com/naguezmarouen/USER_REGISTRATION.git


# Création d'Utilisateur

	URL : http://localhost:8080/user/create
	
	Méthode : POST
	
		Données JSON

		{
			"userName": "marwen Naguez",
			"birthDateUser": "1990-09-23",
			"phone": "0659595959",
			"gender": "HOMME",
			"country": {
				"id" : 1,
				"countrie": "France"
			}
		}
	Description : Cette requête crée un nouvel utilisateur avec les informations spécifiées. Assurez-vous de remplacer les valeurs avec les données réelles.

# Récupération d'Utilisateur

    URL : http://localhost:8080/user/{id}
	
	Méthode : GET
	
		Paramètre URL : Remplacez {id} par l'identifiant de l'utilisateur que vous souhaitez récupérer.
		Description : Cette requête permet de récupérer les détails d'un utilisateur existant en spécifiant son identifiant dans l'URL.

# Configuration sur Gitlab

	Dans cette structure mise à jour, j'ai ajouté des liens vers les fichiers de services (UserService et UserServiceImpl) et j'ai inclus des URL pour les services. Assurez-vous de personnaliser les URL des services selon la structure réelle de votre projet.



