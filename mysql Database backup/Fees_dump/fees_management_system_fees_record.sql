-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: fees_management_system
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `fees_record`
--

DROP TABLE IF EXISTS `fees_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fees_record` (
  `Receipt_No` int NOT NULL DEFAULT '0',
  `Date` date DEFAULT NULL,
  `Student_Name` varchar(100) DEFAULT 'NULL',
  `Enrollment_No` varchar(100) NOT NULL,
  `Course` varchar(500) DEFAULT NULL,
  `Course_Year` varchar(500) DEFAULT NULL,
  `Payment_Mode` varchar(100) DEFAULT 'NULL',
  `Transaction_ID` varchar(500) DEFAULT 'NULL',
  `Card_No` varchar(500) DEFAULT 'NULL',
  `Cheque_No` varchar(500) DEFAULT 'NULL',
  `Bank_Name` varchar(500) DEFAULT 'NULL',
  `Received_From` varchar(500) DEFAULT 'NULL',
  `Year1` int DEFAULT NULL,
  `Year2` int DEFAULT NULL,
  `Amount` bigint DEFAULT NULL,
  `Total_Amount` bigint DEFAULT NULL,
  `GST` int DEFAULT NULL,
  `Total_In_Words` varchar(500) DEFAULT 'NULL',
  `Remarks` varchar(1000) DEFAULT 'NULL',
  PRIMARY KEY (`Receipt_No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fees_record`
--

LOCK TABLES `fees_record` WRITE;
/*!40000 ALTER TABLE `fees_record` DISABLE KEYS */;
INSERT INTO `fees_record` VALUES (1,'2022-03-10','Devesh','SYBCA100','BSC-IT','2nd Year','Online/Bank','123456798','','','SBI','devesh',2021,2022,47000,55460,8460,'Twelve Thousand Nine Hundred Eighty Only','2nd Installment'),(2,'2022-03-09','Aniket','SYBCA101','BCA','2nd Year','Cash','','','','','Aniket',2022,2023,19000,22420,3420,'Twenty Nine Thousand Five Hundred Only','Second installation\n'),(3,'2022-03-10','Prem','SYBCA106','BAF','2nd Year','Cash','','','','','Prem',2021,2022,10000,11800,1800,'Eleven Thousand Eight Hundred Only','1st Intallment'),(4,'2022-03-10','Roshan','SYBCA103','BBA','2nd Year','Cash','','','','','Roshan',2021,2022,20000,23600,3600,'Twenty Three Thousand Six Hundred Only','1st intallment'),(5,'2022-03-10','Vishal','SYBCA102','MCA','2nd Year','Cash','','','','','vishal',2021,2022,5000,5900,900,'Five Thousand Nine Hundred Only','1st Intallment'),(6,'2022-03-10','Sahil','SYBCA107','MSC-IT','2nd Year','Online/Bank','12345678','','','SBI','sahil',2022,2023,3000,3540,540,'Three Thousand Five Hundred Forty Only','1st Installment'),(7,'2022-03-10','Yash','SYBCA104','BAF','2nd Year','Cash','','','','','Yash',2022,2023,4000,4720,720,'Four Thousand Seven Hundred Twenty Only','1st Installment'),(8,'2022-03-10','Devesh','SYBCA100','BSC-IT','2nd Year','Online/Bank','123456798','','','SBI','devesh',2021,2022,11000,12980,1980,'Twelve Thousand Nine Hundred Eighty Only','2nd Installment');
/*!40000 ALTER TABLE `fees_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-07 18:28:20
