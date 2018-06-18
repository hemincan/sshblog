/*
Navicat MySQL Data Transfer

Source Server         : hemincan
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : directselling

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-06-18 16:44:06
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
  `parent_user_id` int(11) DEFAULT NULL COMMENT '父用户ID',
  `left_performance` int(11) DEFAULT NULL COMMENT '左绩效',
  `right_performance` int(11) DEFAULT NULL COMMENT '右绩效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agent_tree
-- ----------------------------
INSERT INTO `agent_tree` VALUES ('1', '1', null, '31', '0', '4800', '0');
INSERT INTO `agent_tree` VALUES ('22', '31', null, '32', '1', '1600', '0');
INSERT INTO `agent_tree` VALUES ('23', '32', null, null, '31', '1000', '0');

-- ----------------------------
-- Table structure for agent_type
-- ----------------------------
DROP TABLE IF EXISTS `agent_type`;
CREATE TABLE `agent_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '用户类型名',
  `able_count` int(11) DEFAULT NULL COMMENT '可代理数量',
  `total_money` int(11) DEFAULT NULL COMMENT '总价',
  `integral` int(11) DEFAULT NULL COMMENT '积分',
  `remark` varchar(255) DEFAULT NULL,
  `display_order` int(11) DEFAULT NULL,
  `first_reward_money` int(11) DEFAULT NULL COMMENT '第一次招收代理所获得的奖金',
  `collision_per` double DEFAULT NULL,
  `top_reward` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agent_type
-- ----------------------------
INSERT INTO `agent_type` VALUES ('1', '一级代理', '50', '2990', '2200', '送4瓶洗发水+围布+手套+喷壳+吹风', null, '500', '0.1', '40000');
INSERT INTO `agent_type` VALUES ('2', '二级代理', '30', '1790', '1000', '什么都不送', null, '200', '0.09', '30000');
INSERT INTO `agent_type` VALUES ('3', '三级代理', '6', '388', '0', '送两瓶洗发水', null, '60', '0.2', '50000');

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
  `remark` varchar(255) DEFAULT NULL,
  `user_account` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply_goods
-- ----------------------------
INSERT INTO `apply_goods` VALUES ('1', '1', '2018-06-15 23:27:54', '一级代理', '50', '2990', '何敏灿', '13445677665', '你342334234', '0', null, null);
INSERT INTO `apply_goods` VALUES ('2', '1', '2018-06-15 23:28:33', '一级代理', '50', '2990', '何敏灿', '13445677665', '你342334234', '0', null, null);
INSERT INTO `apply_goods` VALUES ('3', '1', '2018-06-15 23:29:49', '一级代理', '50', '2990', '何敏灿', '13445677665', '234', '0', null, null);
INSERT INTO `apply_goods` VALUES ('4', '1', '2018-06-15 23:47:57', '一级代理', '50', '2990', '何敏灿', '13445677665', '3456789', '0', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('5', '22', '2018-06-16 20:40:58', '一级代理', '50', '2990', 'asdf', 'sdf', 'asdf', '0', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('6', '22', '2018-06-16 20:43:06', '一级代理', '50', '2990', 'asdf', 'sdf', 'asdf', '0', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('7', '22', '2018-06-16 20:46:20', '一级代理', '50', '2990', 'asdf', 'sdf', 'asdf', '0', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('8', '22', '2018-06-16 20:48:50', '一级代理', '50', '2990', 'asdf', 'sdf', 'asdf', '1', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('9', '22', '2018-06-16 20:49:26', '一级代理', '50', '2990', 'asdf', 'sdf', 'asdf', '1', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('10', '22', '2018-06-16 21:05:10', '一级代理', '50', '2990', 'asdf', 'sdf', 'asdf', '1', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('11', '32', '2018-06-16 21:15:10', '一级代理', '50', '2990', '呵呵', '12443413412', '234234', '1', '送4瓶洗发水+围布+手套+喷壳+吹风', null);
INSERT INTO `apply_goods` VALUES ('12', '1', '2018-06-17 16:02:30', '一级代理', '50', '2990', '何敏灿', '13445677665', '广西南宁横县莲塘', '1', '送4瓶洗发水+围布+手套+喷壳+吹风', '123456');
INSERT INTO `apply_goods` VALUES ('13', '1', '2018-06-17 16:15:57', '一级代理', '50', '2990', '何敏灿', '13445677665', '广西南宁横县莲塘', '1', '送4瓶洗发水+围布+手套+喷壳+吹风', '123456');
INSERT INTO `apply_goods` VALUES ('14', '1', '2018-06-18 11:57:45', '一级代理', '50', '2990', '何敏灿', '13445677665', '广西南宁横县莲塘', '1', '送4瓶洗发水+围布+手套+喷壳+吹风', '123456');

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
  `agent_account` int(11) DEFAULT NULL COMMENT '招收到的代理user_ID',
  `agent_name` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '0:还是余额，1，正在提现中，2，已经提现',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bonus
-- ----------------------------
INSERT INTO `bonus` VALUES ('162', '1', '2018-06-17 18:43:41', '对碰奖金', null, '1000', null, null, '1');
INSERT INTO `bonus` VALUES ('163', '31', '2018-06-17 18:43:51', '对碰奖金', null, '20', null, null, '1');
INSERT INTO `bonus` VALUES ('164', '1', '2018-06-18 11:55:41', '对碰奖金', null, '500', null, null, '1');
INSERT INTO `bonus` VALUES ('165', '1', '2018-06-18 11:57:01', '对碰奖金', null, '20', null, null, '1');
INSERT INTO `bonus` VALUES ('166', '31', '2018-06-18 11:57:01', '对碰奖金', null, '20', null, null, '1');

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receive_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of integral
-- ----------------------------
INSERT INTO `integral` VALUES ('1', '2018-06-16 20:48:50', '22', '2200', null, '代理申单奖励', '0');
INSERT INTO `integral` VALUES ('2', '2018-06-16 20:48:50', '3', '2200', null, '代理申单奖励', '0');
INSERT INTO `integral` VALUES ('3', '2018-06-16 20:49:26', '22', '2200', null, '代理申单奖励', '0');
INSERT INTO `integral` VALUES ('4', '2018-06-16 20:49:26', '3', '2200', null, '代理申单奖励', '0');
INSERT INTO `integral` VALUES ('5', '2018-06-16 21:05:10', '3', '2200', null, '代理申单奖励', '0');
INSERT INTO `integral` VALUES ('6', '2018-06-16 21:15:11', '31', '2200', null, '代理申单奖励', '0');
INSERT INTO `integral` VALUES ('7', '2018-06-16 21:15:11', '1', '2200', null, '代理申单奖励', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

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
INSERT INTO `sys_menu` VALUES ('14', '/memberCenter/treeStructure', '安置结构', '12', null);
INSERT INTO `sys_menu` VALUES ('15', '/memberCenter/recommendedStructure', '推荐结构', '12', null);
INSERT INTO `sys_menu` VALUES ('16', '/memberCenter/agentType', '代理类型', '12', null);
INSERT INTO `sys_menu` VALUES ('17', '/', '报单中心', '0', null);
INSERT INTO `sys_menu` VALUES ('18', '/applyGoods/userApplyList', '用户报单', '17', null);
INSERT INTO `sys_menu` VALUES ('19', '/', '金钱中心', '0', null);
INSERT INTO `sys_menu` VALUES ('20', 'applyGoods/apply', '报单申请', '17', null);
INSERT INTO `sys_menu` VALUES ('21', '/moneyCenter/bonus', '奖金记录', '19', null);
INSERT INTO `sys_menu` VALUES ('22', '/moneyCenter/withdraw', '提现记录', '19', null);
INSERT INTO `sys_menu` VALUES ('23', '/moneyCenter/integral', '积分记录', '19', null);
INSERT INTO `sys_menu` VALUES ('24', '/moneyCenter/applyWithdraw', '申请提现', '19', null);
INSERT INTO `sys_menu` VALUES ('25', '/memberCenter/agentTypeEdit', '代理类型编辑', '12', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `en_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员有最高权限', null, 'superadmin');
INSERT INTO `sys_role` VALUES ('2', '普通管理员', '可以访问非超级管理员的其他内容', null, 'admin');

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
  `balance` int(11) DEFAULT '0' COMMENT '余额',
  `integral` int(11) DEFAULT NULL,
  `is_admin` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '123456', '何敏灿', '123', '2018-06-10 01:16:09', '1', '2018-06-18 13:49:03', '456755544333345', '13445677665', '23455@22.com', '10278723734', '广西南宁横县莲塘', '45644364653634534', '中国农业银行', null, '广西南宁东葛路', '0', null, '1', '1', '5362', '2200', '1');
INSERT INTO `sys_user` VALUES ('2', '9482427', '2342342', '235235', '2018-06-13 22:02:38', '1', null, '5235235', '253', null, '25323', null, null, null, null, null, '1', null, '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('3', '4046828', '2343', '4234234', '2018-06-12 20:34:00', '1', '2018-06-12 20:34:00', '423423', '234234', null, '234', null, null, null, '2343', null, '1', '666666', '1', '1', '0', '6600', null);
INSERT INTO `sys_user` VALUES ('4', '7489642', '23423', '4234234', '2018-06-12 20:39:28', '1', '2018-06-12 20:39:28', '423423', '234234', null, '234234', null, null, null, '23423', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('5', '4866498', '345345', '345345345', '2018-06-12 20:42:08', '1', '2018-06-12 20:42:08', '345345', '35345345', null, '3453', null, null, null, '345345', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('6', '4856212', '234', '234234', '2018-06-12 20:43:33', '1', '2018-06-12 20:43:33', '24', '234243', null, '23424', null, null, null, '234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('7', '3940675', '23434', '234234', '2018-06-13 22:39:37', '1', '2018-06-13 22:39:37', '234234', '234234', null, '234234', null, null, null, '23434', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('8', '1892917', '23423', '234234', '2018-06-13 22:44:06', '1', '2018-06-13 22:44:06', '4234', '234234', null, '234', null, null, null, '23423', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('9', '3739911', '111111111', '31231231', '2018-06-13 22:50:21', '1', '2018-06-13 22:50:21', '2312', '23123123', null, '123', null, null, null, '111111111', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('10', '1569905', '舵手e', '脾', '2018-06-13 22:54:07', '1', '2018-06-13 22:54:07', '甩甩手ert', '34534', null, '5345', null, null, null, '舵手e', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('11', '1739665', '23434', '234234', '2018-06-13 22:54:57', '1', '2018-06-13 22:54:57', '234', '234', null, '234', null, null, null, '23434', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('12', '2694163', '234234', '2342', '2018-06-13 23:26:20', '1', '2018-06-13 23:26:20', '234234', '234234', null, '234234', null, null, null, '234234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('13', '1374946', '342342', '4234', '2018-06-13 23:26:39', '1', '2018-06-13 23:26:39', '3423423', '234', null, '234234', null, null, null, '342342', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('14', '8273584', '2222222222', '4234234', '2018-06-14 22:38:07', '1', '2018-06-14 22:38:07', '23423', '234', null, '234', null, null, null, '2222222222', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('15', '1178280', '222222333', '234234', '2018-06-14 22:38:46', '1', '2018-06-14 22:38:46', '33434', '234', null, '234', null, null, null, '222222333', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('16', '3154485', '234234', '34234', '2018-06-14 22:48:42', '1', '2018-06-14 22:48:42', '2342', '2342342', null, '34234', null, null, null, '234234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('17', '6132010', '234234', '34234', '2018-06-15 22:17:09', '1', '2018-06-15 22:17:09', '2342', '234234', null, '234', '234235235', null, null, '234234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('18', '4367519', '234234', '34234', '2018-06-15 22:17:20', '1', '2018-06-15 22:17:20', '2342', '234234', null, '234', '234235235', null, null, '234234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('19', '3583462', 'sfsdf', 'sfsdfdsfsdfsdfsdf', '2018-06-15 22:19:39', '1', '2018-06-15 22:19:39', 'sfsdfdsfsdf', 'sdf', null, 'sdf', 'sdfsdf', null, null, 'sfsdf', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('20', '4799053', 'sfsdf', 'sfsdfdsfsdfsdfsdf', '2018-06-15 22:19:46', '1', '2018-06-15 22:19:46', 'sfsdfdsfsdf', 'sdf', null, 'sdf', 'sdfsdf', null, null, 'sfsdf', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('21', '9726306', 'adf', 'adf', '2018-06-15 22:20:50', '1', '2018-06-15 22:20:50', 'adf', 'adf', null, 'afd', 'adf', null, null, 'adf', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('22', '7452868', 'asdf', 'adf', '2018-06-15 22:21:18', '1', '2018-06-16 20:46:14', 'adf', 'sdf', null, 'adsf', 'asdf', null, null, 'asdf', null, '1', '666666', '1', '1', '0', '4400', null);
INSERT INTO `sys_user` VALUES ('23', '7154457', 'sdf', 'fadfa', '2018-06-15 22:22:15', '1', '2018-06-15 22:22:15', 'adfad', 'dsfasd', null, 'fasdf', 'adfadf', null, null, 'sdf', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('24', '5733832', 'adf', 'asdfasdf', '2018-06-15 22:22:34', '1', '2018-06-15 22:22:34', 'adf', 'asdf', null, 'adsf', 'adsfad', null, null, 'adf', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('25', '6890886', '3243', '234', '2018-06-15 22:32:32', '1', '2018-06-15 22:32:32', '4234', '234', null, '234', '234234', null, null, '3243', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('26', '5998020', '234', '23423', '2018-06-15 22:36:54', '1', '2018-06-15 22:36:54', '234', '234', null, '234', '23423', null, null, '234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('27', '2341301', '234', '23423', '2018-06-15 22:36:59', '1', '2018-06-15 22:36:59', '234', '234', null, '234', '23423', null, null, '234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('28', '2501227', '234', '23423', '2018-06-15 22:37:02', '1', '2018-06-15 22:37:02', '234', '234', null, '234', '23423', null, null, '234', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('29', '7906974', '2434', '34234', '2018-06-16 00:55:29', '1', '2018-06-16 00:55:29', '2342342', '234234', null, '234234', '2353452', null, null, '2434', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('30', '8491459', 'r', '234234', '2018-06-16 00:58:10', '1', '2018-06-16 00:58:10', 'r\'r\'we\'re\'r', '234', null, '234234', '234234', null, null, 'r', null, '1', '666666', '1', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('31', '4576506', '李小姐', '234234', '2018-06-16 21:14:09', '1', '2018-06-16 21:14:09', '235252439523', '2354523', null, '235325', '广西南宁', null, null, '李小姐', null, '1', '666666', '1', '1', '240', '2200', null);
INSERT INTO `sys_user` VALUES ('32', '4319589', '呵呵', '123123', '2018-06-16 21:14:43', '1', '2018-06-16 21:15:00', '2343524524', '12443413412', null, '234234', '234234', null, null, '呵呵', null, '1', '666666', '1', '1', '0', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('15', '1', '2');
INSERT INTO `sys_user_role` VALUES ('16', '1', '1');

-- ----------------------------
-- Table structure for withdraw
-- ----------------------------
DROP TABLE IF EXISTS `withdraw`;
CREATE TABLE `withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `user_account` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of withdraw
-- ----------------------------
INSERT INTO `withdraw` VALUES ('1', '1', '3', '0', '3', 'werwer', 'werwer', '3wr23r23', 'rwerwer', '0', '2018-06-16 14:32:56', null, 'aa');
INSERT INTO `withdraw` VALUES ('2', '1', '34', '0', '34', '234', '234', '242', '234', '0', '2018-06-16 14:39:05', null, null);
INSERT INTO `withdraw` VALUES ('3', '1', '2342', '23', '2319', '45644364653634534', '何敏灿', '中国农业银行', '广西南宁东葛路', '0', '2018-06-17 16:13:20', null, '123456');
INSERT INTO `withdraw` VALUES ('4', '1', '2', '0', '2', '45644364653634534', '何敏灿', '中国农业银行', '广西南宁东葛路', '0', '2018-06-18 11:58:21', null, '123456');
