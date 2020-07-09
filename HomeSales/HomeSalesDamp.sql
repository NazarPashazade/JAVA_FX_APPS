CREATE DATABASE  IF NOT EXISTS `home_sales` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `home_sales`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: home_sales
-- ------------------------------------------------------
-- Server version	5.5.37

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
-- Table structure for table `advertising`
--

DROP TABLE IF EXISTS `advertising`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertising` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Phone` varchar(70) NOT NULL,
  `İnfo` text,
  `Reg_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dispatcher`
--

DROP TABLE IF EXISTS `dispatcher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispatcher` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Number` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `door`
--

DROP TABLE IF EXISTS `door`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `door` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `floor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home`
--

DROP TABLE IF EXISTS `home`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `area` text NOT NULL,
  `info` text,
  `Host_id` int(11) DEFAULT NULL,
  `Address` text NOT NULL,
  `Price` double NOT NULL,
  `repair_id` int(11) NOT NULL,
  `ownership_id` int(11) DEFAULT NULL,
  `room_count_id` int(11) DEFAULT NULL,
  `Sanitary_node_id` int(11) DEFAULT NULL,
  `Advert_id` int(11) DEFAULT NULL,
  `door_id` int(11) DEFAULT NULL,
  `Floor_id` int(11) DEFAULT NULL,
  `Material_id` int(11) DEFAULT NULL,
  `Project_id` int(11) DEFAULT NULL,
  `Region_id` int(11) DEFAULT NULL,
  `Froze` int(11) DEFAULT '0',
  `Bought` int(11) DEFAULT '0',
  `Reg_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` int(11) DEFAULT '1',
  `Which_Floor` int(11) DEFAULT NULL,
  `Pow_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Home_host__id_idx` (`Host_id`),
  KEY `Home_repair_id_idx` (`repair_id`),
  KEY `Home_ownership_id_idx` (`ownership_id`),
  KEY `Home_room_count_id_idx` (`room_count_id`),
  KEY `Home_Region_id_idx` (`Region_id`),
  KEY `Home_Sanitary_node_id_idx` (`Sanitary_node_id`),
  KEY `Home_Project_id_idx` (`Project_id`),
  KEY `Home_Material_id_idx` (`Material_id`),
  KEY `Advert_id_idx` (`Advert_id`),
  KEY `Home_door_id_idx` (`door_id`),
  KEY `Home_Floor_id_idx` (`Floor_id`),
  KEY `Home_Pow_id_idx` (`Pow_Id`),
  CONSTRAINT `Home_Advert_id` FOREIGN KEY (`Advert_id`) REFERENCES `advertising` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_door_id` FOREIGN KEY (`door_id`) REFERENCES `door` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_Floor_id` FOREIGN KEY (`Floor_id`) REFERENCES `floor` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_host__id` FOREIGN KEY (`Host_id`) REFERENCES `host` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_Material_id` FOREIGN KEY (`Material_id`) REFERENCES `material` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_ownership_id` FOREIGN KEY (`ownership_id`) REFERENCES `ownership` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_Pow_id` FOREIGN KEY (`Pow_Id`) REFERENCES `pow` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_Project_id` FOREIGN KEY (`Project_id`) REFERENCES `project` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_Region_id` FOREIGN KEY (`Region_id`) REFERENCES `region` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_repair_id` FOREIGN KEY (`repair_id`) REFERENCES `repair` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_room_count_id` FOREIGN KEY (`room_count_id`) REFERENCES `room_count` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Home_Sanitary_node_id` FOREIGN KEY (`Sanitary_node_id`) REFERENCES `sanitary_node` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `host`
--

DROP TABLE IF EXISTS `host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `host` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `Surname` varchar(30) DEFAULT NULL,
  `phone` text,
  `Address` text,
  `Reg_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ownership`
--

DROP TABLE IF EXISTS `ownership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ownership` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Home_id` int(11) DEFAULT NULL,
  `Customer_id` int(11) DEFAULT NULL,
  `Host_id` int(11) DEFAULT NULL,
  `Sale_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`),
  KEY `Payment_Home_id_idx` (`Home_id`),
  KEY `Payment_Customer_id_idx` (`Customer_id`),
  KEY `Peyment_Host_id_idx` (`Host_id`),
  CONSTRAINT `Payment_Customer_id` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Payment_Home_id` FOREIGN KEY (`Home_id`) REFERENCES `home` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Peyment_Host_id` FOREIGN KEY (`Host_id`) REFERENCES `host` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pow`
--

DROP TABLE IF EXISTS `pow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pow` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `repair`
--

DROP TABLE IF EXISTS `repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repair` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room_count`
--

DROP TABLE IF EXISTS `room_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_count` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sanitary_node`
--

DROP TABLE IF EXISTS `sanitary_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sanitary_node` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tttttt`
--

DROP TABLE IF EXISTS `tttttt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tttttt` (
  `id1` int(11) NOT NULL AUTO_INCREMENT,
  `id2` varchar(45) NOT NULL,
  PRIMARY KEY (`id1`,`id2`),
  UNIQUE KEY `id1_UNIQUE` (`id1`),
  UNIQUE KEY `id2_UNIQUE` (`id2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Father_Name` varchar(30) NOT NULL,
  `Reg_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(20000) NOT NULL,
  `Phone` varchar(30) NOT NULL,
  `Active` int(11) DEFAULT '1',
  `role` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_rule`
--

DROP TABLE IF EXISTS `user_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rule` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Owerneship_id` int(11) DEFAULT NULL,
  `Active` int(11) DEFAULT '1',
  `Reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  `regionId` int(11) DEFAULT NULL,
  `Status_Rent` int(11) DEFAULT NULL,
  `Status_Sale` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `User_rule_Owerneship_id_idx` (`Owerneship_id`),
  KEY `User_rule_User_İd_idx` (`userId`),
  KEY `User_rule_Region_id_idx` (`regionId`),
  CONSTRAINT `User_rule_Owerneship_id` FOREIGN KEY (`Owerneship_id`) REFERENCES `ownership` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `User_rule_Region_id` FOREIGN KEY (`regionId`) REFERENCES `region` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `User_rule_User_İd` FOREIGN KEY (`userId`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `viewed_home`
--

DROP TABLE IF EXISTS `viewed_home`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `viewed_home` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_İd` int(11) DEFAULT NULL,
  `Customer_id` int(11) DEFAULT NULL,
  `Reg_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` int(11) DEFAULT '1',
  `homeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Viewwed_Home_User_İd_idx` (`User_İd`),
  KEY `Viewwed_Customer_id_idx` (`Customer_id`),
  KEY `Viewwed_Home_Home_İd_idx` (`homeId`),
  CONSTRAINT `Viewwed_Customer_id` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Viewwed_Home_Home_İd` FOREIGN KEY (`homeId`) REFERENCES `home` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Viewwed_Home_User_İd` FOREIGN KEY (`User_İd`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `viewed_user`
--

DROP TABLE IF EXISTS `viewed_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `viewed_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_İd` int(11) DEFAULT NULL,
  `Home_id` int(11) DEFAULT NULL,
  `Reg_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`),
  KEY `Wiewed_Users_User_İd_idx` (`User_İd`),
  KEY `Wiewed_Users_Home_id_idx` (`Home_id`),
  CONSTRAINT `Wiewed_Users_Home_id` FOREIGN KEY (`Home_id`) REFERENCES `home` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Wiewed_Users_User_İd` FOREIGN KEY (`User_İd`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-28 20:10:05
