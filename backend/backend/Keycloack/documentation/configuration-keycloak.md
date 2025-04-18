## Configuration de Keycloak 
Contenu : Guide pour configurer Keycloak.
Création d’un realm (exemple : "my-realm").
Configuration d’un client (exemple : "springboot-app") avec protocole OpenID Connect.
Ajout de rôles (exemple : "USER", "ADMIN").
Création d’utilisateurs de test avec mots de passe.
Exemple de paramètres à ajouter dans application.properties ou application.yml de Spring Boot :

spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/my-realm