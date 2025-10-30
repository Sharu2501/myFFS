# Rapport Projet JAVA - FARIA Lucas, SASIKUMAR Sahkana & SYLLA Elhadj - LSI1

## Login et mot de passe pour tester l'application
- **Lien URL du site :** https://myfss.onrender.com
- **Login :** tuteur
- **Mot de passe :** tuteur
---

## Informations relatives à l’outillage

### IDE utilisé
- IntelliJ IDEA (choisi pour sa bonne intégration avec Spring Boot et Maven)

### SGBD choisi
- **Initialement :** MariaDB
- **Après déploiement :** PostgreSQL 42.7.8 (changement nécessaire pour compatibilité avec Render)
---

## Informations utiles pour lancer et tester l'application

1.  Accéder à l’application à l’adresse : https://myfss.onrender.com
- Attendre son chargement si nécessaire (temps d'attente d'environ 2 min).

2. Pour tester l'application localement :
- Dézipper le fichier .zip.
- Ajouter les variables d'environnements dans application.properties : 
  - DB_USER : 
  - DB_PASSWORD : 
  - DB_URL :
- Lancer le projet avec IntelliJ ou via la commande : 
    `mvn spring-boot:run`
---

## Structure du projet 

### Dans le dossier src/main/java : 
**config**
Ce dossier contient toute la configuration liée à la sécurité et à la gestion des accès.
On y trouve la classe SecurityConfig qui configure Spring Security, les règles d’authentification et les droits d’accès aux différentes pages selon le rôle de l’utilisateur.
C’est ici que sont gérées les redirections après connexion et la restriction de certaines URLs.
---
**controller**
Ce dossier regroupe tous les contrôleurs de l’application séparés selon leur rôle :
- **rest** contient les contrôleurs REST (API) pour chaque entité. Ces classes gèrent les requêtes HTTP entre le front et le backend et renvoient les données au format JSON. 
On y trouve par exemple : ApprenticeController, CompanyController, EvaluationController, MasterController, MissionController, OralController, ReportController et VisiteController.

Chaque contrôleur REST correspond à une entité du modèle et fait le lien entre les services et la base de données via les repositories.
- **web** contient les contrôleurs liés aux pages web rendues avec Thymeleaf.
Avec notamment : 
- **LoginController** pour la gestion de la connexion et de la session tuteur.
- **WebController** pour la navigation entre les différentes pages (détails, ajout, modification, archives, etc).
---
**exception**
Ce dossier regroupe toutes les exceptions personnalisées du projet.
Elles sont utilisées pour :
- Gérer les erreurs spécifiques à chaque entité (ex. : ApprenticeNotFoundException). 
- Contrôler les erreurs de validation des saisies. 
- Fournir des messages d’erreur clairs à l’utilisateur.
Ces exceptions sont ensuite gérées globalement pour renvoyer une réponse adaptée côté front.
---
**model**
Ce dossier contient toutes les entités JPA du projet, c’est-à-dire les classes qui représentent les tables dans la base de données.
Les principales entités sont : Apprentice, Company, Evaluation, Master, Mission, Oral, Report et Visite.
Chaque entité contient ses annotations JPA (@Entity, @Id, @GeneratedValue, @ManyToOne, etc.) pour définir les relations entre les tables (par exemple, un Apprentice peut être lié à une Company ou un Master).
---
**model/enums**

Ce sous-dossier contient les énumérations utilisées dans les entités (par exemple pour définir des statuts, rôles ou types).
Elles permettent de standardiser certaines valeurs au lieu d’utiliser de simples chaînes de caractères, ce qui réduit les erreurs et améliore la lisibilité du code.
---
**repository**
Ce dossier regroupe tous les repositories JPA (interfaces qui héritent de JpaRepository).
Chaque entité a son repository dédié (ApprenticeRepository, CompanyRepository, etc.) qui permet d’effectuer les opérations CRUD directement sur la base de données.
Les repositories sont injectés dans les services grâce à @RequiredArgsConstructor, ce qui rend le code plus propre et mieux structuré.
---

**service**
Ce dossier contient les services associés à chaque entité.
  Chaque service gère la logique métier (validation, traitement des données, appels aux repositories, etc.) avant d’envoyer la réponse au contrôleur.
  Exemples : ApprenticeService, MissionService, ReportService…

Cette couche permet de séparer la logique du projet du simple échange de données et de mieux structurer le code.

---

**util**
Ce dossier contient les classes utilitaires, principalement utilisées pour la validation et la gestion des exceptions liées à la base de données.
Elles regroupent des méthodes réutilisables dans plusieurs parties du projet (par exemple, une méthode de vérification de format avant insertion).

---

Dans le dossier src/main/resources
**static.css**
Contient les fichiers CSS utilisés pour le style de l’interface web (mise en page, couleurs, design général).

---

**templates**
Regroupe les fichiers HTML Thymeleaf.
C’est ici que sont définées les vues affichées à l’utilisateur, liées aux contrôleurs web (LoginController et WebController).
Chaque page correspond à une fonctionnalité ou à une section de l’application (connexion, affichage, tableau, etc.).
---

