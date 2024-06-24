-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: j10b304.p.ssafy.io    Database: chukchuk_db
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `ocr_pills`
--

DROP TABLE IF EXISTS `ocr_pills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocr_pills` (
  `ocr_pills_id` bigint NOT NULL AUTO_INCREMENT,
  `ocr_id` bigint DEFAULT NULL,
  `pill_id` bigint DEFAULT NULL,
  `guide` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ocr_pills_id`),
  KEY `FK_ocr_pills_pill_id` (`pill_id`),
  KEY `FK_ocr_pills_ocr_id` (`ocr_id`),
  CONSTRAINT `FK_ocr_pills_ocr_id` FOREIGN KEY (`ocr_id`) REFERENCES `ocr_list` (`ocr_id`),
  CONSTRAINT `FK_ocr_pills_pill_id` FOREIGN KEY (`pill_id`) REFERENCES `pill` (`pill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocr_pills`
--

LOCK TABLES `ocr_pills` WRITE;
/*!40000 ALTER TABLE `ocr_pills` DISABLE KEYS */;
INSERT INTO `ocr_pills` VALUES (13,16,19798,'0.5씩1의60일분','2024-04-03 22:06:15',NULL,0),(14,16,19798,'0.5씩1회1일분','2024-04-03 22:06:15',NULL,0),(15,17,19798,'0.5씩1의60일분','2024-04-04 00:12:46',NULL,0),(16,18,19798,'0.5씩1의60일분','2024-04-04 00:12:46',NULL,0),(17,18,22530,'0.5씩1회1일분','2024-04-04 00:12:46',NULL,0),(18,17,22530,'0.5씩1회1일분','2024-04-04 00:12:46',NULL,0),(19,19,19798,'0.5씩1의60일분','2024-04-04 00:24:38',NULL,0),(20,19,22530,'0.5씩1회1일분','2024-04-04 00:24:38',NULL,0),(21,20,19798,'0.5씩1의60일분','2024-04-04 00:58:09',NULL,0),(22,20,22530,'0.5씩1회1일분','2024-04-04 00:58:09',NULL,0);
/*!40000 ALTER TABLE `ocr_pills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-04 13:15:22
