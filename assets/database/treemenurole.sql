
/*DDL 信息*/
/*创建菜单角色表*/
CREATE TABLE `tree_menu_role` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                  `menu_id` bigint(20) unsigned DEFAULT NULL COMMENT '菜单id',
                                  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_menu_id` (`menu_id`) USING BTREE,
                                  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单角色关联表'
