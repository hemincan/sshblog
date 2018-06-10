/*
Navicat MySQL Data Transfer

Source Server         : hemincan
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : directselling

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-06-10 17:38:18
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Table structure for declaration_form
-- ----------------------------
DROP TABLE IF EXISTS `declaration_form`;
CREATE TABLE `declaration_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `declaration_date` datetime DEFAULT NULL COMMENT '报单日期',
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
-- Records of declaration_form
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '/menu/index', '菜单管理7', '0', 'null');
INSERT INTO `sys_menu` VALUES ('2', '/menu/add', '添加菜单', '1', null);
INSERT INTO `sys_menu` VALUES ('3', '/menu/edit', '修改菜单', '1', null);
INSERT INTO `sys_menu` VALUES ('4', 'w3r', 'were', '0', null);
INSERT INTO `sys_menu` VALUES ('5', '/menu/index', '菜单管理1w', '0', 'null');
INSERT INTO `sys_menu` VALUES ('6', 'w3r', 'were2231', '0', 'null');
INSERT INTO `sys_menu` VALUES ('7', null, null, null, null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

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
  `level_type_id` int(11) DEFAULT NULL,
  `bank__address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '123456', '何敏灿', '123123', '2018-06-10 01:16:09', '1', '2018-06-10 01:16:25', '456755544333345', '13445677665', '23455@22.com', '1027872373', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for tree_diagram
-- ----------------------------
DROP TABLE IF EXISTS `tree_diagram`;
CREATE TABLE `tree_diagram` (
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
-- Records of tree_diagram
-- ----------------------------

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `level_name` varchar(50) DEFAULT NULL COMMENT '用户类型名',
  `able_count` int(11) DEFAULT NULL COMMENT '可代理数量',
  `total_money` int(11) DEFAULT NULL COMMENT '总价',
  `integral` int(11) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_type
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
