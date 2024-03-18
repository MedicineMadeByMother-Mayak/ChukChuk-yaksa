CREATE DATABASE  IF NOT EXISTS `chukchuk_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chukchuk_db`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: chukchuk_db
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `ocr_list`
--

DROP TABLE IF EXISTS `ocr_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocr_list` (
  `ocr_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `ocr_type` enum('DIAGNOSIS','PILLBAG') NOT NULL,
  `build_date` datetime DEFAULT NULL,
  `ill_code` varchar(50) DEFAULT NULL,
  `ill_name` varchar(50) DEFAULT NULL,
  `hospital_name` varchar(50) DEFAULT NULL,
  `diagnosis_date` datetime DEFAULT NULL,
  `opinion` varchar(1000) DEFAULT NULL,
  `pharmacy_name` varchar(50) DEFAULT NULL,
  `cost` int DEFAULT '0',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ocr_id`),
  KEY `FK_ocr_list_user_id` (`user_id`),
  CONSTRAINT `FK_ocr_list_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocr_list`
--

LOCK TABLES `ocr_list` WRITE;
/*!40000 ALTER TABLE `ocr_list` DISABLE KEYS */;
INSERT INTO `ocr_list` VALUES (1,1,'DIAGNOSIS','2024-03-18 14:38:29','123456','Headache','Hospital A','2024-03-18 14:38:29','No specific opinion','Pharmacy A',10000,'2024-03-18 14:38:29','2024-03-18 14:38:29',0),(2,2,'DIAGNOSIS','2024-03-18 14:38:29','789012','Stomachache','Hospital B','2024-03-18 14:38:29','Needs further examination','Pharmacy B',15000,'2024-03-18 14:38:29','2024-03-18 14:38:29',0);
/*!40000 ALTER TABLE `ocr_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-18 14:45:57
