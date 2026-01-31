Voici un README complet et professionnel pour votre projet :

```markdown
# WebMotoSystem - Plateforme de Vente en Ligne de Véhicules

Une application web moderne de vente en ligne de véhicules développée dans le cadre du cours **INF4067 - UML et Design Patterns** à l'Université de Yaoundé I. Le projet illustre l'implémentation de **11 design patterns** dans un contexte réel d'e-commerce automobile.

## Table des matières

- [Aperçu](#-aperçu)
- [Fonctionnalités](#-fonctionnalités)
- [Design Patterns](#-design-patterns)
- [Technologies](#-technologies)
- [Architecture](#-architecture)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Utilisation](#-utilisation)
- [API Documentation](#-api-documentation)
- [Captures d'écran](#-captures-décran)
- [Contributeurs](#-contributeurs)
- [Licence](#-licence)

## Aperçu

WebMotoSystem est une plateforme complète permettant :
- La consultation d'un **catalogue de véhicules** (automobiles et scooters, essence et électrique)
- La gestion d'un **panier intelligent** avec options personnalisables
- Le **passage de commandes** avec calcul automatique des taxes selon le pays
- La génération de **documents officiels** (PDF/HTML) pour chaque commande
- La gestion des **clients entreprises** avec leurs filiales

## Fonctionnalités

### Authentification & Autorisation
- Inscription avec validation par email (code à 6 chiffres)
- Connexion sécurisée avec JWT
- Gestion des rôles : Administrateur, Utilisateur, Société, Client
- Renvoi de code d'activation

### Gestion des Véhicules
- CRUD complet sur les véhicules
- Types : Automobile (Essence/Électrique), Scooter (Essence/Électrique)
- Recherche avancée avec filtres (marque, modèle, année, prix, couleur)
- Upload d'images multiples par véhicule
- Système de **soldes automatiques** (véhicules en stock > 3 mois)
- Gestion du stock en temps réel

### 🛒 Panier Intelligent
- Ajout/suppression de véhicules
- Sélection d'options avec gestion des **incompatibilités**
- Calcul automatique du prix total (véhicule + options)
- **Undo/Redo** complet (pattern Memento)
- Sauvegarde persistante par utilisateur

### Commandes
- Création de commande depuis le panier
- Types de paiement : **Comptant** (0% frais) ou **Crédit** (10% frais)
- Calcul automatique des **taxes par pays** (France: 20%, Cameroun: 15%)
- États : EN_COURS → VALIDEE → LIVREE
- Conservation des options sélectionnées

### Documents Officiels
Génération automatique de documents professionnels :
- **Demande d'immatriculation**
- **Certificat de cession**
- **Bon de commande** (avec détail des options et récapitulatif financier)

Formats disponibles :
- **HTML** : Aperçu stylisé en temps réel
- **PDF** : Téléchargement pour impression

### Gestion Clients Entreprises
- Structure hiérarchique : Société → Filiales
- Calcul automatique de la flotte totale
- Gestion des commandes groupées

## Design Patterns

Ce projet implémente **11 design patterns** du Gang of Four :

### Patterns de Création
1. **Abstract Factory** (`FabriqueVehicule`)
   - Création de familles de véhicules (Essence/Électrique)
   - Garantit la cohérence des types créés

2. **Builder** (`LiasseBuilder`, `LiasseDirector`)
   - Construction de liasses de documents (HTML/PDF)
   - Séparation de la construction et de la représentation

3. **Factory Method** (`CommandeFactoryService`)
   - Création de commandes (Comptant/Crédit)
   - Délégation de l'instanciation aux sous-classes

4. **Singleton** (`LiasseDocuments`)
   - Instance unique de la liasse de documents
   - Accès global contrôlé

### Patterns Structuraux
5. **Adapter** (`PdfAdapter`)
   - Adaptation de la bibliothèque PDFBox
   - Interface commune avec HtmlDocument

6. **Bridge** (`Formulaire`, `FormsRenderer`)
   - Séparation abstraction (Formulaire) / implémentation (HTML/PDF)
   - Évolution indépendante des deux hiérarchies

7. **Composite** (`ClientEntreprise`, `Societe`, `Filiale`)
   - Structure arborescente des entreprises
   - Traitement uniforme des sociétés et filiales

### Patterns Comportementaux
8. **Command** (`PanierCommand`, `AjouterVehiculeCommand`, etc.)
   - Encapsulation des opérations sur le panier
   - Support de l'annulation (undo/redo)

9. **Memento** (`PanierMemento`, `PanierCaretaker`)
   - Sauvegarde/restauration de l'état du panier
   - Historique des modifications

10. **Strategy** (`TaxeStrategy`, `TaxeFrance`, `TaxeCameroun`)
    - Algorithmes interchangeables de calcul de taxes
    - Ajout facile de nouveaux pays

11. **Template Method** (`Commande.calculerTotal()`)
    - Squelette de l'algorithme de calcul
    - Points d'extension pour les frais spécifiques

## Technologies

### Backend
- **Java 17** - Langage principal
- **Spring Boot 3.2+** - Framework web
- **Spring Security** - Authentification/Autorisation
- **Spring Data JPA** - Persistence des données
- **Hibernate** - ORM
- **MySQL 8.0** - Base de données
- **JWT (jjwt 0.12.3)** - Gestion des tokens
- **Apache PDFBox 2.0.27** - Génération de PDF
- **Lombok** - Réduction du boilerplate
- **Maven** - Gestion des dépendances

### Frontend
- **React 18** - Bibliothèque UI
- **TypeScript** - Typage statique
- **Vite** - Build tool
- **Tailwind CSS** - Framework CSS
- **Shadcn/ui** - Composants UI
- **Lucide React** - Icônes
- **React Router** - Routing

### DevOps
- **Git** - Contrôle de version
- **GitHub** - Hébergement du code
- **XAMPP/LAMPP** - Serveur local (développement)

## Architecture

### Architecture Logique (3-Tiers)

```
┌─────────────────────────────────────────┐
│         Couche Présentation             │
│   (React + TypeScript + Tailwind)       │
│  - Pages (Catalogue, Panier, etc.)      │
│  - Composants réutilisables             │
└──────────────┬──────────────────────────┘
               │ HTTP/REST (JSON)
               │ JWT Authentication
