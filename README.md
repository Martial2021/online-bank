# 💳 Online Bank – Microservices Java Spring Boot

Ce projet est une application bancaire simplifiée conçue en architecture microservices avec Spring Boot 3.4.4 et Java 21. Il utilise MongoDB, Spring Security + JWT, et suit une structure Maven multi-module.

## 📁 Structure du projet


## ⚙️ Technologies utilisées

- Java 21
- Spring Boot 3.4.4
- Spring Security + JWT
- Spring Data MongoDB
- Maven multi-module
- Docker + Docker Compose

## 🔐 Authentification JWT

- `POST /api/auth/register` – Créer un utilisateur
- `POST /api/auth/login` – Se connecter (retourne un JWT)
- `GET /api/auth/user` – Obtenir les infos du user courant (requiert un JWT)

## 💰 Gestion des comptes

Les opérations suivantes sont sécurisées (JWT requis dans l'en-tête `Authorization: Bearer <token>`).

- `POST /api/account/credit` – Créditer un compte
- `POST /api/account/debit` – Débiter un compte

## 🐳 Lancer avec Docker

### 1. Construction des modules Maven
```bash
mvn clean install -DskipTests

