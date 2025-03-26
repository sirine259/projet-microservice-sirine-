# Projets Microservices

Ce repository regroupe trois projets liés à une architecture basée sur des microservices. Il contient une implémentation d'un registre de services avec Eureka et Docker, une gestion d'authentification avec Keycloak, ainsi qu'une documentation détaillée de l'architecture.

## Sommaire
1. [Documentation / Architecture](#documentation--architecture)
2. [Eureka + Docker](#eureka--docker)
3. [Keycloak](#keycloak)
4. [Prérequis](#prérequis)
5. [Instructions d'installation](#instructions-dinstallation)

---
## Documentation / Architecture

### Description
Ce dossier contient la documentation complète de l'architecture microservices, incluant des schémas, des explications et des décisions techniques.

### Contenu
- Schéma global de l'architecture.
- Détails sur les interactions entre Eureka et Keycloak.
- Notes sur les choix techniques.

### Accès
- Ouvrez le dossier `documentation-architecture/` pour consulter les fichiers.
------

## Eureka + Docker

### Description
Ce projet configure un registre de services avec Netflix Eureka, conteneurisé à l'aide de Docker. Il permet la découverte et la gestion des microservices dans une architecture distribuée.

### Fonctionnalités
- Serveur Eureka pour l'enregistrement des microservices.
- Conteneur Docker pour un déploiement simplifié.

### Instructions
1. Assurez-vous que Docker est installé.
2. Naviguez dans le dossier `eureka-docker/`.
3. Lancez le conteneur avec : `docker-compose up`.

---

## Keycloak

### Description
Ce projet met en place Keycloak, une solution open-source pour la gestion des identités et des accès (IAM). Il sécurise les microservices avec une authentification centralisée.

### Fonctionnalités
- Authentification via OAuth2/OpenID Connect.
- Gestion des utilisateurs et des rôles.

### Instructions
1. Accédez au dossier `keycloak/`.
2. Démarrez Keycloak avec : `docker run -p 8080:8080 jboss/keycloak`.
3. Connectez-vous à `http://localhost:8080` pour configurer.

---

## Documentation / Architecture

### Description
Ce dossier contient la documentation complète de l'architecture microservices, incluant des schémas, des explications et des décisions techniques.

### Contenu
- Schéma global de l'architecture.
- Détails sur les interactions entre Eureka et Keycloak.
- Notes sur les choix techniques.

### Accès
- Ouvrez le dossier `documentation-architecture/` pour consulter les fichiers.

---

## Prérequis
- [Docker](https://www.docker.com/get-started) (version 20.10 ou supérieure)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Java 11+ (pour Eureka, si utilisé hors Docker)
- Une connexion internet pour télécharger les images Docker

---

## Instructions d'installation
1. Clonez ce repository :
   ```bash
   git clone https://github.com/votre-utilisateur/projets-microservices.git
