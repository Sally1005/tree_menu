
-- ----------------------------
-- Table structure for tree_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `tree_menu_role`;
CREATE TABLE `tree_menu_role` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
                                  `menu_id` bigint(20) unsigned DEFAULT NULL COMMENT '菜单id',
                                  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
                                  `gmt_create` DATETIME DEFAULT NULL COMMENT '数据创建时间',
                                  `gmt_modified` DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单角色';

-- ----------------------------
-- Records of tree_menu_role
-- ----------------------------
INSERT INTO tree_menu_role(id, menu_id, role_id) VALUES
                          ('1','1','1'),
                          ('2','2','2'),
                          ('3','3','3');

