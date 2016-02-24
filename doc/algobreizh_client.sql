-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 24 Février 2016 à 10:01
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `algobreizh_client`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `dateRdv` int(11) NOT NULL,
  `dateDernierRdv` int(11) NOT NULL,
  `idZoneGeo` int(11) NOT NULL,
  `nomClient` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenomClient` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `particulariteClient` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `numeroClient` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `emailClient` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idClient`),
  KEY `FK_zoneGeoClient` (`idZoneGeo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`idClient`, `dateRdv`, `dateDernierRdv`, `idZoneGeo`, `nomClient`, `prenomClient`, `particulariteClient`, `numeroClient`, `emailClient`) VALUES
(3, 0, 0, 5, 'Meunier', 'Paul', '', '0660656869', 'paulmeunier@gmail.com'),
(5, 0, 0, 2, 'Chevalier', 'Pierre', 'client en retard sur ses factures', '0668844550', 'chevalier@gmail.com'),
(6, 0, 0, 9, 'Leblanc', 'Marguerite', '', '0685456885', 'lbMarguerite@gmail.com'),
(7, 0, 0, 9, 'Loisel', 'Marie', '', '0656894870', 'marie.loisel@gmail.com'),
(8, 0, 0, 9, 'Fonteneau', 'Anne', '', '0746885870', 'ftn.anne@gmail.com'),
(9, 0, 0, 8, 'Garcia', 'Jose', '', '0589214554', 'Jgarcia@gmail.com'),
(10, 0, 0, 1, 'Larcher', 'Antonio', '', '0765456865', 'Lantonio@gmail.com'),
(11, 0, 0, 2, 'Mitachevitch', 'Gerard', '', '0645878988', 'Mitach.gerard@gmail.com'),
(12, 0, 0, 9, 'Pavageau', 'Marcus', '', '0895665237', 'Marcus06@gmail.com'),
(13, 0, 0, 4, 'Vallée', 'Simon', '', '0265585440', 'SimonVallee@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `commercial`
--

CREATE TABLE IF NOT EXISTS `commercial` (
  `idCommercial` int(11) NOT NULL AUTO_INCREMENT,
  `idZoneGeo` int(11) NOT NULL,
  `emailCommercial` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `numeroCommercial` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `nomCommercial` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenomCommercial` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idCommercial`),
  KEY `FK_zoneGeoCommercial` (`idZoneGeo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `commercial`
--

INSERT INTO `commercial` (`idCommercial`, `idZoneGeo`, `emailCommercial`, `numeroCommercial`, `nomCommercial`, `prenomCommercial`) VALUES
(1, 9, 'Guy.camallonga@gmail.com', '0699887766', 'Guy', 'Camallonga');

-- --------------------------------------------------------

--
-- Structure de la table `connexion`
--

CREATE TABLE IF NOT EXISTS `connexion` (
  `idConnexion` int(11) NOT NULL AUTO_INCREMENT,
  `hash` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `idCommercial` int(11) NOT NULL,
  PRIMARY KEY (`idConnexion`),
  KEY `FK_ConnexionCommercial` (`idCommercial`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `connexion`
--

INSERT INTO `connexion` (`idConnexion`, `hash`, `idCommercial`) VALUES
(1, '8b7df143d91c716ecfa5fc1730022f6b421b05cedee8fd52b1fc65a96030ad52', 1);

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

CREATE TABLE IF NOT EXISTS `rendezvous` (
  `idRendezvous` int(11) NOT NULL AUTO_INCREMENT,
  `contactRendezvous` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lieuRendezvous` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idCommercial` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `dateRendezvous` timestamp NOT NULL,
  PRIMARY KEY (`idRendezvous`),
  KEY `FK_ClientRendezvous` (`idClient`),
  KEY `FK_CommercialRendezvous` (`idCommercial`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `zonegeo`
--

CREATE TABLE IF NOT EXISTS `zonegeo` (
  `idZoneGeo` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idZoneGeo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Contenu de la table `zonegeo`
--

INSERT INTO `zonegeo` (`idZoneGeo`, `libelle`) VALUES
(1, 'Aquitaine'),
(2, 'Region Parisienne'),
(3, 'Bretagne'),
(4, 'Loire Atlantique'),
(5, 'Provence'),
(6, 'Picardie'),
(7, 'Ardèche'),
(8, 'Nord Pas de Calais'),
(9, 'Pyrénées'),
(10, 'Alpes');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_zoneGeoClient` FOREIGN KEY (`idZoneGeo`) REFERENCES `zonegeo` (`idZoneGeo`);

--
-- Contraintes pour la table `commercial`
--
ALTER TABLE `commercial`
  ADD CONSTRAINT `FK_zoneGeoCommercial` FOREIGN KEY (`idZoneGeo`) REFERENCES `zonegeo` (`idZoneGeo`);

--
-- Contraintes pour la table `connexion`
--
ALTER TABLE `connexion`
  ADD CONSTRAINT `FK_ConnexionCommercial` FOREIGN KEY (`idCommercial`) REFERENCES `commercial` (`idCommercial`);

--
-- Contraintes pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD CONSTRAINT `FK_CommercialRendezvous` FOREIGN KEY (`idCommercial`) REFERENCES `commercial` (`idCommercial`),
  ADD CONSTRAINT `FK_ClientRendezvous` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
