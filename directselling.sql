/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : directselling

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2018-06-12 13:47:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agent_tree
-- ----------------------------
DROP TABLE IF EXISTS `agent_tree`;
CREATE TABLE `agent_tree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `left_user_id` int(11) DEFAULT NULL COMMENT '左用户ID',
  `right_user_id` int(11) DEFAULT NULL COMMENT '右用户ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父用户ID',
  `left_performance` int(11) DEFAULT NULL COMMENT '左绩效',
  `right_performance` int(11) DEFAULT NULL COMMENT '右绩效',
  `left_userId` int(11) DEFAULT NULL,
  `right_rerformance` int(11) DEFAULT NULL,
  `right_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agent_tree
-- ----------------------------

-- ----------------------------
-- Table structure for agent_type
-- ----------------------------
DROP TABLE IF EXISTS `agent_type`;
CREATE TABLE `agent_type` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '用户类型名',
  `able_count` int(11) DEFAULT NULL COMMENT '可代理数量',
  `total_money` int(11) DEFAULT NULL COMMENT '总价',
  `integral` int(11) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agent_type
-- ----------------------------

-- ----------------------------
-- Table structure for apply_goods
-- ----------------------------
DROP TABLE IF EXISTS `apply_goods`;
CREATE TABLE `apply_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `apply_date` datetime DEFAULT NULL COMMENT '报单日期',
  `goods_type` varchar(255) DEFAULT NULL COMMENT '商品类型',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `total_money` int(11) DEFAULT NULL COMMENT '总价格',
  `receiver_name` varchar(255) DEFAULT NULL COMMENT '收货人',
  `receiver_phone` varchar(255) DEFAULT NULL COMMENT '收货人地址',
  `receiver_address` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply_goods
-- ----------------------------

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `obtain_date` datetime DEFAULT NULL COMMENT '获得奖金时间',
  `bonus_type` varchar(11) DEFAULT NULL COMMENT '奖金类型',
  `mark` varchar(255) DEFAULT NULL COMMENT '标注',
  `money` int(11) DEFAULT NULL COMMENT '所获得金钱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bonus
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '/system/menu/index', '菜单管理', '0', 'null');
INSERT INTO `sys_menu` VALUES ('2', '/system/menu/add', '添加菜单', '1', null);
INSERT INTO `sys_menu` VALUES ('5', '/system/role/index', '角色管理', '0', 'null');
INSERT INTO `sys_menu` VALUES ('7', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('9', '/system/role/add', '角色添加', '5', null);
INSERT INTO `sys_menu` VALUES ('10', '/system/user/index', '用户管理', '0', null);
INSERT INTO `sys_menu` VALUES ('11', '/system/user/add', '添加用户', '10', null);
INSERT INTO `sys_menu` VALUES ('12', '/', '会员中心', '0', null);
INSERT INTO `sys_menu` VALUES ('13', '/memberCenter/addAgent', '注册代理', '12', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '佣兵', '保护膜', null);
INSERT INTO `sys_role` VALUES ('2', '777', '456', null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `user_sex` int(1) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `identity_card` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `qq_number` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bank_card` varchar(50) DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `bank_address` varchar(50) DEFAULT NULL,
  `recommend_user_id` int(11) DEFAULT NULL,
  `second_password` varchar(50) DEFAULT NULL,
  `is_activate` tinyint(4) DEFAULT NULL,
  `agent_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '123456', '何敏灿', '123123', '2018-06-10 01:16:09', '1', '2018-06-10 01:16:25', '456755544333345', '13445677665', '23455@22.com', '1027872373', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for withdraw
-- ----------------------------
DROP TABLE IF EXISTS `withdraw`;
CREATE TABLE `withdraw` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `withdraw_amount` int(11) DEFAULT NULL COMMENT '提现金额',
  `poundage` int(11) DEFAULT NULL COMMENT '手续费',
  `real_amount` int(11) DEFAULT NULL COMMENT '实发金额',
  `bank_card` varchar(255) DEFAULT NULL COMMENT '银行',
  `bank_user_name` varchar(255) DEFAULT NULL COMMENT '银行户名',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名',
  `bank_address` varchar(255) DEFAULT NULL COMMENT '银行地地址',
  `state` int(11) DEFAULT NULL COMMENT '发放状态',
  `application_time` datetime DEFAULT NULL COMMENT '申请时间',
  `declaration_center` varchar(255) DEFAULT NULL COMMENT '报单中心',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of withdraw
-- ----------------------------
