# Rapport Projet JAVA - FARIA Lucas, SASIKUMAR Sahkana & SYLLA Elhadj - LSI1

## Login et mot de passe pour tester l'application
- **Lien URL du site :** https://my-follow-up-student-service.onrender.com/login
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

1.  Accéder à l’application à l’adresse : https://my-follow-up-student-service.onrender.com/login
- Attendre son chargement si nécessaire (temps d'attente d'environ 2 min).

2. Pour tester l'application localement :
- Dézipper le fichier .zip.
- Modifier les variables d'environnements dans application.properties ou dans Run Configurations : 
  - **DB_USER** : apprentices_db_user
  - **DB_PASSWORD** : 0hPvuVscA1IjAcxP994A32cIptTW7P9C
  - **DB_URL** : jdbc:postgresql://dpg-d40d7qodl3ps73ct8ffg-a:5432/apprentices_db
- Lancer le projet avec IntelliJ ou via la commande : 
    `mvn spring-boot:run`
---

## Structure du projet 

## `src/main/java`

---

### **config**

Ce dossier contient toute la configuration liée à la sécurité et à la gestion des accès.
On y trouve la classe **SecurityConfig** qui configure :
- Spring Security
- Les règles d’authentification
- Les droits d’accès selon le rôle utilisateur  

C’est également ici que sont gérées les **redirections après connexion** et la **restriction d’accès** à certaines URLs.

---

### **controller**
Ce dossier regroupe tous les **contrôleurs** de l’application, séparés selon leur rôle :

#### 🔹 `rest/`
Contient les **contrôleurs REST (API)** pour chaque entité.  
Ces classes gèrent les requêtes HTTP entre le front-end et le back-end et renvoient les données au format JSON.

Exemples :
- `ApprenticeController`
- `CompanyController`
- `EvaluationController`
- `MasterController`
- `MissionController`
- `OralController`
- `ReportController`
- `VisitController`

Chaque contrôleur REST :
- correspond à une entité du modèle,
- fait le lien entre les **services** et la **base de données** via les **repositories**.

#### 🔹 `web/`
Contient les contrôleurs liés aux **pages web Thymeleaf**, notamment :
- **`LoginController`** → gestion de la connexion et de la session tuteur.
- **`WebController`** → navigation entre les pages (détails, ajout, modification, archives…).

---

### **exception**
Ce dossier regroupe toutes les **exceptions personnalisées** du projet.  
Elles servent à :

- Gérer les erreurs spécifiques à chaque entité (ex. : `ApprenticeNotFoundException`).
- Contrôler les erreurs de validation des saisies.
- Fournir des messages d’erreur clairs à l’utilisateur.

Ces exceptions sont ensuite **gérées globalement** pour renvoyer une réponse adaptée côté front.

---

### **model**
Ce dossier contient toutes les **entités JPA** du projet, représentant les **tables de la base de données**.

Principales entités :
`Apprentice`, `Company`, `Evaluation`, `Master`, `Mission`, `Oral`, `Report`, `Visit`.

Chaque entité contient ses annotations JPA :
```java
@Entity
@Id
@GeneratedValue
@ManyToOne
```

Ces annotations définissent les **relations entre les tables** (ex. : un `Apprentice` est lié à une `Company` et un `Master`).

---

### **model/enums**

Ce sous-dossier contient les **énumérations** utilisées dans les entités (statuts, rôles, types…).  
Elles permettent de :
- standardiser les valeurs,
- éviter les erreurs liées aux chaînes de caractères,
- améliorer la lisibilité et la cohérence du code.

---

### **repository**

Regroupe tous les **repositories JPA**, qui héritent de `JpaRepository`.  
Chaque entité possède son repository dédié (ex. `ApprenticeRepository`, `CompanyRepository`, etc.).

Les repositories permettent :
- les opérations CRUD sur la base de données,
- d’interagir avec les entités via les services.

Ils sont injectés grâce à `@RequiredArgsConstructor`, rendant le code plus propre et structuré.

---

### **service**

Ce dossier contient les **services métier** associés à chaque entité.  
Chaque service gère :
- la **logique métier**,
- la **validation**,
- le **traitement des données**,
- les **appels aux repositories**.

Exemples :  
`ApprenticeService`, `MissionService`, `ReportService`…

Cette couche permet de **séparer la logique métier** du simple échange de données et d’assurer une **architecture claire et modulaire**.

---

### **util**

Contient les **classes utilitaires**, notamment pour :
- la **validation** des données,
- la **gestion d’exceptions** liées à la base,
- des **méthodes réutilisables** (ex. vérification de format, contrôle d’unicité, etc.).

---

### `src/main/resources`

---

### **static/css**

Contient les **fichiers CSS** utilisés pour le **style de l’interface web**  
(mise en page, couleurs, design général).

---

### **templates**

Regroupe les **fichiers HTML Thymeleaf**.  
C’est ici que sont définies les **vues affichées à l’utilisateur**, reliées aux contrôleurs web (`LoginController`, `WebController`).

Chaque page correspond à une **fonctionnalité ou section** de l’application :
- connexion,
- affichage,
- tableaux,
- formulaires, etc.

---

### **tmp**

