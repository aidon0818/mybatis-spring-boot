/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17-log : Database - wechatorder
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wechatorder` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `wechatorder`;

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `countryname` varchar(255) DEFAULT NULL COMMENT '名称',
  `countrycode` varchar(255) DEFAULT NULL COMMENT '代码',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8 COMMENT='国家信息';

/*Data for the table `country` */

insert  into `country`(`Id`,`countryname`,`countrycode`) values (1,'Angola','AO'),(2,'Afghanistan','AF'),(3,'Albania','AL'),(4,'Algeria','DZ'),(5,'Andorra','AD'),(6,'Anguilla','AI'),(7,'Antigua and Barbuda','AG'),(8,'Argentina','AR'),(9,'Armenia','AM'),(10,'Australia','AU'),(11,'Austria','AT'),(12,'Azerbaijan','AZ'),(13,'Bahamas','BS'),(14,'Bahrain','BH'),(15,'Bangladesh','BD'),(16,'Barbados','BB'),(17,'Belarus','BY'),(18,'Belgium','BE'),(19,'Belize','BZ'),(20,'Benin','BJ'),(21,'Bermuda Is.','BM'),(22,'Bolivia','BO'),(23,'Botswana','BW'),(24,'Brazil','BR'),(25,'Brunei','BN'),(26,'Bulgaria','BG'),(27,'Burkina-faso','BF'),(28,'Burma','MM'),(29,'Burundi','BI'),(30,'Cameroon','CM'),(31,'Canada','CA'),(32,'Central African Republic','CF'),(33,'Chad','TD'),(34,'Chile','CL'),(35,'China','CN'),(36,'Colombia','CO'),(37,'Congo','CG'),(38,'Cook Is.','CK'),(39,'Costa Rica','CR'),(40,'Cuba','CU'),(41,'Cyprus','CY'),(42,'Czech Republic','CZ'),(43,'Denmark','DK'),(44,'Djibouti','DJ'),(45,'Dominica Rep.','DO'),(46,'Ecuador','EC'),(47,'Egypt','EG'),(48,'EI Salvador','SV'),(49,'Estonia','EE'),(50,'Ethiopia','ET'),(51,'Fiji','FJ'),(52,'Finland','FI'),(53,'France','FR'),(54,'French Guiana','GF'),(55,'Gabon','GA'),(56,'Gambia','GM'),(57,'Georgia','GE'),(58,'Germany','DE'),(59,'Ghana','GH'),(60,'Gibraltar','GI'),(61,'Greece','GR'),(62,'Grenada','GD'),(63,'Guam','GU'),(64,'Guatemala','GT'),(65,'Guinea','GN'),(66,'Guyana','GY'),(67,'Haiti','HT'),(68,'Honduras','HN'),(69,'Hongkong','HK'),(70,'Hungary','HU'),(71,'Iceland','IS'),(72,'India','IN'),(73,'Indonesia','ID'),(74,'Iran','IR'),(75,'Iraq','IQ'),(76,'Ireland','IE'),(77,'Israel','IL'),(78,'Italy','IT'),(79,'Jamaica','JM'),(80,'Japan','JP'),(81,'Jordan','JO'),(82,'Kampuchea (Cambodia )','KH'),(83,'Kazakstan','KZ'),(84,'Kenya','KE'),(85,'Korea','KR'),(86,'Kuwait','KW'),(87,'Kyrgyzstan','KG'),(88,'Laos','LA'),(89,'Latvia','LV'),(90,'Lebanon','LB'),(91,'Lesotho','LS'),(92,'Liberia','LR'),(93,'Libya','LY'),(94,'Liechtenstein','LI'),(95,'Lithuania','LT'),(96,'Luxembourg','LU'),(97,'Macao','MO'),(98,'Madagascar','MG'),(99,'Malawi','MW'),(100,'Malaysia','MY'),(101,'Maldives','MV'),(102,'Mali','ML'),(103,'Malta','MT'),(104,'Mauritius','MU'),(105,'Mexico','MX'),(106,'Moldova, Republic of','MD'),(107,'Monaco','MC'),(108,'Mongolia','MN'),(109,'Montserrat Is','MS'),(110,'Morocco','MA'),(111,'Mozambique','MZ'),(112,'Namibia','NA'),(113,'Nauru','NR'),(114,'Nepal','NP'),(115,'Netherlands','NL'),(116,'New Zealand','NZ'),(117,'Nicaragua','NI'),(118,'Niger','NE'),(119,'Nigeria','NG'),(120,'North Korea','KP'),(121,'Norway','NO'),(122,'Oman','OM'),(123,'Pakistan','PK'),(124,'Panama','PA'),(125,'Papua New Cuinea','PG'),(126,'Paraguay','PY'),(127,'Peru','PE'),(128,'Philippines','PH'),(129,'Poland','PL'),(130,'French Polynesia','PF'),(131,'Portugal','PT'),(132,'Puerto Rico','PR'),(133,'Qatar','QA'),(134,'Romania','RO'),(135,'Russia','RU'),(136,'Saint Lueia','LC'),(137,'Saint Vincent','VC'),(138,'San Marino','SM'),(139,'Sao Tome and Principe','ST'),(140,'Saudi Arabia','SA'),(141,'Senegal','SN'),(142,'Seychelles','SC'),(143,'Sierra Leone','SL'),(144,'Singapore','SG'),(145,'Slovakia','SK'),(146,'Slovenia','SI'),(147,'Solomon Is','SB'),(148,'Somali','SO'),(149,'South Africa','ZA'),(150,'Spain','ES'),(151,'Sri Lanka','LK'),(152,'St.Lucia','LC'),(153,'St.Vincent','VC'),(154,'Sudan','SD'),(155,'Suriname','SR'),(156,'Swaziland','SZ'),(157,'Sweden','SE'),(158,'Switzerland','CH'),(159,'Syria','SY'),(160,'Taiwan','TW'),(161,'Tajikstan','TJ'),(162,'Tanzania','TZ'),(163,'Thailand','TH'),(164,'Togo','TG'),(165,'Tonga','TO'),(166,'Trinidad and Tobago','TT'),(167,'Tunisia','TN'),(168,'Turkey','TR'),(169,'Turkmenistan','TM'),(170,'Uganda','UG'),(171,'Ukraine','UA'),(172,'United Arab Emirates','AE'),(173,'United Kiongdom','GB'),(174,'United States of America','US'),(175,'Uruguay','UY'),(176,'Uzbekistan','UZ'),(177,'Venezuela','VE'),(178,'Vietnam','VN'),(179,'Yemen','YE'),(180,'Yugoslavia','YU'),(181,'Zimbabwe','ZW'),(182,'Zaire','ZR'),(183,'Zambia','ZM'),(184,'88','88');

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `order_detail` */

insert  into `order_detail`(`detail_id`,`order_id`,`product_id`,`product_name`,`product_price`,`product_quantity`,`product_icon`,`create_time`,`update_time`) values ('1234567810','1234567','11111112','皮蛋粥','2.20',3,'http://xxxx.jpg','2017-09-14 14:42:48','2017-09-14 14:42:48'),('1234567890','1234567','123456','酸菜','5.00',5,NULL,'2017-09-15 14:25:48','2017-09-15 14:25:48'),('1505457021639441388','1505456980775200744','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1505457042179636748','1505456980775200744','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506587937738381299','1506587937313296063','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506587937770499199','1506587937313296063','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506588021985999626','1506588021594521713','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506588022016413730','1506588021594521713','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506588033919220049','1506588033516998595','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506588033959138760','1506588033516998595','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506588129465360732','1506588129062705137','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506588129496605302','1506588129062705137','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506588195893808672','1506588195456969504','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506588197759474182','1506588195456969504','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506588208987537407','1506588208553449033','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506588210371551645','1506588208553449033','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506590988616566684','1506590988210736285','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506590988662971146','1506590988210736285','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506590997495799253','1506590997105170239','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506590997541683899','1506590997105170239','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506591006965505284','1506591006575706108','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506591007012130511','1506591006575706108','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506591015880459710','1506591015396626657','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506591015911157453','1506591015396626657','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506591025532162135','1506591024802814886','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506591025593413172','1506591024802814886','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('1506591032148961738','1506591031774613072','19163d930d2141c189483932da64543b','冰糖雪梨','12.00',1,NULL,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('1506591032179333096','1506591031774613072','2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',2,NULL,'2017-09-11 17:02:33','2017-09-11 17:02:33');

/*Table structure for table `order_master` */

DROP TABLE IF EXISTS `order_master`;

CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `order_master` */

insert  into `order_master`(`order_id`,`buyer_name`,`buyer_phone`,`buyer_address`,`buyer_openid`,`order_amount`,`order_status`,`pay_status`,`create_time`,`update_time`) values ('1234567','师兄','123456789123','幕课网','110110','2.50',0,0,'2017-09-13 15:42:06','2017-09-13 15:42:06'),('1505456980775200744','廖师兄','123456789012','幕课网','1101110','18.00',0,0,'2017-09-15 14:31:29','2017-09-15 14:31:29'),('1506587937313296063','Aidon','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 16:38:57','2017-09-28 16:38:57'),('1506588021594521713','Annie','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 16:40:22','2017-09-28 16:40:22'),('1506588033516998595','Tom','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 16:40:33','2017-09-28 16:40:33'),('1506588129062705137','Jack','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 16:42:09','2017-09-28 16:42:09'),('1506588195456969504','Nike','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 16:43:17','2017-09-28 16:43:17'),('1506588208553449033','Nike','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 16:43:30','2017-09-28 16:43:30'),('1506590988210736285','IO','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 17:29:48','2017-09-28 17:29:48'),('1506590997105170239','ABC','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 17:29:57','2017-09-28 17:29:57'),('1506591006575706108','BD','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 17:30:07','2017-09-28 17:30:07'),('1506591015396626657','JAVA','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 17:30:15','2017-09-28 17:30:15'),('1506591024802814886','ORACLE','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 17:30:25','2017-09-28 17:30:25'),('1506591031774613072','MYSQL','123456789012','深圳','1101110','18.00',0,0,'2017-09-28 17:30:32','2017-09-28 17:30:32');

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `product_category` */

insert  into `product_category`(`category_id`,`category_name`,`category_type`,`create_time`,`update_time`) values (2,'女生菜谱',0,'2017-09-11 11:06:41','2017-09-11 11:06:41'),(4,'男生菜谱',1,'2017-09-11 11:09:44','2017-09-11 11:09:44'),(5,'测试',2,'2017-09-27 17:16:45','2017-09-27 17:16:45'),(6,'测试2',3,'2017-09-27 17:16:52','2017-09-27 17:16:52'),(7,'测试3',4,'2017-09-27 17:17:01','2017-09-27 17:17:01'),(8,'测试4',5,'2017-09-27 17:17:11','2017-09-27 17:17:11');

/*Table structure for table `product_info` */

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `product_info` */

insert  into `product_info`(`product_id`,`product_name`,`product_price`,`product_stock`,`product_description`,`product_icon`,`product_status`,`category_type`,`create_time`,`update_time`) values ('19163d930d2141c189483932da64543b','冰糖雪梨','12.00',0,NULL,NULL,NULL,0,'2017-09-13 11:32:23','2017-09-13 11:32:54'),('2d2f9eec78304134bb2e07b40c7df0e7','烧烤','3.00',0,NULL,NULL,NULL,1,'2017-09-11 17:02:33','2017-09-11 17:02:33'),('53b1cbcea5a34ec4ae742a760d8a4ed3','菠萝蜜','25.00',10,NULL,NULL,NULL,0,'2017-09-13 11:35:14','2017-09-13 11:36:03');

/*Table structure for table `seller_info` */

DROP TABLE IF EXISTS `seller_info`;

CREATE TABLE `seller_info` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卖家信息表';

/*Data for the table `seller_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
