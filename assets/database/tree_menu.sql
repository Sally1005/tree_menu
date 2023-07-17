/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 100307 (10.3.7-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : tree

 Target Server Type    : MySQL
 Target Server Version : 100307 (10.3.7-MariaDB)
 File Encoding         : 65001

 Date: 10/07/2023 14:33:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tree_dynamic_menu(动态菜单表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_dynamic_menu`;
CREATE TABLE `tree_dynamic_menu`  (
                                      `menu_id` bigint NOT NULL COMMENT '动态菜单id',
                                      `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
                                      `parent_menu_id` bigint NULL DEFAULT NULL COMMENT '当前菜单的父菜单ID',
                                      `menu_level` bigint NULL DEFAULT NULL COMMENT '当前菜单的层级',
                                      `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前菜单地址',
                                      `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
                                      `gmt_create` datetime NULL DEFAULT NULL COMMENT '数据创建时间',
                                      `gmt_modified` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据最后修改时间',
                                      `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除  -1：已删除  0：正常',
                                      `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应前端组件',
                                      PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tree_dynamic_menu(动态菜单数据)
-- ----------------------------
INSERT INTO `tree_dynamic_menu` VALUES (1000, '测试父菜单', 0, 1, '/test/parent', NULL, NULL, NULL, 0, NULL);
INSERT INTO `tree_dynamic_menu` VALUES (1001, '测试子菜单', 1000, 2, '/test/child', NULL, NULL, NULL, 0, NULL);
INSERT INTO `tree_dynamic_menu` VALUES (10000, '用户管理', 0, 1, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `tree_dynamic_menu` VALUES (20000, '菜单管理', 0, 1, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `tree_dynamic_menu` VALUES (100001, '添加用户', 10000, 2, '/sys/user/add-new', NULL, NULL, NULL, 0, 'sys-user/UserAddNewView');
INSERT INTO `tree_dynamic_menu` VALUES (100002, '用户列表', 10000, 2, '/sys/user/list', NULL, NULL, NULL, 0, 'sys-user/UserListView');
INSERT INTO `tree_dynamic_menu` VALUES (200001, '月度', 20000, 2, '/sys/menu/month', NULL, NULL, NULL, 0, 'sys-menu/MonthMenuView');
INSERT INTO `tree_dynamic_menu` VALUES (200002, '季度', 20000, 2, '/sys/menu/quarter', NULL, NULL, NULL, 0, 'sys-menu/QuarterMenuView');
INSERT INTO `tree_dynamic_menu` VALUES (200003, '年度', 20000, 2, '/sys/menu/year', NULL, NULL, NULL, 0, 'sys-menu/YearMenuView');

-- ----------------------------
-- Table structure for tree_menu(菜单表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_menu`;
CREATE TABLE `tree_menu`  (
                              `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '当前菜单ID',
                              `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名',
                              `parent_menu_id` bigint(0) NULL DEFAULT NULL COMMENT '当前菜单的父菜单ID',
                              `menu_level` bigint(0) NULL DEFAULT NULL COMMENT '当前菜单的层级',
                              `perms` varchar(6553) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
                              `menu_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                              `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前菜单地址',
                              `type` int(0) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
                              `icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
                              `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
                              `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '数据最后修改时间',
                              `is_delete` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
                              `menu_tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单层级路径',
                              PRIMARY KEY (`menu_id`) USING BTREE,
                              INDEX `tree_menu_idx`(`menu_id`, `menu_level`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree_menu(菜单数据)
-- ----------------------------
INSERT INTO `tree_menu` VALUES (1, '月度', 0, 1, 'tree:menu:view', NULL, '/sys/menu/month', NULL, NULL, NULL, NULL, 0, '-1');
INSERT INTO `tree_menu` VALUES (2, '价格', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2');
INSERT INTO `tree_menu` VALUES (3, '70个大中城市住宅销售价格指数', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-3');
INSERT INTO `tree_menu` VALUES (4, '各地区工业生产者价格指数(上年同月=100)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-4');
INSERT INTO `tree_menu` VALUES (5, '工业生产者出厂价格分类指数', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-5');
INSERT INTO `tree_menu` VALUES (6, '工业生产者购进价格指数', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-6');
INSERT INTO `tree_menu` VALUES (7, '各地居民消费和商品零售价格指数', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-7');
INSERT INTO `tree_menu` VALUES (8, '36大中城市居民消费和商品零售价格指数(2016-)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-8');
INSERT INTO `tree_menu` VALUES (9, '36大中城市居民消费和商品零售价格指数(-2015)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-9');
INSERT INTO `tree_menu` VALUES (10, '各地居民消费价格分类指数(上年同月＝100)(2016-)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-10');
INSERT INTO `tree_menu` VALUES (11, '各地居民消费价格分类指数(上年同月＝100)(-2015)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-11');
INSERT INTO `tree_menu` VALUES (12, '36大中城市居民消费价格分类指数(上年同月＝100)(2016-)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-12');
INSERT INTO `tree_menu` VALUES (13, '36大中城市居民消费价格分类指数(上年同月＝100)(-2015)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-13');
INSERT INTO `tree_menu` VALUES (14, '分城乡居民消费和商品零售价格指数', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-14');
INSERT INTO `tree_menu` VALUES (15, '累计全国及36个大中城市居民消费价格分类指数(上年同期＝100)(2016-)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-15');
INSERT INTO `tree_menu` VALUES (16, '累计全国及36个大中城市居民消费价格分类指数(上年同期＝100)(-2015)', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-2-16');
INSERT INTO `tree_menu` VALUES (17, '工业', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-17');
INSERT INTO `tree_menu` VALUES (18, '工业增加值增长速度', 17, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-17-18');
INSERT INTO `tree_menu` VALUES (19, '工业主要产品产量及增长速度', 17, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-17-19');
INSERT INTO `tree_menu` VALUES (20, '工业分大类行业增加值增长速度', 17, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-17-20');
INSERT INTO `tree_menu` VALUES (21, '各地区工业增加值增长速度', 17, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-17-21');
INSERT INTO `tree_menu` VALUES (22, '分行业主要工业经济指标', 17, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-17-22');
INSERT INTO `tree_menu` VALUES (23, '能源', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-23');
INSERT INTO `tree_menu` VALUES (24, '能源产品产量', 23, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-23-24');
INSERT INTO `tree_menu` VALUES (25, '固定资产投资和房地产', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25');
INSERT INTO `tree_menu` VALUES (26, '固定资产投资（不含农户）情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-26');
INSERT INTO `tree_menu` VALUES (27, '固定资产投资资金来源情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-27');
INSERT INTO `tree_menu` VALUES (28, '各行业固定资产投资(不含农户)情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-28');
INSERT INTO `tree_menu` VALUES (29, '各地区固定资产投资情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-29');
INSERT INTO `tree_menu` VALUES (30, '按登记注册类型分的固定资产投资（不含农户）情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-30');
INSERT INTO `tree_menu` VALUES (31, '各地区固定资产投资构成情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-31');
INSERT INTO `tree_menu` VALUES (32, '各地区固定资产住宅建设情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-32');
INSERT INTO `tree_menu` VALUES (33, '各地区固定资产投资项目情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-33');
INSERT INTO `tree_menu` VALUES (34, '全国房地产开发投资情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-34');
INSERT INTO `tree_menu` VALUES (35, '各地区房地产开发投资情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-35');
INSERT INTO `tree_menu` VALUES (36, '各地区商品房销售面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-36');
INSERT INTO `tree_menu` VALUES (37, '各地区住宅销售面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-37');
INSERT INTO `tree_menu` VALUES (38, '各地区办公楼销售面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-38');
INSERT INTO `tree_menu` VALUES (39, '各地区商业营业用房销售面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-39');
INSERT INTO `tree_menu` VALUES (40, '各地区房地产开发规模与开、竣工面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-40');
INSERT INTO `tree_menu` VALUES (41, '各地区住宅开发规模与开、竣工面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-41');
INSERT INTO `tree_menu` VALUES (42, '各地区房地产开发规模与开、竣工面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-42');
INSERT INTO `tree_menu` VALUES (43, '各地区商业营业用房开发规模与开、竣工面积增长情况', 25, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-25-43');
INSERT INTO `tree_menu` VALUES (44, '运输邮电', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-44');
INSERT INTO `tree_menu` VALUES (45, '全社会客货运输量', 44, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-44-45');
INSERT INTO `tree_menu` VALUES (46, '邮电业务量完成情况', 44, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-44-45');
INSERT INTO `tree_menu` VALUES (47, '国内贸易', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-47');
INSERT INTO `tree_menu` VALUES (48, '全国社会消费品零售总额', 47, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-47-48');
INSERT INTO `tree_menu` VALUES (49, '限额以上企业（单位）商品零售类值', 47, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-47-49');
INSERT INTO `tree_menu` VALUES (50, '国际比较', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-50');
INSERT INTO `tree_menu` VALUES (51, '各月美国居民消费者价格涨跌率、失业率和进出口贸易', 50, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-50-51');
INSERT INTO `tree_menu` VALUES (52, '各月日本居民消费者价格涨跌率、失业率和进出口贸易', 50, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-50-52');
INSERT INTO `tree_menu` VALUES (53, '各月欧元区居民消费者价格涨跌率、失业率和进出口贸易', 50, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-1-50-53');
INSERT INTO `tree_menu` VALUES (54, '季度', 0, 1, 'tree:menu:view', NULL, '/sys/menu/quarter', NULL, NULL, NULL, NULL, 0, '-54');
INSERT INTO `tree_menu` VALUES (55, '就业与工资', 54, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-55');
INSERT INTO `tree_menu` VALUES (56, '分季度城镇登记失业率', 55, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-55-56');
INSERT INTO `tree_menu` VALUES (57, '价格', 54, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-57');
INSERT INTO `tree_menu` VALUES (58, '各地区农产品生产价格指数(上年同期=100)', 57, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-57-58');
INSERT INTO `tree_menu` VALUES (59, '各地区固定资产投资价格指数(上年同期=100)', 57, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-57-59');
INSERT INTO `tree_menu` VALUES (60, '国民经济核算', 54, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60');
INSERT INTO `tree_menu` VALUES (61, '分季国内生产总值(现价)', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-61');
INSERT INTO `tree_menu` VALUES (62, '累计国内生产总值(现价)', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-62');
INSERT INTO `tree_menu` VALUES (63, '分季国内生产总值(不变价)', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-63');
INSERT INTO `tree_menu` VALUES (64, '累计国内生产总值(不变价)', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-64');
INSERT INTO `tree_menu` VALUES (65, '分季国内生产总值指数', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-65');
INSERT INTO `tree_menu` VALUES (66, '累计国内生产总值指数', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-66');
INSERT INTO `tree_menu` VALUES (67, '国内生产总值环比增长速度', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-67');
INSERT INTO `tree_menu` VALUES (68, '累计地区生产总值', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-68');
INSERT INTO `tree_menu` VALUES (69, '累计地区生产总值指数(上年=100)', 60, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-60-69');
INSERT INTO `tree_menu` VALUES (70, '农业', 54, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-70');
INSERT INTO `tree_menu` VALUES (71, '累计农林牧渔业总产值', 70, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-70-71');
INSERT INTO `tree_menu` VALUES (72, '工业', 54, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-72');
INSERT INTO `tree_menu` VALUES (73, '主要工业产品的生产、销售与库存', 72, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-72-73');
INSERT INTO `tree_menu` VALUES (74, '建筑业', 54, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74');
INSERT INTO `tree_menu` VALUES (75, '累计各地区按构成分组的建筑业总产值', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-75');
INSERT INTO `tree_menu` VALUES (76, '累计各地区建筑业总产值和竣工产值', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-76');
INSERT INTO `tree_menu` VALUES (77, '累计各地区建筑业企业签订合同情况', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-77');
INSERT INTO `tree_menu` VALUES (78, '累计各地区建筑业企业房屋建筑施工面积', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-78');
INSERT INTO `tree_menu` VALUES (79, '累计各地区建筑业企业个数、从业人数和劳动生产率', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-79');
INSERT INTO `tree_menu` VALUES (80, '累计各地区建筑业企业房屋竣工面积', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-80');
INSERT INTO `tree_menu` VALUES (81, '累计各地区建筑业企业房屋竣工价值', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-81');
INSERT INTO `tree_menu` VALUES (82, '累计各地区按构成分组的国有及国有控股企业建筑业总产值', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-82');
INSERT INTO `tree_menu` VALUES (83, '累计各地区国有及国有控股企业建筑业总产值和竣工产值', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-83');
INSERT INTO `tree_menu` VALUES (84, '累计各地区国有及国有控股建筑业企业签订合同情况', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-84');
INSERT INTO `tree_menu` VALUES (85, '累计各地区国有及国有控股建筑业企业房屋建筑施工面积', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-85');
INSERT INTO `tree_menu` VALUES (86, '累计各地区国有及国有控股建筑业企业个数、从业人数和劳动生产率', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-86');
INSERT INTO `tree_menu` VALUES (87, '累计各地区国有及国有控股建筑业企业房屋竣工面积', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-87');
INSERT INTO `tree_menu` VALUES (88, '累计各地区国有及国有控股建筑业企业房屋竣工价值', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-88');
INSERT INTO `tree_menu` VALUES (89, '累计各地区建筑业企业总收入', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-89');
INSERT INTO `tree_menu` VALUES (90, '累计各地区建筑业企业利润', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-90');
INSERT INTO `tree_menu` VALUES (91, '累计各地区建筑业企业费用情况', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-91');
INSERT INTO `tree_menu` VALUES (92, '累计各地区国有及国有控股建筑业企业总收入', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-92');
INSERT INTO `tree_menu` VALUES (93, '累计各地区国有及国有控股建筑业企业利润', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-93');
INSERT INTO `tree_menu` VALUES (94, '累计各地区国有及国有控股建筑业企业费用情况', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-94');
INSERT INTO `tree_menu` VALUES (95, '累计各地区建筑业企业资产负债情况', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-95');
INSERT INTO `tree_menu` VALUES (96, '累计各地区国有及国有控股建筑业企业资产负债情况', 74, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-74-96');
INSERT INTO `tree_menu` VALUES (97, '国际比较', 54, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-97');
INSERT INTO `tree_menu` VALUES (98, '主要国家和地区国内生产总值环比增长率', 97, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-97-98');
INSERT INTO `tree_menu` VALUES (99, '主要国家和地区国内生产总值同比增长率', 97, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-54-97-99');
INSERT INTO `tree_menu` VALUES (100, '年度', 0, 1, 'tree:menu:view', NULL, '/sys/menu/year', NULL, NULL, NULL, NULL, 0, '-100');
INSERT INTO `tree_menu` VALUES (101, '各地区行政区划', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-101');
INSERT INTO `tree_menu` VALUES (102, '国民经济核算指标', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-102');
INSERT INTO `tree_menu` VALUES (103, '人口基本情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-103');
INSERT INTO `tree_menu` VALUES (104, '固定资产投资概况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-104');
INSERT INTO `tree_menu` VALUES (105, '社会消费品零售总额', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-105');
INSERT INTO `tree_menu` VALUES (106, '对外贸易和利用外资', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-106');
INSERT INTO `tree_menu` VALUES (107, '国家财政收支', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-107');
INSERT INTO `tree_menu` VALUES (108, '金融机构人民币信贷收支(年底余额)', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-108');
INSERT INTO `tree_menu` VALUES (109, '居民消费价格指数(上年=100)（2016-）', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-109');
INSERT INTO `tree_menu` VALUES (110, '居民消费价格指数(上年=100)（-2015）', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-110');
INSERT INTO `tree_menu` VALUES (111, '商品零售价格指数(上年=100)', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-111');
INSERT INTO `tree_menu` VALUES (112, '农产品生产价格指数(上年＝100)', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-112');
INSERT INTO `tree_menu` VALUES (113, '工业生产者出厂价格指数(上年＝100)', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-113');
INSERT INTO `tree_menu` VALUES (114, '工业生产者购进价格分类指数(上年＝100)', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-114');
INSERT INTO `tree_menu` VALUES (115, '居民人均可支配收入', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-115');
INSERT INTO `tree_menu` VALUES (116, '农业基本情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-116');
INSERT INTO `tree_menu` VALUES (117, '规模以上工业企业工业增加值增长速度', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-117');
INSERT INTO `tree_menu` VALUES (118, '能源生产总量和构成', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-118');
INSERT INTO `tree_menu` VALUES (119, '能源消费总量和构成', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-119');
INSERT INTO `tree_menu` VALUES (120, '建筑业企业概况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-120');
INSERT INTO `tree_menu` VALUES (121, '建筑业企业生产完成情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-121');
INSERT INTO `tree_menu` VALUES (122, '交通运输业基本情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-122');
INSERT INTO `tree_menu` VALUES (123, '邮电业务基本情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-123');
INSERT INTO `tree_menu` VALUES (124, '招生、在校学生、毕业生数', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-124');
INSERT INTO `tree_menu` VALUES (125, '民办教育情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-125');
INSERT INTO `tree_menu` VALUES (126, '科技事业发展情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-126');
INSERT INTO `tree_menu` VALUES (127, '卫生、文化和体育基本情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-127');
INSERT INTO `tree_menu` VALUES (128, '社会服务基本情况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-128');
INSERT INTO `tree_menu` VALUES (129, '环境保护基本概况', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-129');
INSERT INTO `tree_menu` VALUES (130, '香港特别行政区主要社会经济指标', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-130');
INSERT INTO `tree_menu` VALUES (131, '澳门特别行政区主要社会经济指标', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-131');
INSERT INTO `tree_menu` VALUES (132, '台湾省主要社会经济指标', 100, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '-100-132');

-- ----------------------------
-- Table structure for tree_menu_role(菜单角色表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_menu_role`;
CREATE TABLE `tree_menu_role`  (
                                   `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
                                   `menu_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '菜单id',
                                   `role_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
                                   `gmt_create` datetime NULL DEFAULT NULL COMMENT '数据创建时间',
                                   `gmt_modified` datetime NULL DEFAULT NULL COMMENT '数据最后修改时间',
                                   `parent_id` bigint NULL DEFAULT NULL COMMENT '角色父级id',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4200004 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tree_menu_role(菜单角色数据)
-- ----------------------------
INSERT INTO `tree_menu_role` VALUES (1000, 1000, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (1001, 1001, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (10000, 10000, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (20000, 20000, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (100001, 100001, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (100002, 100002, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (200001, 200001, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (200002, 200002, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (200003, 200003, 1, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (210000, 10000, 2, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (220000, 20000, 2, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (310000, 10000, 3, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (320000, 20000, 3, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (410000, 10000, 4, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (420000, 20000, 4, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (2100002, 100002, 2, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (2200003, 200003, 2, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (3100002, 100002, 3, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (3200003, 200002, 3, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (4100002, 100002, 4, NULL, NULL, NULL);
INSERT INTO `tree_menu_role` VALUES (4200003, 200001, 4, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tree_permission(权限表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_permission`;
CREATE TABLE `tree_permission`  (
                                    `id` bigint NOT NULL COMMENT '编号',
                                    `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
                                    `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
                                    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tree_permission(权限数据)
-- ----------------------------
INSERT INTO `tree_permission` VALUES (1, '用户-更新', '/tree/user/update', '更新数据');
INSERT INTO `tree_permission` VALUES (2, '用户-读取', '/tree/user/read', '读取数据');
INSERT INTO `tree_permission` VALUES (3, '用户-删除', '/tree/user/delete', '删除数据');
INSERT INTO `tree_permission` VALUES (4, '用户-读取', '/tree/user/read', '读取数据');

-- ----------------------------
-- Table structure for tree_role(角色表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_role`;
CREATE TABLE `tree_role`  (
                              `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
                              `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
                              `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tree_role(角色数据)
-- ----------------------------
INSERT INTO `tree_role` VALUES (1, 'root', '超级管理员');
INSERT INTO `tree_role` VALUES (2, 'year', '年度管理员');
INSERT INTO `tree_role` VALUES (3, 'quarter', '季度管理员');
INSERT INTO `tree_role` VALUES (4, 'month', '月度管理员');

-- ----------------------------
-- Table structure for tree_role_permission(角色权限表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_role_permission`;
CREATE TABLE `tree_role_permission`  (
                                         `id` bigint NOT NULL COMMENT '编号',
                                         `permission_id` bigint NULL DEFAULT NULL COMMENT '权限id',
                                         `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tree_role_permission(角色权限数据)
-- ----------------------------
INSERT INTO `tree_role_permission` VALUES (1, 1, 1);
INSERT INTO `tree_role_permission` VALUES (2, 2, 1);
INSERT INTO `tree_role_permission` VALUES (3, 3, 1);
INSERT INTO `tree_role_permission` VALUES (22, 2, 2);
INSERT INTO `tree_role_permission` VALUES (23, 2, 3);
INSERT INTO `tree_role_permission` VALUES (24, 2, 4);

-- ----------------------------
-- Table structure for tree_user(用户表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_user`;
CREATE TABLE `tree_user`  (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
                              `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                              `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
                              `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                              `role_id` bigint NULL DEFAULT NULL COMMENT '角色编号',
                              `enable` tinyint NULL DEFAULT NULL COMMENT '是否启用，1=启用，0=未启用',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tree_user(用户数据)
-- ----------------------------
INSERT INTO `tree_user` VALUES (1, 'root', '超级管理员', '$2a$10$mLTqtptnw9RvEIZkJ3ljG.EWsn3dbSpj/z9V4FzJi3WhZ/yuL9WPi', 1, 1);
INSERT INTO `tree_user` VALUES (2, 'year', '年度管理员', '$2a$10$mLTqtptnw9RvEIZkJ3ljG.EWsn3dbSpj/z9V4FzJi3WhZ/yuL9WPi', 2, 1);
INSERT INTO `tree_user` VALUES (3, 'quarter', '季度管理员', '$2a$10$mLTqtptnw9RvEIZkJ3ljG.EWsn3dbSpj/z9V4FzJi3WhZ/yuL9WPi', 3, 1);
INSERT INTO `tree_user` VALUES (4, 'month', '月度管理员', '$2a$10$mLTqtptnw9RvEIZkJ3ljG.EWsn3dbSpj/z9V4FzJi3WhZ/yuL9WPi', 4, 1);
INSERT INTO `tree_user` VALUES (13, '3333', '33333', '$2a$10$RsnTmoLumaoRFzAr8IYB9egsWThaBJ.OyENzwETCd3Lp7cwN1sQYi', NULL, 0);
INSERT INTO `tree_user` VALUES (14, 'sally', '萨利', '$2a$10$wKicaQkfuaoe03YaE3l/se.7/Iwm107G5AHnXhE8xaoa/kpVGGpAC', NULL, NULL);
INSERT INTO `tree_user` VALUES (15, '萌二', '萌小二', '$2a$10$1HBRd0BnisxQaTw4Uo5o8uzAdxtbMWpY6npLxAlxxrhH2ZSFumN5y', NULL, NULL);

-- ----------------------------
-- Table structure for tree_user_role(用户角色表)
-- ----------------------------
DROP TABLE IF EXISTS `tree_user_role`;
CREATE TABLE `tree_user_role`  (
                                   `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
                                   `user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
                                   `role_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tree_user_role(用户角色数据)
-- ----------------------------
INSERT INTO `tree_user_role` VALUES (1, 1, 1);
INSERT INTO `tree_user_role` VALUES (2, 2, 2);
INSERT INTO `tree_user_role` VALUES (3, 3, 3);
INSERT INTO `tree_user_role` VALUES (4, 4, 4);
INSERT INTO `tree_user_role` VALUES (8, 11, 1);
INSERT INTO `tree_user_role` VALUES (10, 13, 1);
INSERT INTO `tree_user_role` VALUES (11, 14, 2);
INSERT INTO `tree_user_role` VALUES (12, 15, 2);
INSERT INTO `tree_user_role` VALUES (13, 15, 3);
INSERT INTO `tree_user_role` VALUES (14, 15, 4);

SET FOREIGN_KEY_CHECKS = 1;