┌──────────────▼──────────────────────────┐
│          Couche Métier                   │
│      (Spring Boot + Services)            │
│  - Controllers (API REST)                │
│  - Services (Logique métier)             │
│  - Security (JWT + BCrypt)               │
│  - Factory/Strategy/Command              │
└──────────────┬──────────────────────────┘
               │ JPA/Hibernate
               │
┌──────────────▼──────────────────────────┐
│       Couche Persistence                 │
│    (Spring Data JPA + MySQL)             │
│  - Repositories                          │
│  - Entities (JPA)                        │
│  - Base de données relationnelle         │
└──────────────────────────────────────────┘
```

### Architecture Technique

```
webmotosystem/
├── backend/                          # Application Spring Boot
│   ├── src/main/java/
│   │   └── com.designpattern.webmotosystem/
│   │       ├── Controller/           # REST Controllers
│   │       │   ├── VehiculeController.java
│   │       │   ├── CommandeController.java
│   │       │   ├── PanierController.java
│   │       │   ├── DocumentController.java
│   │       │   └── UtilisateurController.java
│   │       ├── Services/             # Logique métier
│   │       │   ├── VehiculeService.java
│   │       │   ├── CommandeService.java
│   │       │   ├── PanierService.java
│   │       │   ├── DocumentService.java
│   │       │   ├── UtilisateurService.java
│   │       │   ├── ValidationService.java
│   │       │   └── taxe/
│   │       │       ├── TaxeStrategy.java
│   │       │       ├── TaxeFrance.java
│   │       │       ├── TaxeCameroun.java
│   │       │       └── TaxeStrategyFactory.java
│   │       ├── Entities/             # Entités JPA
│   │       │   ├── Vehicule/
│   │       │   │   ├── Vehicule.java (abstract)
│   │       │   │   ├── Automobile.java (abstract)
│   │       │   │   ├── Scooter.java (abstract)
│   │       │   │   ├── AutomobileEssence.java
│   │       │   │   ├── AutomobileElectrique.java
│   │       │   │   ├── ScooterEssence.java
│   │       │   │   └── ScooterElectrique.java
│   │       │   ├── Commande/
│   │       │   │   ├── Commande.java (abstract)
│   │       │   │   ├── CommandeComptant.java
│   │       │   │   ├── CommandeCredit.java
│   │       │   │   ├── OptionCommande.java
│   │       │   │   ├── EtatCommande.java (enum)
│   │       │   │   └── EnumCommande.java (enum)
│   │       │   ├── client/
│   │       │   │   ├── ClientEntreprise.java (interface)
│   │       │   │   ├── Societe.java
│   │       │   │   └── Filiale.java
│   │       │   ├── panier/
│   │       │   │   ├── Panier.java
│   │       │   │   ├── ArticlePanier.java
│   │       │   │   ├── OptionChoisie.java
│   │       │   │   ├── OptionProduit.java
│   │       │   │   ├── command/
│   │       │   │   │   ├── PanierCommand.java (interface)
│   │       │   │   │   ├── AjouterVehiculeCommand.java
│   │       │   │   │   ├── AjouterOptionCommand.java
│   │       │   │   │   ├── RetirerArticleCommand.java
│   │       │   │   │   └── RetirerOptionCommand.java
│   │       │   │   └── memento/
│   │       │   │       ├── PanierMemento.java
│   │       │   │       └── PanierCaretaker.java
│   │       │   ├── documents/
│   │       │   │   ├── adapter/
│   │       │   │   │   ├── Document.java (interface)
│   │       │   │   │   ├── HtmlDocument.java
│   │       │   │   │   ├── PdfAdapter.java
│   │       │   │   │   └── PdfLibrary.java
│   │       │   │   ├── bridge/
│   │       │   │   │   ├── FormsRenderer.java (interface)
│   │       │   │   │   ├── Formulaire.java (abstract)
│   │       │   │   │   ├── HTMLRenderer.java
│   │       │   │   │   ├── PDFRenderer.java
│   │       │   │   │   ├── FormulaireClient.java
│   │       │   │   │   ├── FormulaireCommande.java
│   │       │   │   │   └── FormulaireFacture.java
│   │       │   │   ├── builder/
│   │       │   │   │   ├── LiasseBuilder.java (interface)
│   │       │   │   │   ├── LiasseDirector.java
│   │       │   │   │   ├── LiasseHTMLBuilder.java
│   │       │   │   │   └── LiassePDFBuilder.java
│   │       │   │   └── singleton/
│   │       │   │       └── LiasseDocuments.java
│   │       │   ├── Utilisateur.java
│   │       │   ├── Validation.java
│   │       │   ├── Adresse.java
│   │       │   └── Role.java (enum)
│   │       ├── Repositories/         # Accès données
│   │       │   ├── VehiculeRepository.java
│   │       │   ├── CommandeRepository.java
│   │       │   ├── PanierRepository.java
│   │       │   ├── ArticlePanierRepository.java
│   │       │   ├── OptionProduitRepository.java
│   │       │   ├── OptionChoisieRepository.java
│   │       │   ├── OptionCommandeRepository.java
│   │       │   ├── UtilisateurRepository.java
│   │       │   └── ValidationRepository.java
│   │       ├── Security/             # Configuration sécurité
│   │       │   ├── ConfigSecurityApp.java
│   │       │   ├── JwtService.java
│   │       │   ├── JwtAuthenticationFilter.java
│   │       │   ├── CustomUserDetailsService.java
│   │       │   └── WebConfig.java
│   │       ├── factory/              # Factory patterns
│   │       │   ├── FabriqueVehicule.java (abstract)
│   │       │   ├── FabriqueVehiculeEssence.java
│   │       │   ├── FabriqueVehiculeElectrique.java
│   │       │   └── CommandeFactoryService.java
│   │       ├── DTO/                  # Data Transfer Objects
│   │       │   ├── LoginRequest.java
│   │       │   ├── LoginResponse.java
│   │       │   ├── CommandeResponse.java
│   │       │   ├── VehiculeCommandeResponse.java
│   │       │   └── panier/
│   │       │       └── PanierResponse.java
│   │       ├── Mappers/              # Entity ↔ DTO
│   │       │   ├── CommandeMapper.java
│   │       │   └── PanierMapper.java
│   │       ├── fileManager/          # Gestion fichiers
│   │       │   └── FileFilter.java
│   │       ├── utils/
│   │       │   └── FileUrlBuilder.java
│   │       └── exception/
│   │           └── UnsupportedFileTypeException.java
│   ├── src/main/resources/
│   │   ├── application.properties    # Configuration Spring
│   │   └── static/uploads/           # Stockage images
│   └── pom.xml                       # Dépendances Maven
│
└── frontend/                         # Application React
    ├── src/
    │   ├── components/               # Composants réutilisables
    │   │   ├── layout/
    │   │   │   └── Layout.tsx
    │   │   └── ui/                   # Shadcn components
    │   ├── pages/                    # Pages de l'application
    │   │   ├── Catalogue.tsx
    │   │   ├── Panier.tsx
    │   │   ├── Commandes.tsx
    │   │   ├── Documents.tsx
    │   │   ├── Connexion.tsx
    │   │   └── Inscription.tsx
    │   ├── lib/
    │   │   └── utils.ts
    │   ├── App.tsx
    │   └── main.tsx
    ├── package.json
    ├── tsconfig.json
    ├── vite.config.ts
    └── tailwind.config.js
