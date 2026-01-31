-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 31, 2026 at 10:00 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `article_panier`
--

CREATE TABLE `article_panier` (
  `id` bigint(20) NOT NULL,
  `quantite` int(11) NOT NULL,
  `panier_id` bigint(20) NOT NULL,
  `vehicule_id` bigint(20) NOT NULL,
  `prix_final` double NOT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `article_panier`
--

INSERT INTO `article_panier` (`id`, `quantite`, `panier_id`, `vehicule_id`, `prix_final`, `image`) VALUES
(44, 1, 6, 1, 0, NULL),
(47, 1, 7, 1, 1850000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `automobile_electrique`
--

CREATE TABLE `automobile_electrique` (
  `nombre_portes` int(11) NOT NULL,
  `batterie_kwh` double NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `automobile_electrique`
--

INSERT INTO `automobile_electrique` (`nombre_portes`, `batterie_kwh`, `id`) VALUES
(0, 100, 3);

-- --------------------------------------------------------

--
-- Table structure for table `automobile_essence`
--

CREATE TABLE `automobile_essence` (
  `nombre_portes` int(11) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `automobile_essence`
--

INSERT INTO `automobile_essence` (`nombre_portes`, `id`) VALUES
(0, 1),
(0, 2),
(0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `type_commande` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `date_commande` datetime(6) DEFAULT NULL,
  `etat_commande` enum('EN_COURS','LIVREE','VALIDEE') DEFAULT NULL,
  `montant` double NOT NULL,
  `num_commande` varchar(255) DEFAULT NULL,
  `paye` bit(1) NOT NULL,
  `pays_livraison` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `vehicule_id` bigint(20) DEFAULT NULL,
  `vendeur_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`type_commande`, `id`, `date_commande`, `etat_commande`, `montant`, `num_commande`, `paye`, `pays_livraison`, `client_id`, `vehicule_id`, `vendeur_id`) VALUES
('COMPTANT', 5, '2026-01-20 15:34:51.000000', 'EN_COURS', 1610000, '87446950-77c7-46db-8853-6698d1e1d796', b'0', 'Cameroun', 1, 1, 1),
('CREDIT', 6, '2026-01-21 19:42:25.000000', 'EN_COURS', 2125000, '719e9cc7-d5bb-4301-ac54-a668e81168c2', b'0', 'Cameroun', 4, 1, 1),
('CREDIT', 7, '2026-01-31 05:22:46.000000', 'EN_COURS', 2312500, 'd217cfca-59dc-4672-8288-7e6ac5ea6b2d', b'0', 'Cameroun', 4, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `option_choisie`
--

CREATE TABLE `option_choisie` (
  `id` bigint(20) NOT NULL,
  `article_panier_id` bigint(20) NOT NULL,
  `option_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `option_choisie`
--

INSERT INTO `option_choisie` (`id`, `article_panier_id`, `option_id`) VALUES
(37, 47, 2),
(38, 47, 5),
(39, 47, 4);

-- --------------------------------------------------------

--
-- Table structure for table `option_commande`
--

CREATE TABLE `option_commande` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prix` double NOT NULL,
  `commande_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `option_commande`
--

INSERT INTO `option_commande` (`id`, `code`, `nom`, `prix`, `commande_id`) VALUES
(1, 'SIEGES_CUIR', 'Sièges cuir', 200000, 6),
(2, 'TOIT_OUVRANT', 'Toit ouvrant', 300000, 6),
(3, 'SIEGES_SPORT', 'Sièges sport', 250000, 7),
(4, 'CAMERA_RECUL', 'Caméra de recul', 100000, 7),
(5, 'TOIT_OUVRANT', 'Toit ouvrant', 300000, 7);

-- --------------------------------------------------------

--
-- Table structure for table `option_produit`
--

CREATE TABLE `option_produit` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prix` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `option_produit`
--

INSERT INTO `option_produit` (`id`, `code`, `nom`, `prix`) VALUES
(1, 'SIEGES_CUIR', 'Sièges cuir', 200000),
(2, 'SIEGES_SPORT', 'Sièges sport', 250000),
(3, 'GPS', 'GPS intégré', 150000),
(4, 'TOIT_OUVRANT', 'Toit ouvrant', 300000),
(5, 'CAMERA_RECUL', 'Caméra de recul', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `option_produit_incompatibles`
--

CREATE TABLE `option_produit_incompatibles` (
  `option_produit_id` bigint(20) NOT NULL,
  `incompatibles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

CREATE TABLE `panier` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `utilisateur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `panier`
--

INSERT INTO `panier` (`id`, `date_creation`, `utilisateur_id`) VALUES
(6, '2026-01-20 12:30:23.000000', 1),
(7, '2026-01-21 19:41:35.000000', 4),
(8, '2026-01-30 20:54:23.000000', 3);

-- --------------------------------------------------------

--
-- Table structure for table `scooter_electrique`
--

CREATE TABLE `scooter_electrique` (
  `batterie_kwh` double NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `scooter_electrique`
--

INSERT INTO `scooter_electrique` (`batterie_kwh`, `id`) VALUES
(100, 5);

-- --------------------------------------------------------

--
-- Table structure for table `scooter_essence`
--

CREATE TABLE `scooter_essence` (
  `injection_directe` bit(1) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `actif` bit(1) NOT NULL,
  `boite_postale` varchar(255) DEFAULT NULL,
  `pays` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `date_inscription` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `numero_registre_commerce` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `raison_sociale` varchar(255) DEFAULT NULL,
  `role` enum('ADMINISTRATEUR','CLIENT','SOCIETE','UTILISATEUR') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `actif`, `boite_postale`, `pays`, `telephone`, `ville`, `date_inscription`, `email`, `nom`, `numero_registre_commerce`, `password`, `raison_sociale`, `role`) VALUES
(1, b'1', '', 'Cameroun', '694907134', 'Yaounde', '2026-01-16 03:34:00.000000', 'kieran.foguem@facsciences-uy1.cm', 'Kieran', NULL, '$2a$10$VkGreTYuwD12JEzEXrm1zOg2GHKm.DZVArxotSosBKHNfxac7jMmC', NULL, 'CLIENT'),
(2, b'1', '', 'Cameroun', '677509134', 'Yaounde', '2026-01-16 03:36:50.000000', 'junior@mail.com', 'Junior', NULL, '$2a$10$4PSi5shAre8pFz3KS/fNqOnnoQaRRFvicfSugaozTs2.Uoppx.dPa', NULL, 'ADMINISTRATEUR'),
(3, b'1', '', 'Cameroun', '677908123', 'Yaounde', '2026-01-16 03:41:19.000000', 'pipil4811@gmail.com', 'Junior', NULL, '$2a$10$oEGxC6qUQEhXK33uNsQp4OBNwRef1LYgp9mPGvWjTBzsat7OAsUAa', NULL, 'ADMINISTRATEUR'),
(4, b'1', '', 'Cameroun', '698509268', 'Douala', '2026-01-21 19:40:33.000000', 'foguemjunior23@gmail.com', 'Moryan', NULL, '$2a$10$y2VmD.Eeq1U254ajKpe/HuXGC9xVhL4qP29h3OdDWXvGbHKphlR1e', NULL, 'SOCIETE');

-- --------------------------------------------------------

--
-- Table structure for table `validation`
--

CREATE TABLE `validation` (
  `id` int(11) NOT NULL,
  `activation` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `creation` datetime(6) DEFAULT NULL,
  `expiration` datetime(6) DEFAULT NULL,
  `utilisateur_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `validation`
--

INSERT INTO `validation` (`id`, `activation`, `code`, `creation`, `expiration`, `utilisateur_id`) VALUES
(1, '2026-01-16 03:34:21.000000', '109473', '2026-01-16 03:34:00.000000', '2026-01-16 03:44:00.000000', 1),
(2, '2026-01-16 03:37:08.000000', '432898', '2026-01-16 03:36:50.000000', '2026-01-16 03:46:50.000000', 2),
(3, '2026-01-16 03:41:33.000000', '527948', '2026-01-16 03:41:19.000000', '2026-01-16 03:51:19.000000', 3),
(4, '2026-01-21 19:40:46.000000', '218033', '2026-01-21 19:40:33.000000', '2026-01-21 19:50:33.000000', 4);

-- --------------------------------------------------------

--
-- Table structure for table `vehicule`
--

CREATE TABLE `vehicule` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `couleur` varchar(255) DEFAULT NULL,
  `date_arrivee` date DEFAULT NULL,
  `est_solde` bit(1) NOT NULL,
  `kilometrage` double NOT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `modele` varchar(255) DEFAULT NULL,
  `prix_base` double NOT NULL,
  `qte_stock` int(11) NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `status` enum('NEUF','OCCASION') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicule`
--

INSERT INTO `vehicule` (`id`, `annee`, `couleur`, `date_arrivee`, `est_solde`, `kilometrage`, `marque`, `modele`, `prix_base`, `qte_stock`, `reference`, `status`) VALUES
(1, 2008, 'Noir', '2025-08-16', b'1', 1200, 'Toyota', 'Corolla', 1200000, 5, 'REF-123', 'NEUF'),
(2, 2008, 'Rouge', '2005-01-05', b'0', 3000, 'Toyota', 'Starlet', 1350000, 2, 'REF-238', 'NEUF'),
(3, 2020, 'Bleu', '2020-03-07', b'0', 120000, 'BMW', 'M4', 45000000, 3, 'REF_128', 'NEUF'),
(4, 2022, 'Blanche', '2023-05-06', b'0', 4000, 'Mercedes', 'Classe S', 12000000, 2, 'REF_237', 'NEUF'),
(5, 2022, 'Noir', '2022-11-03', b'0', 13000, 'Yamaha ', 'Skuteri', 4000000, 4, 'SCOOT_123', 'NEUF');

-- --------------------------------------------------------

--
-- Table structure for table `vehicule_images`
--

CREATE TABLE `vehicule_images` (
  `vehicule_id` bigint(20) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicule_images`
--

INSERT INTO `vehicule_images` (`vehicule_id`, `image_url`) VALUES
(1, 'e839a11c-dfa2-4dd0-83a5-ff90e719e4d9.png'),
(1, 'f495416c-9851-4c04-bd6a-825a09fec49e.png'),
(2, '13733b47-b984-46d7-9560-f524169b7d39.jpeg'),
(2, 'cbde753a-0a8d-4602-9a9b-9030775485a0.jpeg'),
(3, '2073c97e-7f66-4701-8b53-54bf0c8b563a.png'),
(4, 'a77e534c-e5bc-4c2a-9583-a921aa4a5c16.png'),
(5, 'cb3207a4-ed0f-4283-9022-c3fb89badbac.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article_panier`
--
ALTER TABLE `article_panier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46lolhhnfmfvt5b6mxii2q4c4` (`panier_id`),
  ADD KEY `FKkih603vlnu0s4qb8h2ettyfp0` (`vehicule_id`);

--
-- Indexes for table `automobile_electrique`
--
ALTER TABLE `automobile_electrique`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `automobile_essence`
--
ALTER TABLE `automobile_essence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4gewq1g57h3veyw1npj79j701` (`client_id`),
  ADD KEY `FKh7a9jnfviwien38p2kp3evrem` (`vehicule_id`),
  ADD KEY `FKjxj2mgjiy8e030qpvphlou1ht` (`vendeur_id`);

--
-- Indexes for table `option_choisie`
--
ALTER TABLE `option_choisie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa72tpkvfgt7f341cjvnvdn6f3` (`article_panier_id`),
  ADD KEY `FK6l93avyom6ru65u4aus2qnljx` (`option_id`);

--
-- Indexes for table `option_commande`
--
ALTER TABLE `option_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnu8dc25impvrmokxa7a9y9eyq` (`commande_id`);

--
-- Indexes for table `option_produit`
--
ALTER TABLE `option_produit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK76pq3m75bl72qbrrap07bkp97` (`code`);

--
-- Indexes for table `option_produit_incompatibles`
--
ALTER TABLE `option_produit_incompatibles`
  ADD KEY `FKd69n6hmq8pq7lc021qv2d0x16` (`option_produit_id`);

--
-- Indexes for table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9cdicxkotj5he5ruwy9m03idx` (`utilisateur_id`);

--
-- Indexes for table `scooter_electrique`
--
ALTER TABLE `scooter_electrique`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `scooter_essence`
--
ALTER TABLE `scooter_essence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `validation`
--
ALTER TABLE `validation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKlbpw0tm5eu215mqoagm7wte1c` (`utilisateur_id`);

--
-- Indexes for table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vehicule_images`
--
ALTER TABLE `vehicule_images`
  ADD KEY `FKo3400ac44xbsi74nhvv7kvwkj` (`vehicule_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article_panier`
--
ALTER TABLE `article_panier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `option_choisie`
--
ALTER TABLE `option_choisie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `option_commande`
--
ALTER TABLE `option_commande`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `option_produit`
--
ALTER TABLE `option_produit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `validation`
--
ALTER TABLE `validation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `article_panier`
--
ALTER TABLE `article_panier`
  ADD CONSTRAINT `FK46lolhhnfmfvt5b6mxii2q4c4` FOREIGN KEY (`panier_id`) REFERENCES `panier` (`id`),
  ADD CONSTRAINT `FKkih603vlnu0s4qb8h2ettyfp0` FOREIGN KEY (`vehicule_id`) REFERENCES `vehicule` (`id`);

--
-- Constraints for table `automobile_electrique`
--
ALTER TABLE `automobile_electrique`
  ADD CONSTRAINT `FK9yfnnb8kt4ww4td1paqejpd6m` FOREIGN KEY (`id`) REFERENCES `vehicule` (`id`);

--
-- Constraints for table `automobile_essence`
--
ALTER TABLE `automobile_essence`
  ADD CONSTRAINT `FK31584i8m8t7lr1huu2g9eutkh` FOREIGN KEY (`id`) REFERENCES `vehicule` (`id`);

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK4gewq1g57h3veyw1npj79j701` FOREIGN KEY (`client_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKh7a9jnfviwien38p2kp3evrem` FOREIGN KEY (`vehicule_id`) REFERENCES `vehicule` (`id`),
  ADD CONSTRAINT `FKjxj2mgjiy8e030qpvphlou1ht` FOREIGN KEY (`vendeur_id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `option_choisie`
--
ALTER TABLE `option_choisie`
  ADD CONSTRAINT `FK6l93avyom6ru65u4aus2qnljx` FOREIGN KEY (`option_id`) REFERENCES `option_produit` (`id`),
  ADD CONSTRAINT `FKa72tpkvfgt7f341cjvnvdn6f3` FOREIGN KEY (`article_panier_id`) REFERENCES `article_panier` (`id`);

--
-- Constraints for table `option_commande`
--
ALTER TABLE `option_commande`
  ADD CONSTRAINT `FKnu8dc25impvrmokxa7a9y9eyq` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`);

--
-- Constraints for table `option_produit_incompatibles`
--
ALTER TABLE `option_produit_incompatibles`
  ADD CONSTRAINT `FKd69n6hmq8pq7lc021qv2d0x16` FOREIGN KEY (`option_produit_id`) REFERENCES `option_produit` (`id`);

--
-- Constraints for table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `FK9cdicxkotj5he5ruwy9m03idx` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `scooter_electrique`
--
ALTER TABLE `scooter_electrique`
  ADD CONSTRAINT `FKoapmie9gtqubppqchs0es5onn` FOREIGN KEY (`id`) REFERENCES `vehicule` (`id`);

--
-- Constraints for table `scooter_essence`
--
ALTER TABLE `scooter_essence`
  ADD CONSTRAINT `FK1te2touwv71mf3ouofff4jnrj` FOREIGN KEY (`id`) REFERENCES `vehicule` (`id`);

--
-- Constraints for table `validation`
--
ALTER TABLE `validation`
  ADD CONSTRAINT `FKg0vmxkmj7wfai4s41fytetn9n` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `vehicule_images`
--
ALTER TABLE `vehicule_images`
  ADD CONSTRAINT `FKo3400ac44xbsi74nhvv7kvwkj` FOREIGN KEY (`vehicule_id`) REFERENCES `vehicule` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
