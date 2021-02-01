构建数据库

CREATE TABLE `detail` (
  `room_id` int(11) NOT NULL,
  `electric` double DEFAULT NULL COMMENT '电',
  `water` double DEFAULT NULL COMMENT '水',
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `total_price` double NOT NULL COMMENT '是否已缴费',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `room` (
  `room_num` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;