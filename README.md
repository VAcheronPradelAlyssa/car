
# Your Car Your Way - POC Chat

Ce projet est un **Proof of Concept (POC)** d’un système de chat pour l’application "Your Car Your Way". Il permet de tester l’intégration d’un chat temps réel entre utilisateurs et conseillers, dans le cadre d’une application de location et gestion automobile.

## Structure du projet

- **Backend** : Spring Boot (Java 21), REST & WebSocket, JPA/Hibernate, Lombok
- **Frontend** : Angular 20, TypeScript
- **Base de données** : MySQL 8
- **Scripts d’initialisation** : Les fichiers SQL sont désormais dans le dossier `resources/` :
	- `resources/init-schema.sql` : création du schéma
	- `resources/init-user_customer.sql` : insertion des données de base

## Démarrage rapide

### 1. Lancer le backend

Dans le dossier `chat/` :

```bash
mvn spring-boot:run
```

### 2. Lancer le frontend

Dans le dossier `chat-client/` :

```bash
ng serve
```
Accéder à l’application sur [http://localhost:4200/](http://localhost:4200/)

### 3. Initialiser la base de données

Dans le dossier racine :

```bash
mysql -u <user> -p <database> < resources/init-schema.sql
mysql -u <user> -p <database> < resources/init-user_customer.sql
```
Remplacez `<user>` et `<database>` par vos identifiants MySQL.



## Ressources complémentaires

- [Angular CLI Documentation](https://angular.dev/tools/cli)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

---

**Contact** : Projet réalisé dans le cadre d’OpenClassrooms.
