start transaction;

create database `Acme-Rendezvous`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Rendezvous`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Rendezvous`.* to 'acme-manager'@'%';
-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Rendezvous
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adult` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` int(11) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (59,0,'','fabgar@gmail.com','Fabián','658478521',43089,'García',56);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (68,0,'description1','2018-05-10 18:00:00','announcement1'),(69,0,'description2','2018-05-10 20:00:00','announcement2'),(70,0,'description3','2018-05-15 19:00:00','announcement3');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `written` varchar(255) DEFAULT NULL,
  `answerer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_oueobn94019h564p7gcwyr962` (`answerer_id`),
  CONSTRAINT `FK_oueobn94019h564p7gcwyr962` FOREIGN KEY (`answerer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (66,0,'Me encanta',61),(67,0,'La adoro',60);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (62,0,'2018-05-25 23:59:00','http://www.google.com/pictures','Hola, que tal'),(64,0,'2018-05-15 14:00:00','http://www.google.com/pictures','Comentario 2');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_reply`
--

DROP TABLE IF EXISTS `comment_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_reply` (
  `Comment_id` int(11) NOT NULL,
  `replies_id` int(11) NOT NULL,
  UNIQUE KEY `UK_he55waq583i8uonwsnh1u37ad` (`replies_id`),
  KEY `FK_3v03l5mr9hjiqvly8dtsmul1p` (`Comment_id`),
  CONSTRAINT `FK_3v03l5mr9hjiqvly8dtsmul1p` FOREIGN KEY (`Comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FK_he55waq583i8uonwsnh1u37ad` FOREIGN KEY (`replies_id`) REFERENCES `reply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_reply`
--

LOCK TABLES `comment_reply` WRITE;
/*!40000 ALTER TABLE `comment_reply` DISABLE KEYS */;
INSERT INTO `comment_reply` VALUES (62,63),(64,65);
/*!40000 ALTER TABLE `comment_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `questionToAnswer` varchar(255) DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  `rendezvous_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e72rcs3uatxufr3afg1xfkkd4` (`creator_id`),
  KEY `FK_ejk14ged2t2jaquwya6f97awi` (`rendezvous_id`),
  CONSTRAINT `FK_ejk14ged2t2jaquwya6f97awi` FOREIGN KEY (`rendezvous_id`) REFERENCES `rendezvous` (`id`),
  CONSTRAINT `FK_e72rcs3uatxufr3afg1xfkkd4` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (73,0,'¿Qué te parece el fútbol?',60,71),(74,0,'¿Te gusta Belén Esteban?',61,72);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answer`
--

DROP TABLE IF EXISTS `question_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_answer` (
  `Question_id` int(11) NOT NULL,
  `answers_id` int(11) NOT NULL,
  UNIQUE KEY `UK_mtd6dwvb7winafyb55ulyfy4n` (`answers_id`),
  KEY `FK_i5qibemuk47pknf0ss2ulbvnn` (`Question_id`),
  CONSTRAINT `FK_i5qibemuk47pknf0ss2ulbvnn` FOREIGN KEY (`Question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_mtd6dwvb7winafyb55ulyfy4n` FOREIGN KEY (`answers_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answer`
--

LOCK TABLES `question_answer` WRITE;
/*!40000 ALTER TABLE `question_answer` DISABLE KEYS */;
INSERT INTO `question_answer` VALUES (73,66),(74,67);
/*!40000 ALTER TABLE `question_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rendezvous` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adultOnly` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `finalMode` bit(1) NOT NULL,
  `flag` int(11) DEFAULT NULL,
  `locationLatitude` double DEFAULT NULL,
  `locationLongitude` double DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4cru16jpqbsxd0g6runtbwqlt` (`creator_id`),
  CONSTRAINT `FK_4cru16jpqbsxd0g6runtbwqlt` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous`
--

LOCK TABLES `rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous` DISABLE KEYS */;
INSERT INTO `rendezvous` VALUES (71,0,'\0','Reunión trimestral para ponerse al día','\0',0,23.5,47.3,'2018-05-23 23:59:00','Reunión trimestral','http://www.google.com/pictures',60),(72,0,'\0','Description rendezvous 2','\0',1,20.5,70,'2018-06-30 20:30:00','Rendezvous 2','http://www.google.com/pictures',61);
/*!40000 ALTER TABLE `rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous_announcement`
--

DROP TABLE IF EXISTS `rendezvous_announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rendezvous_announcement` (
  `Rendezvous_id` int(11) NOT NULL,
  `announcements_id` int(11) NOT NULL,
  UNIQUE KEY `UK_mlwhdfpnv7d4o5ww7gluddgrv` (`announcements_id`),
  KEY `FK_2y7rmn6v1bqn1lm9lbs1w28vr` (`Rendezvous_id`),
  CONSTRAINT `FK_2y7rmn6v1bqn1lm9lbs1w28vr` FOREIGN KEY (`Rendezvous_id`) REFERENCES `rendezvous` (`id`),
  CONSTRAINT `FK_mlwhdfpnv7d4o5ww7gluddgrv` FOREIGN KEY (`announcements_id`) REFERENCES `announcement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous_announcement`
--

LOCK TABLES `rendezvous_announcement` WRITE;
/*!40000 ALTER TABLE `rendezvous_announcement` DISABLE KEYS */;
INSERT INTO `rendezvous_announcement` VALUES (71,68),(72,69),(72,70);
/*!40000 ALTER TABLE `rendezvous_announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous_comment`
--

DROP TABLE IF EXISTS `rendezvous_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rendezvous_comment` (
  `Rendezvous_id` int(11) NOT NULL,
  `comments_id` int(11) NOT NULL,
  UNIQUE KEY `UK_233yqm5jxngf9wj7xvwf8asem` (`comments_id`),
  KEY `FK_10l6y61x4rurnwre3uw62620y` (`Rendezvous_id`),
  CONSTRAINT `FK_10l6y61x4rurnwre3uw62620y` FOREIGN KEY (`Rendezvous_id`) REFERENCES `rendezvous` (`id`),
  CONSTRAINT `FK_233yqm5jxngf9wj7xvwf8asem` FOREIGN KEY (`comments_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous_comment`
--

LOCK TABLES `rendezvous_comment` WRITE;
/*!40000 ALTER TABLE `rendezvous_comment` DISABLE KEYS */;
INSERT INTO `rendezvous_comment` VALUES (71,62),(72,64);
/*!40000 ALTER TABLE `rendezvous_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous_rendezvous`
--

DROP TABLE IF EXISTS `rendezvous_rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rendezvous_rendezvous` (
  `Rendezvous_id` int(11) NOT NULL,
  `rendezvouses_id` int(11) NOT NULL,
  KEY `FK_d4jmno5qg9i427qenlp5t0l5l` (`rendezvouses_id`),
  KEY `FK_fuhs4y1oqtlba5sencebgmosa` (`Rendezvous_id`),
  CONSTRAINT `FK_fuhs4y1oqtlba5sencebgmosa` FOREIGN KEY (`Rendezvous_id`) REFERENCES `rendezvous` (`id`),
  CONSTRAINT `FK_d4jmno5qg9i427qenlp5t0l5l` FOREIGN KEY (`rendezvouses_id`) REFERENCES `rendezvous` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous_rendezvous`
--

LOCK TABLES `rendezvous_rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous_rendezvous` DISABLE KEYS */;
INSERT INTO `rendezvous_rendezvous` VALUES (71,72);
/*!40000 ALTER TABLE `rendezvous_rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous_user`
--

DROP TABLE IF EXISTS `rendezvous_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rendezvous_user` (
  `attendances_id` int(11) NOT NULL,
  `attendants_id` int(11) NOT NULL,
  KEY `FK_38sxgvfuoa8bkkqfyahtfgj7l` (`attendants_id`),
  KEY `FK_mpg87ys2md3rc2b0byk63j94u` (`attendances_id`),
  CONSTRAINT `FK_mpg87ys2md3rc2b0byk63j94u` FOREIGN KEY (`attendances_id`) REFERENCES `rendezvous` (`id`),
  CONSTRAINT `FK_38sxgvfuoa8bkkqfyahtfgj7l` FOREIGN KEY (`attendants_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous_user`
--

LOCK TABLES `rendezvous_user` WRITE;
/*!40000 ALTER TABLE `rendezvous_user` DISABLE KEYS */;
INSERT INTO `rendezvous_user` VALUES (71,60),(72,60),(72,61);
/*!40000 ALTER TABLE `rendezvous_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (63,0,'2018-05-26 15:30:00','No me parece correcto'),(65,0,'2018-05-15 19:30:00','Ok');
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adult` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` int(11) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (60,0,'','carfer@gmail.com','Carlos','636985147',30479,'Fernández',57),(61,0,'','perpe@gmail.com','Pedro','636456111',45100,'Ruiz Penegordo',58);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_comment`
--

DROP TABLE IF EXISTS `user_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_comment` (
  `User_id` int(11) NOT NULL,
  `comments_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ah9x95me6onvo198wl0s87lji` (`comments_id`),
  KEY `FK_irlvalt2dtjvuof5n6wxmif7f` (`User_id`),
  CONSTRAINT `FK_irlvalt2dtjvuof5n6wxmif7f` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ah9x95me6onvo198wl0s87lji` FOREIGN KEY (`comments_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comment`
--

LOCK TABLES `user_comment` WRITE;
/*!40000 ALTER TABLE `user_comment` DISABLE KEYS */;
INSERT INTO `user_comment` VALUES (60,62),(61,64);
/*!40000 ALTER TABLE `user_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_reply`
--

DROP TABLE IF EXISTS `user_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_reply` (
  `User_id` int(11) NOT NULL,
  `replies_id` int(11) NOT NULL,
  UNIQUE KEY `UK_1557qookvxnamyrdjkta5dn91` (`replies_id`),
  KEY `FK_gt4y0o18w0ll7eon67oa2oegw` (`User_id`),
  CONSTRAINT `FK_gt4y0o18w0ll7eon67oa2oegw` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_1557qookvxnamyrdjkta5dn91` FOREIGN KEY (`replies_id`) REFERENCES `reply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_reply`
--

LOCK TABLES `user_reply` WRITE;
/*!40000 ALTER TABLE `user_reply` DISABLE KEYS */;
INSERT INTO `user_reply` VALUES (60,63),(61,65);
/*!40000 ALTER TABLE `user_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (56,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(57,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(58,0,'7e58d63b60197ceb55a1c487989a3720','user2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (56,'ADMIN'),(57,'USER'),(58,'USER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 23:46:20
commit;