构建数据库
CREATE TABLE `info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `year` int(4) DEFAULT NULL COMMENT '年份',
  `month` int(2) DEFAULT NULL COMMENT '月份',
  `room_number` int(3) DEFAULT NULL COMMENT '房间号',
  `build_type` int(1) NOT NULL DEFAULT '1' COMMENT '建筑类型（默认1 代表我住的那一栋）',
  `electric` int(20) DEFAULT NULL COMMENT '电表数',
  `water` int(20) DEFAULT NULL COMMENT '水表数',
  `create_time` datetime DEFAULT NULL COMMENT '录入时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;