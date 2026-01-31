````
#  WebMotoSystem - Plateforme de Vente de Véhicules

Application web de vente en ligne de véhicules développée avec **Spring Boot** (backend) et **React + TypeScript** (frontend), illustrant l'implémentation de **11 design patterns** (GoF).

##  Contexte du Projet

WebMotoSystem est une plateforme e-commerce complète permettant :
- La consultation d'un catalogue de véhicules (automobiles/scooters, essence/électrique)
- La gestion d'un panier avec options personnalisables
- Le passage de commandes avec calcul automatique des taxes
- La génération de documents officiels (PDF/HTML)

**Projet académique** - Cours INF4067 - UML et Design Patterns (2025-2026)

## Technologies Utilisées

### Backend
- Java 17
- Spring Boot 3.2+
- MySQL 8.0
- JWT (authentification)
- Apache PDFBox (génération PDF)
- Maven

### Frontend
- React 18
- TypeScript
- Vite
- Tailwind CSS
- Shadcn/ui

## Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- **Java JDK 17+** → [Télécharger](https://www.oracle.com/java/technologies/downloads/)
- **Node.js 18+** et npm → [Télécharger](https://nodejs.org/)
- **MySQL 8.0+** ou **XAMPP/LAMPP** → [Télécharger](https://www.apachefriends.org/)
- **Maven** (généralement inclus avec les IDE Java)
- **Git** → [Télécharger](https://git-scm.com/)

## Installation et Démarrage

###  Cloner le Projet
```bash
git clone https://github.com/votre-username/webmotosystem.git
cd webmotosystem
```

###  Configurer la Base de Données

#### Démarrer MySQL

**Avec XAMPP (Linux)** :
```bash
sudo /opt/lampp/lampp start
```

**Avec XAMPP (Windows)** :
- Ouvrir le panneau de contrôle XAMPP
- Démarrer Apache et MySQL

**Avec MySQL autonome** :
```bash
sudo systemctl start mysql      # Linux
brew services start mysql       # macOS
# Utiliser les services Windows  # Windows
```

#### Importer la Base de Données

1. **Accéder à phpMyAdmin** : `http://localhost/phpmyadmin`
2. **Créer une nouvelle base de données** nommée `ecommerce`
3. **Importer le fichier SQL** :
   - Cliquez sur la base `ecommerce`
   - Allez dans l'onglet **"Importer"**
   - Sélectionnez le fichier `ecommerce.sql` (situé à la **racine du projet**)
   - Cliquez sur **"Exécuter"**

**Ou via la ligne de commande** :
```bash
mysql -u root -p ecommerce < ecommerce.sql
```

>  **Important** : Le fichier `ecommerce.sql` contient toutes les tables et données nécessaires. Sans cette importation, l'application ne fonctionnera pas correctement.

###  Configuration du Backend

#### Vérifier la Configuration

Ouvrez le fichier `backend/src/main/resources/application.properties` et vérifiez les paramètres :
```properties
# Port du serveur
server.port=8084

# Base de données (vérifiez le nom de la BD : ecommerce)
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=

# Context path
server.servlet.context-path=/api
```

>  **Note** : Si votre utilisateur MySQL n'est pas `root` ou si vous avez un mot de passe, modifiez les lignes `username` et `password` en conséquence.

#### Créer le Dossier Uploads
```bash
mkdir backend/uploads
```

#### Lancer le Backend
```bash
cd backend

# Compiler le projet
mvn clean install

# Démarrer l'application
mvn spring-boot:run
```

 **Le backend est prêt** quand vous voyez :
```
Started WebmotosystemApplication in X.XXX seconds
```

**Accès** : `http://localhost:8084/api`

###  Configuration du Frontend

Ouvrez un **nouveau terminal** (laissez le backend tourner) :
```bash
cd frontend

# Installer les dépendances
npm install

# Lancer le serveur de développement
npm run dev
```

 **Le frontend est prêt** quand vous voyez :
```
➜  Local:   http://localhost:5173/
```

**Accès** : `http://localhost:5173`

##  Utilisation de l'Application

### 1. Inscription

1. Accédez à `http://localhost:5173`
2. Cliquez sur **"Inscription"**
3. Remplissez le formulaire
4. **Récupérez le code de validation** dans la **console du backend** (6 chiffres)
5. Entrez le code pour activer votre compte

### 2. Connexion

Connectez-vous avec vos identifiants :
- Email : votre-email@example.com
- Mot de passe : votre-mot-de-passe

### 3. Explorer le Catalogue

- Parcourez les véhicules disponibles
- Utilisez les filtres (prix, marque, modèle, année)
- Recherche avancée : `marque:Ferrari AND annee:2024`

### 4. Ajouter au Panier

- Cliquez sur un véhicule
- Ajoutez-le au panier
- Sélectionnez des options (sièges cuir, toit ouvrant, etc.)
- Le prix se recalcule automatiquement

### 5. Passer une Commande

- Accédez à votre panier
- Cliquez sur **"Commander"**
- Choisissez le type de paiement (Comptant/Crédit)
- Sélectionnez le pays de livraison
- Validez la commande

### 6. Générer les Documents

- Allez dans **"Mes Commandes"**
- Cliquez sur une commande
- Accédez à **"Documents"**
- Téléchargez :
  - Demande d'immatriculation
  - Certificat de cession
  - Bon de commande (avec détail des options)
- Format : HTML (aperçu) ou PDF (téléchargement)

##  Structure du Projet
```
webmotosystem/
├── ecommerce.sql              #  À importer dans MySQL
├── backend/                   # Application Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/          # Code source Java
│   │   │   └── resources/     # Configuration
│   │   └── test/
│   ├── uploads/               # Dossier pour les images
│   └── pom.xml               # Dépendances Maven
│
└── frontend/                  # Application React
    ├── src/
    │   ├── components/        # Composants réutilisables
    │   ├── pages/            # Pages de l'application
    │   └── lib/              # Utilitaires
    ├── package.json          # Dépendances npm
    └── vite.config.ts        # Configuration Vite
```

##  Dépannage

### Problème 1 : Erreur de connexion MySQL

**Symptôme** : `Access denied for user 'root'@'localhost'`

**Solution** :
```bash
# Se connecter à MySQL
mysql -u root -p

# Dans MySQL
ALTER USER 'root'@'localhost' IDENTIFIED BY 'votre_mot_de_passe';
FLUSH PRIVILEGES;
EXIT;

# Puis modifier application.properties
spring.datasource.password=votre_mot_de_passe
```

### Problème 2 : Port 8084 déjà utilisé

**Solution** :
```bash
# Trouver le processus
sudo lsof -i :8084

# Tuer le processus
sudo kill -9 

# Ou changer le port dans application.properties
server.port=8085
```

### Problème 3 : La base `ecommerce` n'existe pas

**Solution** :
```bash
# Créer la base manuellement
mysql -u root -p
CREATE DATABASE ecommerce CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT;

# Puis importer le fichier SQL
mysql -u root -p ecommerce < ecommerce.sql
```

### Problème 4 : Erreur npm install

**Solution** :
```bash
# Nettoyer le cache npm
npm cache clean --force

# Supprimer node_modules
rm -rf node_modules package-lock.json

# Réinstaller
npm install
```

### Problème 5 : Dossier uploads introuvable

**Solution** :
```bash
# Créer le dossier
mkdir -p backend/uploads
chmod 755 backend/uploads
```

##  Comptes de Test

Si la base `ecommerce.sql` contient des données de test :

**Administrateur** :
- Email : admin@webmoto.com
- Mot de passe : admin123

**Client** :
- Email : client@test.com
- Mot de passe : client123

##  Fonctionnalités Principales

-  Authentification JWT sécurisée
-  Gestion complète des véhicules (CRUD)
-  Panier intelligent avec options incompatibles
-  Undo/Redo sur le panier (Pattern Memento)
-  Calcul automatique des taxes par pays (Pattern Strategy)
-  Génération de documents PDF/HTML (Patterns Adapter & Builder)
-  Gestion des sociétés et filiales (Pattern Composite)
-  Véhicules soldés automatiques
-  Recherche avancée avec opérateurs logiques

##  Design Patterns Implémentés

1. **Abstract Factory** - Création de véhicules
2. **Builder** - Construction de documents
3. **Factory Method** - Création de commandes
4. **Singleton** - Liasse de documents
5. **Adapter** - Documents PDF
6. **Bridge** - Formulaires HTML/PDF
7. **Composite** - Sociétés/Filiales
8. **Command** - Opérations du panier
9. **Memento** - Undo/Redo
10. **Strategy** - Calcul des taxes
11. **Template Method** - Calcul des commandes


## Remerciements

Projet réalisé dans le cadre du cours **INF4067 - UML et Design Patterns** (2025-2026) à l'Université de Yaoundé I.

---

** N'oubliez pas d'importer le fichier `ecommerce.sql` avant de lancer l'application !**

Checklist de Démarrage Rapide  
Cochez au fur et à mesure :

## Checklist de Démarrage

- [ ] Java 17+ installé
- [ ] Node.js 18+ installé
- [ ] MySQL démarré
- [ ] Base de données `ecommerce` créée
- [ ] Fichier `ecommerce.sql` importé  **CRITIQUE**
- [ ] Dossier `backend/uploads` créé
- [ ] Backend démarré (port 8084)
- [ ] Frontend démarré (port 5173)
- [ ] Compte créé et activé
- [ ] Premier véhicule ajouté au panier
- [ ] Première commande passée
- [ ] Documents générés

Si tous les points sont cochés, l'application fonctionne
````
