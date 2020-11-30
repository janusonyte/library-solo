CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `stockID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `volume` varchar(100) DEFAULT NULL,
  `issue` varchar(100) DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL,
  `borrowerID` int DEFAULT NULL,
  `type` varchar(45) NOT NULL COMMENT 'type - book or journal',
  `returndate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stockID`),
  UNIQUE KEY `stockID_UNIQUE` (`stockID`),
  KEY `borrowerID` (`borrowerID`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`borrowerID`) REFERENCES `members` (`memberID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,'Circe','Madeline Miller','Bloomsbury',NULL,NULL,'2018',NULL,'book',NULL),(2,'Harry Potter and the Philosopher\'s Stone','JK Rowling','Bloomsbury',NULL,NULL,'1997',2,'book','2020/12/30'),(3,'Harry Potter and the Chamber of Secrets','JK Rowling','Bloomsbury',NULL,NULL,'1998',NULL,'book',NULL),(4,'Lancet',NULL,NULL,'542','3','2020',NULL,'journal',NULL),(5,'Harry Potter and the Prisoner of Azkaban','JK Rowling','Bloomsbury',NULL,NULL,'1999',NULL,'book',NULL),(6,'Harry Potter and the Goblet of Fire','JK Rowling','Bloomsbury',NULL,NULL,'2000',NULL,'book',NULL),(7,'Harry Potter and the Order of the Phoenix','JK Rowling','Bloomsbury',NULL,NULL,'2003',NULL,'book',NULL),(8,'Harry Potter and the Half-Blood Prince','JK Rowling','Bloomsbury',NULL,NULL,'2005',NULL,'book',NULL),(9,'Harry Potter and the Deathly Hallows','JK Rowling','Bloomsbury',NULL,NULL,'2007',NULL,'book',NULL),(10,'Brief Answers to the Big Questions','Stephen Hawking','John Murray',NULL,NULL,'2018',NULL,'book',NULL),(11,'Superintelligence: Paths, Dangers, Strategies','Nick Bostrom','Oxford University Press',NULL,NULL,'2014',NULL,'book',NULL),(12,'Nature',NULL,NULL,'587','7835','2020',NULL,'journal',NULL),(13,'National Geographic Magazine',NULL,NULL,'141','3','1972',2,'journal','2020/12/02'),(14,'The Master Algorithm','Pedro Domingos','Penguin Books',NULL,NULL,'2017',NULL,'book',NULL),(15,'Wild: A Journey From Lost To Found','Cheryl Strayed','Atlantic Books',NULL,NULL,'1988',NULL,'book',NULL);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-30 22:16:54
