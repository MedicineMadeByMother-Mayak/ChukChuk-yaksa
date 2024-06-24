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
-- Table structure for table `take_pills`
--

DROP TABLE IF EXISTS `take_pills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `take_pills` (
  `take_pills_id` bigint NOT NULL AUTO_INCREMENT,
  `pill_id` bigint DEFAULT NULL,
  `take_list_id` bigint DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`take_pills_id`),
  KEY `FK_take_pills_pill_id` (`pill_id`),
  KEY `FK_take_pills_take_list_id` (`take_list_id`),
  CONSTRAINT `FK_take_pills_pill_id` FOREIGN KEY (`pill_id`) REFERENCES `pill` (`pill_id`),
  CONSTRAINT `FK_take_pills_take_list_id` FOREIGN KEY (`take_list_id`) REFERENCES `take_list` (`take_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `take_pills`
--

LOCK TABLES `take_pills` WRITE;
/*!40000 ALTER TABLE `take_pills` DISABLE KEYS */;
INSERT INTO `take_pills` VALUES (1,1,1,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(2,2,2,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(3,1,3,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(4,2,4,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(5,1,5,'2024-03-19 15:40:41','2024-03-19 15:40:41',0);
/*!40000 ALTER TABLE `take_pills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-19 15:46:46
