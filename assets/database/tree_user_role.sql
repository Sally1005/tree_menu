-- ----------------------------
-- Table structure for tree_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tree_user_role`;
CREATE TABLE `tree_user_role` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
                                  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
                                  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
                                  `gmt_create` DATETIME DEFAULT NULL COMMENT '数据创建时间',
                                  `gmt_modified` DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色';

-- ----------------------------
-- Records of tree_user_role
-- ----------------------------
INSERT INTO tree_user_role(id, user_id, role_id) VALUES
                        ('1','1','1'),
                        ('2','2','2'),
                        ('3','3','3');











