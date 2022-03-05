/*
SQLyog Professional v12.14 (64 bit)
MySQL - 5.6.44 : Database - con
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`con` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `con`;

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pic_url` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `sub_name` varchar(255) DEFAULT NULL,
  `mart_price` decimal(10,2) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1502 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Table structure for table `h_log` */

DROP TABLE IF EXISTS `h_log`;

CREATE TABLE `h_log` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `product_id` varchar(100) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `h_prod` */

DROP TABLE IF EXISTS `h_prod`;

CREATE TABLE `h_prod` (
  `id` varchar(255) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `value` varchar(10) DEFAULT NULL,
  UNIQUE KEY `unique_id_sex_age` (`id`,`sex`,`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `h_union_prod` */

DROP TABLE IF EXISTS `h_union_prod`;

CREATE TABLE `h_union_prod` (
  `id` varchar(255) NOT NULL,
  `product` varchar(200) DEFAULT NULL,
  `union_type` varchar(50) DEFAULT NULL,
  `value` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `h_user` */

DROP TABLE IF EXISTS `h_user`;

CREATE TABLE `h_user` (
  `id` varchar(255) NOT NULL,
  `country` varchar(100) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `style` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL COMMENT '''red'', ''blue'', ''green'', ''grey'', ''black'',''brown''',
  `diameter` varchar(255) DEFAULT NULL COMMENT '8-16',
  `style` varchar(255) DEFAULT NULL COMMENT '0-5',
  `material` varchar(255) DEFAULT NULL COMMENT '0-3',
  `country` varchar(255) DEFAULT NULL COMMENT '''china'',''japan'',''korea''',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Table structure for table `r_top_product` */

DROP TABLE IF EXISTS `r_top_product`;

CREATE TABLE `r_top_product` (
  `product_id` int(11) DEFAULT NULL,
  `action_times` int(11) DEFAULT NULL,
  `window_end` bigint(20) DEFAULT NULL,
  `rank_name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `registertime` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
