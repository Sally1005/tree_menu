/*DDL 信息*/
/*创建角色表*/
CREATE TABLE `tree_role` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                             `name` varchar(50) DEFAULT NULL COMMENT '名称',
                             `code` varchar(255) DEFAULT NULL COMMENT '编码',
                             PRIMARY KEY (`id`),
                             KEY `idx_code` (`code`) USING BTREE,
                             KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色表'
