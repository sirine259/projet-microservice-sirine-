# Projet Eureka avec Docker

## Description
Ce projet implémente un serveur **Eureka**, une solution de découverte de services fournie par Netflix et intégrée dans l'écosystème Spring Cloud. Il permet aux microservices de s'enregistrer dynamiquement et de découvrir d'autres services dans une architecture distribuée. Ce projet est conteneurisé avec **Docker** pour faciliter le déploiement et la gestion.

## Prérequis
- Java 19 
- Maven (pour construire le projet)
- Docker (installé et en cours d'exécution)
- Docker Compose (optionnel, pour une exécution multi-conteneurs)

## Structure du Projet
- `src/` : Code source de l'application Eureka.
- `Dockerfile` : Fichier pour construire l'image Docker.
- `docker-compose.yml` : Fichier pour orchestrer le conteneur Eureka (optionnel).
- `pom.xml` : Fichier de configuration Maven.

## Installation et Configuration

### 1. Cloner le dépôt
```bash
git clone https://github.com/votre-utilisateur/votre-projet-eureka.git
cd votre-projet-eureka