```

## Installation

### Prérequis

- **Java JDK 17+** ([Télécharger](https://www.oracle.com/java/technologies/downloads/))
- **Node.js 18+** et npm ([Télécharger](https://nodejs.org/))
- **MySQL 8.0+** ou **XAMPP/LAMPP** ([Télécharger](https://www.apachefriends.org/))
- **Maven 3.6+** (inclus avec la plupart des IDE Java)
- **Git** ([Télécharger](https://git-scm.com/))

### 1️Cloner le projet

```bash
git clone https://github.com/votre-username/webmotosystem.git
cd webmotosystem
```

### Configuration de la base de données

#### Option A : Avec XAMPP/LAMPP (Recommandé pour le développement)

```bash
# Démarrer XAMPP (Linux)
sudo /opt/lampp/lampp start

# Démarrer XAMPP (Windows)
# Ouvrir le panneau de contrôle XAMPP et démarrer Apache + MySQL

# Démarrer XAMPP (macOS)
sudo /Applications/XAMPP/xamppfiles/mampp start
```

#### Option B : MySQL autonome

```bash
# Démarrer MySQL
sudo systemctl start mysql      # Linux
brew services start mysql       # macOS
# Utiliser les services Windows  # Windows
```

#### Créer la base de données

```bash
# Se connecter à MySQL
mysql -u root -p