**tmp**
Ce dossier contient notamment le script SQL de création de la base et les données d’initialisation.
Ces scripts sont utilisés pour préparer la base rapidement lors de l’installation ou du déploiement.

---
**application.properties**
Fichier principal de configuration Spring Boot :
on y définit les paramètres de la base de données, le port de l’application, les identifiants, et d’autres options comme le mode debug ou les stratégies de mise à jour du schéma.

---
**import.sql**
Script d’import exécuté automatiquement au démarrage pour insérer des données de base (ex. : comptes de test, données d’exemple).

---
**Dans la racine du projet** :
- **Rapport.md** : le rapport du projet (celui-ci). 
- **Dockerfile** : utilisé pour le déploiement sur Render. Il définit comment construire et lancer le conteneur de l’application. 
- **pom.xml** : fichier Maven contenant toutes les dépendances, les plugins et la configuration du projet (Spring Boot, JPA, Thymeleaf, Lombok, etc.).

## 3.4 Réponse aux questions :

### a) Sur quel aspect de votre travail souhaitez-vous attirer l'attention du correcteur ?
Nous aimerions que la correction se concentre sur plusieurs points techniques :
- La gestion des exceptions, que nous avons bien travaillée, aussi bien côté backend (erreurs SQL, accès aux données) que côté front (saisies invalides, formats incorrects).
Nous avons la validation des saisies utilisateur à l’aide de patterns pour vérifier les formats avant enregistrement.
- La gestion des conflits en base qui sont également remontés dans le front.
- Le code backend, que nous avons voulu clair, propre et bien structuré, avec une séparation nette entre les couches (Controller, Service, Repository).
- La réalisation des modèles et ses contraintes.
- Une configuration de sécurité pour la connexion du tuteur.

### b) Quelle est la plus grande difficulté que vous avez rencontrée ? Comment avez-vous géré/solutionné/contourné cette difficulté ?
Notre plus grande difficulté a été de poser une base solide pour le projet et de devoir réadapter plusieurs fois le code au fil de l’avancement.
Au départ, tout fonctionnait sous MariaDB, mais pour le déploiement sur Render, nous avons dû migrer vers PostgreSQL.
Cela a demandé de revoir les dépendances et des fonctions d'ajout et de modifications.
Ensuite, plusieurs fonctionnalités du front ont dû être réajustées pour correspondre aux modifications du backend.
Ces adaptations nous ont permis de mieux comprendre la logique des annotations JPA, la portabilité du code Java et l’importance d’avoir une architecture bien pensée dès le début.

### c) Quelle a été la contribution de chaque membre de l'équipe ?
- Elhadj : création des modèles, gestion de la base de données, des scripts SQL et de quelques fonctions du contrôleur.
- Sahkana : développement des services front, gestion des erreurs et des exceptions liées à la saisie utilisateur (vérification des formats et messages d’erreur).
- Lucas : développement des contrôleurs principaux et des exceptions liées à la base de données, ainsi que la coordination entre backend et front.

Même si chacun avait une partie précise, nous avons tous participé au front-end pour garder une cohérence dans le rendu global.

### d) Si vous deviez retenir trois points de ce cours en général et de ce projet en particulier, quels seraient ces trois points ?
- La gestion des relations JPA entre entités et mapping précis
- L'utilisation de Spring MVC + Thymeleaf pour créer des interfaces web dynamiques 
- La mise en place de la sécurité avec Spring Security et gestion des rôles utilisateurs
- La collaboration en équipe et l’organisation du code pour rendre un projet fonctionnel du backend au front.

### e) Les fonctionnalités que vous n'avez pas eu le temps de mettre en œuvre et pourquoi.
- Le bonus d’export CSV des apprentis et missions. 
- Une interface plus ergonomique et esthétique.

On a pas eu l'occasion de réaliser par choix car nous avons choisi de prioriser la stabilité des fonctionnalités principales et surtout la qualité du code.

### f) A quel niveau, dans votre projet, avez-vous réussi à respecter entièrement ou partiellement les principes SOLID ?

NNous avons essayé de respecter au mieux les principes SOLID dans la structure du projet :
- **Single Responsibility (SRP)** : chaque classe (Service, Controller, Repository) a un rôle bien défini.
- **Open/Closed (OCP)** : Les services sont extensibles via des méthodes supplémentaires sans modification des existantes. 
- **Liskov Substitution (LSP)** : les entités peuvent être utilisées sans casser la logique globale.
- **Interface Segregation (ISP)** : Les interfaces n’ont pas de méthodes inutiles.
- **Dependency Inversion (DIP)** : Utilisation de l’injection de dépendances avec @RequiredArgsConstructor pour les dépendances abstraites (Service, Repository)

### Conclusion
Ce projet nous a permis de vraiment comprendre le fonctionnement d’une application Spring Boot complète, du backend jusqu’au front.
On a appris à gérer les exceptions, à valider les saisies utilisateur et à maintenir un code propre et organisé.