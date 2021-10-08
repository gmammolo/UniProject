-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Mag 02, 2015 alle 18:07
-- Versione del server: 5.5.38-0ubuntu0.14.04.1
-- Versione PHP: 5.5.9-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `socialproject`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `Comment`
--

CREATE TABLE IF NOT EXISTS `Comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idpost` int(11) NOT NULL,
  `text` varchar(250) NOT NULL,
  `author` int(11) NOT NULL,
  `likeit` int(11) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idpost` (`idpost`),
  KEY `author` (`author`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dump dei dati per la tabella `Comment`
--

INSERT INTO `Comment` (`id`, `idpost`, `text`, `author`, `likeit`, `date`) VALUES
(1, 27, 'Che bel commento!', 9, 2, '2015-05-02 16:18:41');

-- --------------------------------------------------------

--
-- Struttura della tabella `Notify`
--

CREATE TABLE IF NOT EXISTS `Notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL,
  `receiving` int(11) NOT NULL,
  `cause` int(11) NOT NULL,
  `reference` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dump dei dati per la tabella `Notify`
--

INSERT INTO `Notify` (`id`, `type`, `receiving`, `cause`, `reference`, `date`) VALUES
(1, 'newPost', -1, 11, 27, '2015-05-02 16:18:11'),
(2, 'LikeCommen', 11, -1, 27, '2015-05-02 16:18:16'),
(3, 'newComment', -1, 9, 1, '2015-05-02 16:18:41'),
(4, 'LikeCommen', 9, -1, 1, '2015-05-02 16:44:05'),
(5, 'LikeCommen', 9, -1, 1, '2015-05-02 16:44:05'),
(6, 'LikeCommen', 11, -1, 27, '2015-05-02 16:44:31'),
(7, 'LikeCommen', 11, -1, 27, '2015-05-02 16:44:31'),
(8, 'newPost', -1, 11, 28, '2015-05-02 17:13:31'),
(9, 'LikeCommen', 11, -1, 28, '2015-05-02 17:15:43'),
(10, 'newPost', -1, 9, 29, '2015-05-02 17:15:54'),
(11, 'LikeCommen', 9, -1, 29, '2015-05-02 17:15:56'),
(12, 'LikeCommen', 9, -1, 29, '2015-05-02 17:15:56'),
(13, 'LikeCommen', 9, -1, 29, '2015-05-02 17:15:56'),
(14, 'newPost', -1, 11, 30, '2015-05-02 17:17:30');

-- --------------------------------------------------------

--
-- Struttura della tabella `Post`
--

CREATE TABLE IF NOT EXISTS `Post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) NOT NULL,
  `text` varchar(250) NOT NULL,
  `image` varchar(250) NOT NULL,
  `hashtag` varchar(250) NOT NULL,
  `date` datetime NOT NULL,
  `locate` varchar(30) NOT NULL,
  `privacy` int(11) NOT NULL,
  `likeit` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dump dei dati per la tabella `Post`
--

INSERT INTO `Post` (`id`, `author`, `text`, `image`, `hashtag`, `date`, `locate`, `privacy`, `likeit`) VALUES
(27, 11, 'Scrivo un nuovo commento qui', '', 'a:0:{}', '2015-05-02 16:18:11', '', 1, 3),
(28, 11, 'Ne scrivo una altra', '', 'a:0:{}', '2015-05-02 17:13:31', '', 1, 1),
(29, 9, 'rewrw', '', 'a:0:{}', '2015-05-02 17:15:54', '', 1, 3),
(30, 11, 'Pippo Porva l.ultima volta', '', 'a:0:{}', '2015-05-02 17:17:30', '', 1, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `Profile`
--

CREATE TABLE IF NOT EXISTS `Profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(64) NOT NULL,
  `generalita` enum('uomo','donna','altro','nessuno') NOT NULL DEFAULT 'nessuno',
  `avatar` varchar(250) NOT NULL,
  `residenza` varchar(250) NOT NULL,
  `data` date NOT NULL,
  `email` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dump dei dati per la tabella `Profile`
--

INSERT INTO `Profile` (`id`, `nome`, `generalita`, `avatar`, `residenza`, `data`, `email`) VALUES
(2, 'amministratore', 'uomo', 'http://www.gopsp.it/uploads/profile/photo-206.png', '', '0000-00-00', 'root@gmail.com'),
(4, 'Pippo', 'nessuno', 'https://scontent-mxp.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/11074295_10205690252111622_7873289294299222000_n.jpg?oh=c0af5564920ff115eed27388ce9c57c7&oe=559F6F9B', '', '0000-00-00', 'pippo@super.it'),
(6, 'Topolino', 'nessuno', 'http://img4.wikia.nocookie.net/__cb20141220175033/disney/it/images/4/4e/Topolinodisney.jpg', '', '0000-00-00', 'Miki@altro.com'),
(7, 'Piero-Franco', 'nessuno', 'Template/images/avatar.jpg', '', '0000-00-00', 'piero@gre.it'),
(8, 'Pluto', 'nessuno', 'Template/images/avatar.jpg', '', '0000-00-00', 'pluto@megaemail.it'),
(9, 'Nuovo', 'nessuno', 'Template/images/avatar.jpg', '', '0000-00-00', 'tmp@gmailc.om');

-- --------------------------------------------------------

--
-- Struttura della tabella `Relationship`
--