# Créer la base de données
CREATE DATABASE webmotosystem CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT;
```

### Configuration du Backend

#### Créer le fichier de configuration

Créez le fichier `backend/src/main/resources/application.properties` :

```properties
# Configuration du serveur
server.port=8084

# Configuration de la base de données
spring.datasource.url=jdbc:mysql://localhost:3306/webmotosystem?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuration JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Configuration JWT
jwt.secret=votreClefSecreteTresLongueEtSecuriseeIci123456789

# Configuration du stockage de fichiers
file.upload-dir=./uploads
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Configuration CORS
spring.web.cors.allowed-origins=http://localhost:5173
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*
spring.web.cors.allow-credentials=true
```

#### Compiler et lancer le backend

```bash
cd backend

# Compiler le projet
mvn clean install

# Lancer l'application
mvn spring-boot:run

# Ou avec Java directement
java -jar target/webmotosystem-0.0.1-SNAPSHOT.jar
```

Le backend sera accessible sur `http://localhost:8084`

### Configuration du Frontend

```bash
cd frontend

# Installer les dépendances
npm install

# Lancer le serveur de développement
npm run dev

# Build pour la production (optionnel)
npm run build
```

Le frontend sera accessible sur `http://localhost:5173`

## Configuration

### Variables d'environnement Backend

