-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 16 Février 2016 à 09:50
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
  KEY `FK_zoneGeoClient` (`idZoneGeo`),
  KEY `FK_ClientLastRdv` (`dateDernierRdv`),
  KEY `FK_ClientRdv` (`dateRdv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `commercial`
--

CREATE TABLE IF NOT EXISTS `commercial` (
  `idCommercial` int(11) NOT NULL AUTO_INCREMENT,
  `idZoneGeo` int(11) NOT NULL,
  `emailCommercial` int(255) NOT NULL,
  `numeroCommercial` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `nomCommercial` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenomCommercial` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idCommercial`),
  KEY `FK_zoneGeoCommercial` (`idZoneGeo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `zonegeo`
--

CREATE TABLE IF NOT EXISTS `zonegeo` (
  `idZoneGeo` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idZoneGeo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_ClientLastRdv` FOREIGN KEY (`dateDernierRdv`) REFERENCES `rendezvous` (`idRendezvous`),
  ADD CONSTRAINT `FK_ClientRdv` FOREIGN KEY (`dateRdv`) REFERENCES `rendezvous` (`idRendezvous`),
  ADD CONSTRAINT `FK_zoneGeoClient` FOREIGN KEY (`idZoneGeo`) REFERENCES `zonegeo` (`idZoneGeo`);

--
-- Contraintes pour la table `commercial`
--
ALTER TABLE `commercial`
  ADD CONSTRAINT `FK_zoneGeoCommercial` FOREIGN KEY (`idZoneGeo`) REFERENCES `zonegeo` (`idZoneGeo`);

--
-- Contraintes pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD CONSTRAINT `FK_CommercialRendezvous` FOREIGN KEY (`idCommercial`) REFERENCES `commercial` (`idCommercial`),
  ADD CONSTRAINT `FK_ClientRendezvous` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
