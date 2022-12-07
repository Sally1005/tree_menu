/*DDL 信息*/
/*创建用户表*/
CREATE TABLE `tree_user` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                             `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                             `password` char(64) DEFAULT NULL COMMENT '密码（密文）',
                             `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
                             PRIMARY KEY (`id`),
                             KEY `idx_phone` (`phone`) USING BTREE,
                             KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表'
