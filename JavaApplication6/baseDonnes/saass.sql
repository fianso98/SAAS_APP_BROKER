-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 21 juin 2019 à 15:53
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `saass`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE `account` (
  `account_id` int(100) NOT NULL,
  `account_nom` varchar(255) DEFAULT NULL,
  `account_password` varchar(255) DEFAULT NULL,
  `account_detail` varchar(255) DEFAULT NULL,
  `domain_nom_account` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`account_id`, `account_nom`, `account_password`, `account_detail`, `domain_nom_account`) VALUES
(1, 'sofiane', '1234', 'Fournisseur', 'transport');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `client_nom` varchar(255) NOT NULL,
  `client_password` varchar(255) DEFAULT NULL,
  `client_points` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`client_nom`, `client_password`, `client_points`) VALUES
('sofiane', '1234', '1');

-- --------------------------------------------------------

--
-- Structure de la table `domain`
--

CREATE TABLE `domain` (
  `domain_id` int(30) NOT NULL,
  `domain_nom` varchar(255) DEFAULT NULL,
  `fournisseur_clé` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `fournisseur_clé` int(30) NOT NULL,
  `domain_nom` varchar(255) DEFAULT NULL,
  `fournisseur_nom` varchar(255) DEFAULT NULL,
  `fournisseur_password` varchar(255) DEFAULT NULL,
  `fichier` blob,
  `fournisseur_date` datetime DEFAULT NULL,
  `fournisseur_nb_vente` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`fournisseur_clé`, `domain_nom`, `fournisseur_nom`, `fournisseur_password`, `fichier`, `fournisseur_date`, `fournisseur_nb_vente`) VALUES
(5, 'transport', 'sofiane', '1234', 0x3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d3822207374616e64616c6f6e653d226e6f223f3e3c646566696e6974696f6e733e3c74797065733e3c70726f707269c3a974c3a9733e3c72c3a97075746174696f6e3e313c2f72c3a97075746174696f6e3e3c636f6e6669616e63653e313c2f636f6e6669616e63653e3c657870c3a97269656e63653e313c2f657870c3a97269656e63653e3c646973706f6e6962696c6974c3a93e313c2f646973706f6e6962696c6974c3a93e3c636fc3bb743e313c2f636fc3bb743e3c74656d70735f64655f72c3a9706f6e73653e313c2f74656d70735f64655f72c3a9706f6e73653e3c726973717565733e313c2f726973717565733e3c74656d70735f6d6f79656e5f64655f72657072c3a973656e746174696f6e3e313c2f74656d70735f6d6f79656e5f64655f72657072c3a973656e746174696f6e3e3c2f70726f707269c3a974c3a9733e3c2f74797065733e3c6d6573736167653e646566696e6974696f6e206f66207468652064617461206265696e6720636f6d6d756e6963617465642e2e2e2e3c2f6d6573736167653e3c706f7274547970653e736574206f66206f7065726174696f6e732e2e2e2e2e2e203c2f706f7274547970653e3c62696e64696e673e70726f746f636f6c20616e64206461746120666f726d61742073706563696669636174696f6e2e2e2e2e3c2f62696e64696e673e3c2f646566696e6974696f6e733e, '2019-06-15 12:08:28', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_nom`);

--
-- Index pour la table `domain`
--
ALTER TABLE `domain`
  ADD PRIMARY KEY (`domain_id`),
  ADD KEY `fournisseur_clé` (`fournisseur_clé`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`fournisseur_clé`),
  ADD UNIQUE KEY `thekey` (`domain_nom`,`fournisseur_nom`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `domain`
--
ALTER TABLE `domain`
  MODIFY `domain_id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `fournisseur_clé` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `domain`
--
ALTER TABLE `domain`
  ADD CONSTRAINT `domain_ibfk_1` FOREIGN KEY (`fournisseur_clé`) REFERENCES `fournisseur` (`fournisseur_clé`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
