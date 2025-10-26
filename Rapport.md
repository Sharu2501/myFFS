# Rapport du projet myFSS - FARIA Lucas, SASIKUMAR Sahkana & SYLLA Elhadj - LSI

## 3.1 Login et mot de passe pour tester l'application
- **Login :** tuteur
- **Mot de passe :** tuteur

---

## 3.2 Informations relatives à l’outillage

### 3.2.1 IDE utilisé
- IntelliJ IDEA

### 3.2.2 SGBD choisi
- MariaDB
- Version : ?

---

## 3.3 Informations utiles pour lancer et tester l'application

1. Cloner le projet depuis le dépôt Git : https://github.com/Sharu2501/myFSS.git
2. Configurer la base de données MariaDB :
    - Créer une base `apprentices_db`
    - Adapter `application.properties` avec votre port si besoin et identifiants
3. Lancer l’application via IntelliJ ou en ligne de commande :
   ```bash
   mvn spring-boot:run

Accéder à l’application à l’adresse : http://localhost:3306

## 3.4 La réponse aux questions suivantes :

### a)Sur quel aspect de votre travail souhaitez-vous attirer l'attention du correcteur ?

### b) Quelle est la plus grande difficulté que vous avez rencontrée ? Comment avez-vous géré/solutionné/contourné cette difficulté ?

### c) Quelle a été la contribution de chaque membre de l'équipe ?

### d) Si vous deviez retenir trois points de ce cours en général et de ce projet en particulier, quels seraient ces trois points ?
- La gestion des relations JPA entre entités et mapping précis
- L'utilisation de Spring MVC + Thymeleaf pour créer des interfaces web dynamiques 
- La mise en place de la sécurité avec Spring Security et gestion des rôles utilisateurs

### e) Les fonctionnalités que vous n'avez pas eu le temps de mettre en œuvre et pourquoi.
- Export CSV et PDF des apprentis et missions
- Notifications par email aux tuteurs
- Raison : Manque de temps et priorité donnée à la stabilité des fonctionnalités principales

### f) A quel niveau, dans votre projet, avez-vous réussi à respecter entièrement ou partiellement les principes SOLID ?

Nous avons tenté de notre mieux pour respecter les principes SOLID. Nous pouvons notamment les retrouver ici :
- **Single Responsibility (SRP)** : Chaque service a une responsabilité unique (ApprenticeService, CompanyService)
- **Open/Closed (OCP)** : Les services sont extensibles via des méthodes supplémentaires sans modification des existantes 
- **Liskov Substitution (LSP)** : Respecté par les entités et leurs relations 
- **Interface Segregation (ISP)** : Les interfaces n’ont pas de méthodes inutiles 
- **Dependency Inversion (DIP)** : Utilisation de l’injection de dépendances avec @RequiredArgsConstructor pour dépendances abstraites (Service, Repository)