Créez un fichier `.env` (optionnel, pour la production) :

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=webmotosystem
DB_USER=root
DB_PASSWORD=
JWT_SECRET=votreClefSecreteTresLongueEtSecuriseeIci123456789
UPLOAD_DIR=./uploads
```

### Variables d'environnement Frontend

Créez un fichier `frontend/.env` :

```env
VITE_API_URL=http://localhost:8084/api
```

### Configuration de l'email (optionnel)

Pour l'envoi d'emails de validation, ajoutez dans `application.properties` :

```properties
# Configuration email (exemple avec Gmail)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=votre-email@gmail.com
spring.mail.password=votre-mot-de-passe-application
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Utilisation

### 1. Inscription et Connexion

1. Accédez à `http://localhost:5173`
2. Cliquez sur **"Inscription"**
3. Remplissez le formulaire :
   - Nom complet
   - Email
   - Mot de passe
   - Adresse (ville, pays, téléphone)
4. Un code de validation à 6 chiffres sera affiché dans la console backend
5. Entrez le code pour activer votre compte
6. Connectez-vous avec vos identifiants

### 2. Parcourir le catalogue

1. Naviguez dans le **catalogue de véhicules**
2. Utilisez les **filtres** :
   - Par prix (croissant/décroissant)
   - Par marque
   - Par modèle
   - Par année
   - Véhicules soldés uniquement
3. Recherche avancée avec opérateurs logiques :
   ```
   marque:Toyota AND modele:Corolla
   marque:Ferrari OR marque:Lamborghini
   annee:2024 AND marque:Tesla
   ```

### 3. Ajouter au panier

1. Cliquez sur un véhicule pour voir les détails
2. Cliquez sur **"Ajouter au panier"**
3. Sélectionnez des **options** (sièges cuir, toit ouvrant, etc.)
4. Le prix se recalcule automatiquement
5. Utilisez **Undo/Redo** pour annuler/rétablir des modifications

### 4. Passer une commande

1. Accédez à votre **panier**
2. Vérifiez les articles et options
3. Cliquez sur **"Commander"**
4. Choisissez :
   - Type de paiement : **Comptant** ou **Crédit**
   - Pays de livraison (calcul automatique des taxes)
5. Validez la commande

### 5. Générer les documents

1. Allez dans **"Mes commandes"**
2. Cliquez sur une commande
3. Accédez à **"Documents"**
4. Sélectionnez un document :
   - Demande d'immatriculation
   - Certificat de cession
   - Bon de commande
5. Choisissez le format :
   - **HTML** : Aperçu stylisé
   - **PDF** : Téléchargement

## API Documentation

### Authentification

#### Inscription
```http
POST /inscription
Content-Type: application/json

{
  "nom": "Jean Dupont",
  "email": "jean@example.com",
  "password": "motdepasse123",
  "adresse": {
    "pays": "France",
    "ville": "Paris",
    "telephone": "+33123456789"
  },
  "role": "CLIENT"
}
```

#### Activation du compte
```http
POST /activation
Content-Type: application/json

{
  "code": "123456"
}
```

#### Connexion
```http
POST /login
Content-Type: application/json

{
  "email": "jean@example.com",
  "password": "motdepasse123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "expiresIn": 86400
}
```

### Véhicules

#### Lister tous les véhicules
```http
GET /vehicules
Authorization: Bearer {token}
```

