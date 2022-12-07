/*DDL 信息*/
/*创建用户角色表*/
CREATE TABLE `tree_user_role` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
                                  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_user_id` (`user_id`) USING BTREE,
                                  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表'
