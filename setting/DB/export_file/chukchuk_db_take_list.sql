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
-- Table structure for table `take_list`
--

DROP TABLE IF EXISTS `take_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `take_list` (
  `take_list_id` bigint NOT NULL AUTO_INCREMENT,
  `take_list_name` varchar(20) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `is_alarm` tinyint(1) DEFAULT NULL,
  `alarm_time` datetime DEFAULT NULL,
  `cycle` int NOT NULL DEFAULT '24',
  `is_finish` tinyint(1) DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`take_list_id`),
  KEY `FK_take_list_user_id` (`user_id`),
  CONSTRAINT `FK_take_list_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `take_list`
--

LOCK TABLES `take_list` WRITE;
/*!40000 ALTER TABLE `take_list` DISABLE KEYS */;
INSERT INTO `take_list` VALUES (1,'Morning Pills',1,1,'2024-03-15 08:00:00',24,0,NULL,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(2,'Afternoon Pills',2,1,'2024-03-15 14:00:00',24,0,NULL,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(3,'Evening Pills',3,1,'2024-03-15 20:00:00',24,0,NULL,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(4,'Before Bed Pills',4,1,'2024-03-15 22:00:00',24,0,NULL,'2024-03-19 15:40:41','2024-03-19 15:40:41',0),(5,'Emergency Pills',5,0,NULL,0,0,NULL,'2024-03-19 15:40:41','2024-03-19 15:40:41',0);
/*!40000 ALTER TABLE `take_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-19 15:46:30