CREATE TABLE IF NOT EXISTS `Relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applicant` int(11) NOT NULL,
  `requested` int(11) NOT NULL,
  `ablocked` tinyint(1) NOT NULL DEFAULT '0',
  `rblocked` tinyint(1) NOT NULL DEFAULT '0',
  `accepted` tinyint(1) NOT NULL DEFAULT '0',
  `acceptedDate` datetime DEFAULT NULL,
  `requestDate` datetime DEFAULT NULL,
  `ablockedDate` datetime DEFAULT NULL,
  `rblockedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `applicant` (`applicant`),
  KEY `requested` (`requested`),
  KEY `applicant_2` (`applicant`),
  KEY `requested_2` (`requested`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `Relationship`
--

INSERT INTO `Relationship` (`id`, `applicant`, `requested`, `ablocked`, `rblocked`, `accepted`, `acceptedDate`, `requestDate`, `ablockedDate`, `rblockedDate`) VALUES
(1, 9, 11, 0, 0, 1, '2015-04-29 20:25:36', '2015-04-29 20:09:37', NULL, NULL),
(2, 11, 13, 0, 0, 1, '2015-04-29 22:28:55', '2015-04-29 20:34:40', NULL, NULL),
(3, 9, 13, 0, 0, 0, NULL, '2015-05-01 18:49:26', NULL, NULL),
(4, 16, 11, 0, 0, 1, '2015-05-02 15:27:19', '2015-05-02 15:26:22', NULL, NULL),
(5, 16, 13, 0, 0, 0, NULL, '2015-05-02 15:26:27', NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `Showcase`
--

CREATE TABLE IF NOT EXISTS `Showcase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_post` int(11) NOT NULL,
  `displayed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_post` (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dump dei dati per la tabella `Showcase`
--

INSERT INTO `Showcase` (`id`, `id_user`, `id_post`, `displayed`) VALUES
(1, 11, 27, 0),
(2, 11, 28, 0),
(3, 9, 29, 0),
(4, 11, 30, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(74) NOT NULL,
  `accessLevel` int(11) NOT NULL,
  `roles` varchar(250) NOT NULL,
  `email` varchar(32) NOT NULL,
  `profile` int(11) NOT NULL,
  `cookie` varchar(33) DEFAULT NULL,
  `cookie_expire` date NOT NULL,
  `lastnewsaccess` datetime NOT NULL COMMENT 'Serve per l''eliminazione notifiche',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dump dei dati per la tabella `User`
--

INSERT INTO `User` (`id`, `username`, `password`, `accessLevel`, `roles`, `email`, `profile`, `cookie`, `cookie_expire`, `lastnewsaccess`) VALUES
(9, 'admin', '$1$3rl9ZBDI$rOfwt7yKy6LXv7PAI3uKJ1', 5, 'a:1:{i:0;i:1;}', 'root@gmail.com', 2, '', '0000-00-00', '2015-05-02 18:03:11'),
(11, 'Pippo', '$1$1255aAfg$kCSIvXo11RRGOioC.wAx00', 2, 'a:0:{}', 'pippo@super.it', 4, NULL, '0000-00-00', '2015-05-02 17:17:30'),
(13, 'Topolino', '$1$2TDKxkgZ$PE7a0Xt0closwEpOVxrA8/', 2, 'a:0:{}', 'Miki@altro.com', 6, NULL, '0000-00-00', '0000-00-00 00:00:00'),
(14, 'Piero-Franco', '$1$xk4xSJ7J$R1cWbOTRWxhWnZK4AJ7pB/', 1, 'a:0:{}', 'piero@gre.it', 7, NULL, '0000-00-00', '0000-00-00 00:00:00'),
(15, 'Pluto', '$1$vXNnx3vP$rXmUEQtinF2MCXGA.MJuy/', 2, 'a:0:{}', 'pluto@megaemail.it', 8, NULL, '0000-00-00', '0000-00-00 00:00:00'),
(16, 'Nuovo', '$1$vDowMqjJ$9FF/rHs7oYDpK1r/2mQTR.', 2, 'a:0:{}', 'tmp@gmailc.om', 9, NULL, '0000-00-00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Struttura della tabella `V_UserRegistred`
--
-- in uso(#1356 - View 'socialproject.V_UserRegistred' references invalid table(s) or column(s) or function(s) or definer/invoker of view lack rights to use them)
-- Si è verificato un errore durante la lettura dei dati: (#1356 - View 'socialproject.V_UserRegistred' references invalid table(s) or column(s) or function(s) or definer/invoker of view lack rights to use them)

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Comment`
--
ALTER TABLE `Comment`
  ADD CONSTRAINT `Comment_ibfk_1` FOREIGN KEY (`idpost`) REFERENCES `Post` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `Comment_ibfk_2` FOREIGN KEY (`author`) REFERENCES `User` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `Relationship`
--
ALTER TABLE `Relationship`
  ADD CONSTRAINT `Relationship_ibfk_1` FOREIGN KEY (`applicant`) REFERENCES `User` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `Relationship_ibfk_2` FOREIGN KEY (`requested`) REFERENCES `User` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `Showcase`
--
ALTER TABLE `Showcase`
  ADD CONSTRAINT `Showcase_ibfk_2` FOREIGN KEY (`id_post`) REFERENCES `Post` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `Showcase_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
