-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 3.36.115.130    Database: solomon
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `email_auth`
--

DROP TABLE IF EXISTS `email_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_auth` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `auth_token` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expire_date` datetime(6) DEFAULT NULL,
  `expired` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_auth`
--

LOCK TABLES `email_auth` WRITE;
/*!40000 ALTER TABLE `email_auth` DISABLE KEYS */;
INSERT INTO `email_auth` VALUES (1,'1ab17965-8c57-4513-88bb-5429b4b3d4bb','kiixdox@gmail.com','2022-02-18 09:24:15.383000',_binary ''),(2,'0eaadb19-0351-42b5-813a-79b019e48735','ironylovely@naver.com','2022-02-18 09:25:24.747000',_binary ''),(3,'58be37e7-1a1d-457b-853d-1f1af6ad298f','tjswn0425@naver.com','2022-02-18 09:28:36.877000',_binary ''),(4,'46acd62e-fb28-45db-a684-590388118071','ht9943@naver.com','2022-02-18 10:08:13.953000',_binary ''),(5,'4d4df331-ade2-4751-b707-f47c030b3c2f','tlsdk20@naver.com','2022-02-18 10:56:41.295000',_binary ''),(6,'96bc7270-7d34-4ed6-b81e-b67626b74fbe','developer.seonju@gmail.com','2022-02-18 11:04:59.698000',_binary '');
/*!40000 ALTER TABLE `email_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_idx` bigint NOT NULL AUTO_INCREMENT,
  `email_auth` bit(1) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `member_key` varchar(255) DEFAULT NULL,
  `member_pwd` varchar(255) DEFAULT NULL,
  `member_sns` varchar(50) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,_binary '','kiixdox@gmail.com',NULL,'$2a$10$K94PfP/dNl8.kpHl3tDGmu8N3xlceA0ZOHgvKY61WRelqc9a9X16q',NULL,'치즈볼'),(2,_binary '','ironylovely@naver.com',NULL,'$2a$10$S925OsqaqVDiJC/PxL7gHOt2jw/5edSKYxTAXfYkHyOkXrgzAUxVu',NULL,'고누'),(3,_binary '','tjswn0425@naver.com',NULL,'$2a$10$ck5j.7N0x2OMHVRr5xv6NuCDWRszKBtnB7beGRx620cP2KCFCHEn.',NULL,'사오정'),(4,_binary '','ht9943@naver.com',NULL,'$2a$10$fOOj0ds6VMBOOY7TTAYRiekMLl872lZpzeKpNUuwGhy4CwMWCfUx.',NULL,'손오공'),(5,_binary '','tlsdk20@naver.com',NULL,'$2a$10$F8a6M9ZDSp7LKx9jB3D9/ujmS9nhkhauiPNU/Po6t3un5W0JRbx5G',NULL,'띠나'),(6,_binary '','developer.seonju@gmail.com',NULL,'$2a$10$esbU3FJRSAaXKfTv/Cu.Nu4cpWzyn4rV0sGV7PU77tCJzPqutC3iC',NULL,'선주');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `qna_id` bigint NOT NULL AUTO_INCREMENT,
  `qna_answer` varchar(255) DEFAULT NULL,
  `qna_content` varchar(255) DEFAULT NULL,
  `qnas_id_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`qna_id`),
  KEY `FK7eeyl85we5eyacd83xpjtneul` (`qnas_id_fk`),
  CONSTRAINT `FK7eeyl85we5eyacd83xpjtneul` FOREIGN KEY (`qnas_id_fk`) REFERENCES `qnas` (`qnas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (1,'솔로몬접','안녕하세요 ?',1),(2,'질문을 작성해주세요','답변을 작성해주세요',1),(3,'질문을 작성해주세요','답변을 작성해주세요',1),(4,'자기소개를 해주세요','안녕하세요 저는 노건우 입니다',3),(5,'가장 슬펐던 일은 무엇인가요?','',3),(6,'자기소개를 해주세요','답변을 작성해주세요',5),(7,'하이하이요','답변을 작성해주세요',5),(11,'스트레스를 어떻게 푸시나요?','맛집 탐방하러 갑니다!',10),(12,'질문을 작성해주세요','답변을 작성해주세요',10),(13,'자기소개를 해주세요','답변을 작성해주세요',13),(14,'회사에 지원하게된 계기는 무엇인가요?','답변을 작성해주세요',13);
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qnas`
--

DROP TABLE IF EXISTS `qnas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qnas` (
  `qnas_id` bigint NOT NULL AUTO_INCREMENT,
  `qnas_title` varchar(255) DEFAULT NULL,
  `member_idfk` bigint DEFAULT NULL,
  PRIMARY KEY (`qnas_id`),
  KEY `FK1ree0oexwsbjwj773v1a8qado` (`member_idfk`),
  CONSTRAINT `FK1ree0oexwsbjwj773v1a8qado` FOREIGN KEY (`member_idfk`) REFERENCES `member` (`member_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qnas`
--

LOCK TABLES `qnas` WRITE;
/*!40000 ALTER TABLE `qnas` DISABLE KEYS */;
INSERT INTO `qnas` VALUES (1,'면접질문 모음집',1),(2,'제목',1),(3,'삼성면접대비',2),(4,'카카오면접대비',2),(5,'카카오 면접',3),(6,'제목',3),(10,'카카오',5),(11,'쿠팡',5),(12,'카카오',4),(13,'카카오 면접',6),(14,'삼성전자 면접',6);
/*!40000 ALTER TABLE `qnas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 11:06:26