Ce dossier contient :
- le **script SQL de création de la base**,
- les **données d’initialisation**.
- le fichier pour l'**import de csv**.

Ces scripts permettent de **préparer rapidement la base** lors du premier déploiement ou de la réinitialisation du projet.

---

### **application.properties**

Fichier principal de configuration Spring Boot.  
Il contient :
- les **paramètres de la base de données**,
- le **port de l’application**,
- les **identifiants** et options.

---

### **import.sql**

Script d’import exécuté automatiquement au démarrage pour insérer des **données de base**,

---

## Racine du projet

- **`Rapport.md`** → le présent rapport du projet.
- **`Dockerfile`** → configuration du déploiement sur Render (construction et lancement du conteneur).
- **`pom.xml`** → fichier Maven listant les dépendances, plugins et configurations (Spring Boot, JPA, Thymeleaf, Lombok…).

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
**Elhadj** s’est principalement concentré sur la création des modèles JPA et la gestion de la base de données.
Il a rédigé les scripts SQL d’initialisation et assuré la cohérence entre les entités et les relations.
Il a également participé à la création de plusieurs services backend, notamment dans :

- **MasterServiceImpl** → mise en place de la validation dans la méthode validateMasterRequiredFields(),
- **CompanyServiceImpl** → gestion des doublons d’entreprises et normalisation des noms,
- **MissionServiceImpl** → ajout de la logique pour la création et la mise à jour des missions.

C’est aussi lui qui a développé le bonus du projet, en ajoutant des fonctionnalités supplémentaires autour de la gestion des apprentis et l’import de données CSV.
Enfin, elle a participé à la mise en place de la sécurité (SecurityConfig), pour gérer la connexion et le rôle “tuteur”.

**Sahkana** a pris en charge le développement des contrôleurs principaux et la coordination entre le backend et le front.
Elle a codée la majorité des endpoints REST dans :
- **ApprenticeController.java**
- **CompanyController.java**
- **VisitController.java**
Elle a également centralisé la gestion des exceptions liées à la base de données, notamment via la création d’exceptions personnalisées.
Mais aussi assuré la liaison entre le front et le back, en s’assurant que les données transmises par les contrôleurs REST soient bien interprétées et affichées dans les pages Thymeleaf.

**Lucas** a principalement travaillé sur la couche front et service, en assurant la validation des saisies utilisateur et la gestion des erreurs.
Il a implémenté les règles de validation dans la classe ValidationUtils.java. Il a aussi veillé à la clarté des messages d’erreur affichés aux utilisateurs dans les formulaires Thymeleaf et à la gestion des exceptions liées emails et téléphones existants.

Dans le front, il a ajusté plusieurs vues HTML (dashboard.html, apprentice-details.html).
Il a également contribué à des classes de service comme ApprenticeServiceImpl et ReportServiceImpl, en ajoutant les validations et messages utilisateur avant la sauvegarde en base.

Même si chaque membre avait une partie spécifique, nous avons tous contribué au front-end, notamment sur les pages Thymeleaf et le style CSS.

### d) Si vous deviez retenir trois points de ce cours en général et de ce projet en particulier, quels seraient ces trois points ?
- La gestion des relations JPA entre entités et mapping précis
- L'utilisation de Spring MVC + Thymeleaf pour créer des interfaces web dynamiques 
- La mise en place de la sécurité avec Spring Security et gestion des rôles utilisateurs

### e) Les fonctionnalités que vous n'avez pas eu le temps de mettre en œuvre et pourquoi.
- Une interface plus ergonomique et esthétique avec une librairie externe comme angular. Nous avons commencé mais il y a une trop grosse différence de qualité entre le frontend angular et thymeleaf donc nous avons fait le choix de ne pas l'intégrer

On a pas eu l'occasion de réaliser par choix car nous avons choisi de prioriser la stabilité des fonctionnalités principales et surtout la qualité du code.

### f) A quel niveau, dans votre projet, avez-vous réussi à respecter entièrement ou partiellement les principes SOLID ?

NNous avons essayé de respecter au mieux les principes SOLID dans la structure du projet :
- **Single Responsibility (SRP)** : chaque classe (Service, Controller, Repository) a un rôle bien défini.
- **Open/Closed (OCP)** : Il y a une réelles dissociations entre les classes, elles sont découplés grace à des interfaces bien placés
- **Liskov Substitution (LSP)** : les entités peuvent être utilisées sans casser la logique globale.
- **Interface Segregation (ISP)** : Les interfaces n’ont pas de méthodes inutiles.
- **Dependency Inversion (DIP)** : Utilisation de l’injection de dépendances avec @RequiredArgsConstructor pour les dépendances abstraites (Service, Repository)

### Conclusion
Ce projet nous a permis de vraiment comprendre le fonctionnement d’une application Spring Boot complète, du backend jusqu’au front.
On a appris à gérer les exceptions, à valider les saisies utilisateur et à maintenir un code propre et organisé. Un axe principale de ce projet à été la maintenabilité du projet, nous avons donné ce que nous pouvons pour essayer de permetttre une maintenabilité durable et une gestion des bug la plus simple possible. Nous avons aussi appris / consolidé nos connaissances en Gestion de Projet ainsi que l'utilisation d'application de versioning comme git. 