#### Créer un véhicule (Automobile Électrique)
```http
POST /vehicules/automobile/electrique
Content-Type: multipart/form-data
Authorization: Bearer {token}

reference: REF-001
modele: Model S
marque: Tesla
annee: 2024
couleur: Noir
qteStock: 5
prixBase: 89990
dateArrivee: 2024-01-15
estSolde: false
kilometrage: 0
status: NEUF
batterieKwh: 100
images: [file1.jpg, file2.jpg]
```

#### Rechercher des véhicules
```http
GET /vehicules/search/keywords?query=marque:Tesla AND annee:2024
Authorization: Bearer {token}
```

### Panier

#### Obtenir le panier
```http
GET /api/panier/{utilisateurId}
Authorization: Bearer {token}
```

#### Ajouter un véhicule
```http
POST /api/panier/{utilisateurId}/articles/{vehiculeId}
Authorization: Bearer {token}
```

#### Ajouter une option
```http
POST /api/panier/{utilisateurId}/articles/{articleId}/options/{optionCode}
Authorization: Bearer {token}
```

#### Undo/Redo
```http
POST /api/panier/{utilisateurId}/undo
POST /api/panier/{utilisateurId}/redo
Authorization: Bearer {token}
```

### Commandes

#### Créer une commande depuis le panier
```http
POST /commandes/from-panier?typeCommande=COMPTANT&clientId=1&vendeurId=2&cartItemId=5&paysLivraison=France
Authorization: Bearer {token}
```

#### Obtenir les commandes d'un client
```http
GET /commandes/client/{clientId}
Authorization: Bearer {token}
```

### Documents

#### Télécharger un document HTML
```http
GET /documents/html/commande/download?orderId=1
Authorization: Bearer {token}
```

#### Télécharger un document PDF
```http
GET /documents/pdf/commande/download?orderId=1
Authorization: Bearer {token}
```

## Captures d'écran

### Page d'accueil
![Catalogue de véhicules](screenshots/catalogue.png)

### Panier
![Panier avec options](screenshots/panier.png)

### Documents
![Génération de documents](screenshots/documents.png)

### Commandes
![Liste des commandes](screenshots/commandes.png)

## Tests

### Exécuter les tests backend

```bash
cd backend
mvn test
```

### Exécuter les tests frontend

```bash
cd frontend
npm test
```

## Déploiement

### Backend (Spring Boot)

```bash
cd backend
mvn clean package
java -jar target/webmotosystem-0.0.1-SNAPSHOT.jar
```

### Frontend (React)

```bash
cd frontend
npm run build
# Les fichiers de production seront dans le dossier dist/
```

### Docker (optionnel)

```bash
# À la racine du projet
docker-compose up -d
```

## Dépannage

### Problème de connexion à MySQL

```bash
# Vérifier que MySQL est démarré
sudo systemctl status mysql

# Réinitialiser le mot de passe root
sudo mysql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'nouveau_mot_de_passe';
FLUSH PRIVILEGES;
```

### Erreur de port déjà utilisé

```bash
# Backend (port 8084)
sudo lsof -i :8084
sudo kill -9 <PID>

# Frontend (port 5173)
sudo lsof -i :5173
sudo kill -9 <PID>
```

### Problème d'upload de fichiers

```bash
# Créer le dossier uploads
mkdir -p backend/uploads
chmod 755 backend/uploads
```

## Contributeurs

- **Heil Tchamba Nana** - *Développeur principal* - [@votre-github](https://github.com/votre-username)

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Contexte Académique

Projet réalisé dans le cadre du cours **INF4067 - UML et Design Patterns** (2025-2026) à l'Université de Yaoundé I, sous la direction de [Nom du Professeur].

### Objectifs pédagogiques
- Maîtriser l'application des design patterns du Gang of Four
- Concevoir une architecture logicielle robuste et maintenable
- Implémenter un système complet avec authentification, persistance et génération de documents
- Travailler avec des technologies modernes (Spring Boot, React, JWT)

## Remerciements

- **Gang of Four** pour les design patterns fondamentaux
- **Spring Framework** pour l'excellent framework Java
- **React Team** pour la bibliothè
