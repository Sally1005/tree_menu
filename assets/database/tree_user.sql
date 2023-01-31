
-- ----------------------------
-- Table structure for tree_user
-- ----------------------------
DROP TABLE IF EXISTS `tree_user`;
CREATE TABLE `tree_user` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                             `nick_name` varchar(150) DEFAULT NULL COMMENT '昵称',
                             `password` char(64) DEFAULT NULL COMMENT '密码（密文）',
                             `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
                             `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
                             `email` VARCHAR(50) DEFAULT NULL COMMENT '电子邮箱',
                             `status` TINYINT UNSIGNED DEFAULT 0 COMMENT '用户状态，1=禁用，0=正常',
                             `gmt_create` DATETIME DEFAULT NULL COMMENT '数据创建时间',
                             `gmt_modified` DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
                             `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户管理';


-- ----------------------------
-- Records of tree_user
-- ----------------------------
INSERT INTO tree_user (id,username, nick_name,password, avatar,phone, email,status, gmt_create, gmt_modified, is_delete) VALUES
                      ('1', 'root','超级管理员', '1234', null, '13612345671','root@qq.com', '0','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0'),
                      ('2', 'year','年度管理员', '1234', null, '13612345672','year@qq.com', '0','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0'),
                      ('3', 'quarter','季度管理员', '1234', null, '13612345673','quarter@qq.com', '0','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0'),
                      ('4', 'month', '月度管理员','1234', null, '13612345674','month@qq.com', '0','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0');






