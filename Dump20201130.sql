CREATE DATABASE  IF NOT EXISTS `procamp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `procamp`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: procamp
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `building_id` int NOT NULL,
  `work_name` varchar(100) DEFAULT NULL,
  `price` decimal(10,1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_building_idx` (`building_id`),
  CONSTRAINT `activity_building` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` VALUES (1,1,'build a foundation',100.9);
INSERT INTO `activity` VALUES (2,10,' Install Hard Surface Flooring, Countertops',1000.0);
INSERT INTO `activity` VALUES (3,11,'Complete Rough Plumbing',300.0);
INSERT INTO `activity` VALUES (4,8,'Install Insulation',2030.0);
INSERT INTO `activity` VALUES (5,10,'Complete Drywall and Interior Fixtures',900.0);
INSERT INTO `activity` VALUES (6,11,'Complete Rough Plumbing',300.0);
INSERT INTO `activity` VALUES (7,11,'Complete Rough Framing',5000.0);
INSERT INTO `activity` VALUES (8,10,' Install Hard Surface Flooring, Countertops',1000.0);
INSERT INTO `activity` VALUES (9,10,'Complete Drywall and Interior Fixtures',900.0);
INSERT INTO `activity` VALUES (10,11,'Complete Rough Framing',5000.0);
INSERT INTO `activity` VALUES (11,11,'Complete Rough Plumbing',300.0);

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` int NOT NULL AUTO_INCREMENT,
  `report_id` int NOT NULL,
  `building_name` varchar(45) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `building_report_idx` (`report_id`),
  CONSTRAINT `building_report` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

INSERT INTO `building` VALUES (1,1,'building1',_binary '\0');
INSERT INTO `building` VALUES (2,1,'building2',_binary '');
INSERT INTO `building` VALUES (8,9,'Grey Building',_binary '\0');
INSERT INTO `building` VALUES (9,9,'Yellow Building',_binary '\0');
INSERT INTO `building` VALUES (10,9,'Black Building',_binary '');
INSERT INTO `building` VALUES (11,10,'Pink Building',_binary '\0');

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `report_name` varchar(45) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `order_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `report_user_idx` (`user_id`),
  CONSTRAINT `report_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

INSERT INTO `report` VALUES (1,1,'report1',100000.00,'2020-11-28');
INSERT INTO `report` VALUES (2,1,'report2',1000.00,'3920-12-29');
INSERT INTO `report` VALUES (3,1,'report3',1000.00,'3920-12-29');
INSERT INTO `report` VALUES (9,7,'September report',0.00,'3920-10-02');
INSERT INTO `report` VALUES (10,8,'October report',0.00,'3920-11-02');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` char(50) DEFAULT NULL,
  `lastname` char(50) DEFAULT NULL,
  `email` char(50) NOT NULL,
  `phone_number` char(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,'Alex','Naumenko','naum@gmail.com','239482340');
INSERT INTO `user` VALUES (7,'Jhon','Smith','smithj@gmail.com','34903284');
INSERT INTO `user` VALUES (8,'Kate','Lkky','lkku@gmail.com','923084324');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
