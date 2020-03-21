/*
Navicat MySQL Data Transfer

Source Server         : 阿里-外网
Source Server Version : 80016
Source Host           : rm-2ze49ybm2gxkzk6i1fo.mysql.rds.aliyuncs.com:3306
Source Database       : code-gen

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-03-21 20:03:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_datasource_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource_conf`;
CREATE TABLE `sys_datasource_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据源名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'url',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据库密码',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'driver',
  `db_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据库类型 mysql oracle',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据源配置表';

-- ----------------------------
-- Records of sys_datasource_conf
-- ----------------------------
INSERT INTO `sys_datasource_conf` VALUES ('1', '本地数据源', 'jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8', 'root', 'root', '2020-03-21 19:24:17', '2020-03-21 19:28:58', '0', 'com.mysql.cj.jdbc.Driver', 'mysql');

-- ----------------------------
-- Table structure for sys_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_gen_config`;
CREATE TABLE `sys_gen_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `package_name` varchar(255) DEFAULT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成配置表';

-- ----------------------------
-- Records of sys_gen_config
-- ----------------------------
INSERT INTO `sys_gen_config` VALUES ('1', 'monkeyzi22', 'com.monkeyzi.code', 'app', 'monkeyzi-code', null, '2020-03-21 19:21:55');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', null, '代码生成管理', null, 'layui-icon-setting', '0', '0', null, null);
INSERT INTO `sys_menu` VALUES ('2', '1', 'gen:view', '代码生成', '/gen/code', '', '0', '1', null, null);
INSERT INTO `sys_menu` VALUES ('3', '1', 'ds:view', '数据源管理', '/ds', null, '0', '2', null, null);
INSERT INTO `sys_menu` VALUES ('4', '3', 'ds:add', '新增数据源', '', null, '1', '3', null, null);
INSERT INTO `sys_menu` VALUES ('5', '3', 'ds:update', '更新数据源', null, null, '1', '4', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超管', '超级管理员', '0', null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('5', '5', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `theme` varchar(255) DEFAULT NULL,
  `is_tab` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'monkeyzi', '0fd6d6ca22862e4115aafe1ef4b2a8bb', null, '0', '2020-03-21 18:48:08', null, 'default.jpg', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
