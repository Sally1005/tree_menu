-- ----------------------------
-- Table structure for tree_role
-- ----------------------------
DROP TABLE IF EXISTS `tree_role`;
CREATE TABLE `tree_role` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT  COMMENT '编号',
                             `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
                             `gmt_create` DATETIME DEFAULT NULL COMMENT '数据创建时间',
                             `gmt_modified` DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
                             `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色管理';

-- ----------------------------
-- Records of tree_role
-- ----------------------------
INSERT INTO `tree_role`(id, name, gmt_create, gmt_modified, is_delete) VALUES
                    ('1','超级管理员','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0'),
                    ('2','年度管理员','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0'),
                    ('3','季度管理员','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0'),
                    ('4','月度管理员','2022-08-14 11:11:11',  '2022-08-14 11:11:11', '0');
