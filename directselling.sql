/*
Navicat MySQL Data Transfer

Source Server         : hemincan
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : directselling

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-07-06 21:32:35
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
  `parent_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '父用户ID',
  `left_performance` int(11) DEFAULT NULL COMMENT '左绩效',
  `right_performance` int(11) DEFAULT NULL COMMENT '右绩效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of agent_tree
-- ----------------------------
INSERT INTO `agent_tree` VALUES ('104', '1', '134', '138', '0', '2200', '6600');
INSERT INTO `agent_tree` VALUES ('105', '138', '137', '136', '1', '6600', '2200');
INSERT INTO `agent_tree` VALUES ('106', '137', '140', null, '138', '6600', '0');
INSERT INTO `agent_tree` VALUES ('107', '136', null, null, '138', '0', '0');
INSERT INTO `agent_tree` VALUES ('108', '134', '135', '139', '1', '2200', '2200');
INSERT INTO `agent_tree` VALUES ('109', '135', null, null, '134', '0', '0');
INSERT INTO `agent_tree` VALUES ('110', '139', null, null, '134', '0', '0');
INSERT INTO `agent_tree` VALUES ('111', '140', null, null, '137', '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of agent_type
-- ----------------------------
INSERT INTO `agent_type` VALUES ('1', '一级代理', '50', '2990', '2200', '多送4瓶洗发水，两套洗护用品', null, '500', '0.1', '60000');
INSERT INTO `agent_type` VALUES ('2', '二级代理', '30', '1790', '1000', '多送2瓶，一套洗护', null, '260', '0.09', '25000');
INSERT INTO `agent_type` VALUES ('3', '三级代理', '6', '348', '200', '', null, '0', '0.08', '5000');

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
  `agent_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of apply_goods
-- ----------------------------
INSERT INTO `apply_goods` VALUES ('246', '1', '2018-07-04 20:55:44', '一级代理', '50', '2990', '何敏灿', '13445677665', '广西南宁横县莲塘', '1', '多送4瓶洗发水，两套洗护用品', 'developer', '1');
INSERT INTO `apply_goods` VALUES ('247', '134', '2018-07-04 20:55:55', '一级代理', '50', '2990', '324234', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '9783223', '1');
INSERT INTO `apply_goods` VALUES ('248', '135', '2018-07-04 20:55:58', '一级代理', '50', '2990', '324234', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '6091022', '1');
INSERT INTO `apply_goods` VALUES ('249', '136', '2018-07-04 20:56:02', '一级代理', '50', '2990', '324234', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '5116388', '1');
INSERT INTO `apply_goods` VALUES ('250', '137', '2018-07-04 20:56:06', '一级代理', '50', '2990', '324234', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '9441029', '1');
INSERT INTO `apply_goods` VALUES ('251', '138', '2018-07-04 20:56:08', '一级代理', '50', '2990', '324234', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '5128711', '1');
INSERT INTO `apply_goods` VALUES ('252', '139', '2018-07-04 20:56:39', '一级代理', '50', '2990', '234', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '8928550', '1');
INSERT INTO `apply_goods` VALUES ('253', '140', '2018-07-04 20:58:57', '一级代理', '50', '2990', '12412423', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '4656726', '1');
INSERT INTO `apply_goods` VALUES ('254', '140', '2018-07-04 21:00:03', '一级代理', '50', '2990', '12412423', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '4656726', '1');
INSERT INTO `apply_goods` VALUES ('255', '140', '2018-07-04 21:05:17', '一级代理', '50', '2990', '12412423', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '4656726', '1');
INSERT INTO `apply_goods` VALUES ('256', '140', '2018-07-04 21:08:20', '一级代理', '50', '2990', '12412423', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '4656726', '1');
INSERT INTO `apply_goods` VALUES ('257', '140', '2018-07-04 21:10:06', '一级代理', '50', '2990', '12412423', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '4656726', '1');
INSERT INTO `apply_goods` VALUES ('258', '140', '2018-07-04 21:35:47', '一级代理', '50', '2990', '12412423', '234', '234', '1', '多送4瓶洗发水，两套洗护用品', '4656726', '1');

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
  `agent_account` varchar(30) DEFAULT NULL COMMENT '招收到的代理user_ID',
  `agent_name` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '0:还是余额，1，正在提现中，2，已经提现',
  `collision_integral` int(11) DEFAULT NULL,
  `collision_ratio` double DEFAULT NULL,
  `collision_top` int(11) DEFAULT NULL,
  `left_performance` int(11) DEFAULT NULL,
  `right_performance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=398 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bonus
-- ----------------------------
INSERT INTO `bonus` VALUES ('384', '1', '2018-07-04 20:56:15', '代理申单奖金', null, '500', '5128711', '324234', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('385', '1', '2018-07-04 20:56:17', '代理申单奖金', null, '500', '9441029', '324234', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('386', '1', '2018-07-04 20:56:19', '代理申单奖金', null, '500', '5116388', '324234', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('387', '1', '2018-07-04 20:56:21', '代理申单奖金', null, '500', '9783223', '324234', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('388', '1', '2018-07-04 20:56:23', '代理申单奖金', null, '500', '6091022', '324234', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('389', '1', '2018-07-04 20:56:44', '代理申单奖金', null, '500', '8928550', '234', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('390', '1', '2018-07-04 20:59:05', '代理申单奖金', null, '500', '4656726', '12412423', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('391', '1', '2018-07-04 21:00:13', '代理申单奖金', null, '500', '4656726', '12412423', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('392', '1', '2018-07-04 21:05:25', '代理申单奖金', null, '500', '4656726', '12412423', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('393', '1', '2018-07-04 21:08:27', '代理申单奖金', null, '500', '4656726', '12412423', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('396', '1', '2018-07-04 21:21:06', '代理申单奖金', null, '500', '4656726', '12412423', '1', null, null, null, null, null);
INSERT INTO `bonus` VALUES ('397', '1', '2018-07-04 21:35:54', '代理申单奖金', null, '500', '4656726', '12412423', '1', null, null, null, null, null);

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
  `position` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=480 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of integral
-- ----------------------------
INSERT INTO `integral` VALUES ('452', '2018-07-04 20:56:15', '1', '2200', null, '代理申单奖励', '1', 'right');
INSERT INTO `integral` VALUES ('453', '2018-07-04 20:56:17', '138', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('454', '2018-07-04 20:56:17', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('455', '2018-07-04 20:56:19', '138', '2200', null, '代理申单奖励', '1', 'right');
INSERT INTO `integral` VALUES ('456', '2018-07-04 20:56:19', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('457', '2018-07-04 20:56:21', '1', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('458', '2018-07-04 20:56:23', '134', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('459', '2018-07-04 20:56:23', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('460', '2018-07-04 20:56:44', '134', '2200', null, '代理申单奖励', '1', 'right');
INSERT INTO `integral` VALUES ('461', '2018-07-04 20:56:44', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('462', '2018-07-04 20:59:05', '137', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('463', '2018-07-04 20:59:05', '138', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('464', '2018-07-04 20:59:05', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('465', '2018-07-04 21:00:13', '137', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('466', '2018-07-04 21:00:13', '138', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('467', '2018-07-04 21:00:13', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('468', '2018-07-04 21:05:25', '137', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('469', '2018-07-04 21:05:25', '138', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('470', '2018-07-04 21:05:25', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('471', '2018-07-04 21:08:27', '137', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('472', '2018-07-04 21:08:27', '138', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('473', '2018-07-04 21:08:27', '1', '2200', null, '代理申单奖励', '1', null);
INSERT INTO `integral` VALUES ('474', '2018-07-04 21:21:18', '137', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('475', '2018-07-04 21:21:18', '138', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('476', '2018-07-04 21:21:18', '1', '2200', null, '代理申单奖励', '1', 'right');
INSERT INTO `integral` VALUES ('477', '2018-07-04 21:35:54', '137', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('478', '2018-07-04 21:35:54', '138', '2200', null, '代理申单奖励', '1', 'left');
INSERT INTO `integral` VALUES ('479', '2018-07-04 21:35:54', '1', '2200', null, '代理申单奖励', '1', 'right');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content` longtext,
  `receive_date` datetime DEFAULT NULL,
  `readed` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('215', '1', '代理:324234(5128711)通过验证成功，正式成为你的代理。放置在你的左区。', '2018-07-04 20:56:15', '0');
INSERT INTO `message` VALUES ('216', '1', '代理:324234(5128711)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 20:56:15', '0');
INSERT INTO `message` VALUES ('217', '1', '代理:324234(5128711)申单成功，您右区获得2200积分。', '2018-07-04 20:56:15', '0');
INSERT INTO `message` VALUES ('218', '1', '代理:324234(9441029)通过验证成功，正式成为你的代理。放置在你的左区。', '2018-07-04 20:56:16', '0');
INSERT INTO `message` VALUES ('219', '1', '代理:324234(9441029)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 20:56:17', '0');
INSERT INTO `message` VALUES ('220', '138', '代理:324234(9441029)申单成功，您左区获得2200积分。', '2018-07-04 20:56:17', '0');
INSERT INTO `message` VALUES ('221', '1', '代理:324234(9441029)申单成功，您左区获得2200积分。', '2018-07-04 20:56:17', '0');
INSERT INTO `message` VALUES ('222', '1', '代理:324234(5116388)通过验证成功，正式成为你的代理。放置在你的左区。', '2018-07-04 20:56:19', '0');
INSERT INTO `message` VALUES ('223', '1', '代理:324234(5116388)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 20:56:19', '0');
INSERT INTO `message` VALUES ('224', '138', '代理:324234(5116388)申单成功，您右区获得2200积分。', '2018-07-04 20:56:19', '0');
INSERT INTO `message` VALUES ('225', '1', '代理:324234(5116388)申单成功，您左区获得2200积分。', '2018-07-04 20:56:19', '0');
INSERT INTO `message` VALUES ('226', '1', '代理:324234(9783223)通过验证成功，正式成为你的代理。放置在你的左区。', '2018-07-04 20:56:21', '0');
INSERT INTO `message` VALUES ('227', '1', '代理:324234(9783223)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 20:56:21', '0');
INSERT INTO `message` VALUES ('228', '1', '代理:324234(9783223)申单成功，您左区获得2200积分。', '2018-07-04 20:56:21', '0');
INSERT INTO `message` VALUES ('229', '1', '代理:324234(6091022)通过验证成功，正式成为你的代理。放置在你的左区。', '2018-07-04 20:56:23', '0');
INSERT INTO `message` VALUES ('230', '1', '代理:324234(6091022)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 20:56:23', '0');
INSERT INTO `message` VALUES ('231', '134', '代理:324234(6091022)申单成功，您左区获得2200积分。', '2018-07-04 20:56:23', '0');
INSERT INTO `message` VALUES ('232', '1', '代理:324234(6091022)申单成功，您左区获得2200积分。', '2018-07-04 20:56:23', '0');
INSERT INTO `message` VALUES ('233', '1', '代理:234(8928550)通过验证成功，正式成为你的代理。放置在你的左区。', '2018-07-04 20:56:44', '0');
INSERT INTO `message` VALUES ('234', '1', '代理:234(8928550)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 20:56:44', '0');
INSERT INTO `message` VALUES ('235', '134', '代理:234(8928550)申单成功，您右区获得2200积分。', '2018-07-04 20:56:44', '0');
INSERT INTO `message` VALUES ('236', '1', '代理:234(8928550)申单成功，您左区获得2200积分。', '2018-07-04 20:56:44', '0');
INSERT INTO `message` VALUES ('237', '1', '代理:12412423(4656726)通过验证成功，正式成为你的代理。放置在你的左区。', '2018-07-04 20:59:05', '0');
INSERT INTO `message` VALUES ('238', '1', '代理:12412423(4656726)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 20:59:05', '0');
INSERT INTO `message` VALUES ('239', '137', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 20:59:05', '0');
INSERT INTO `message` VALUES ('240', '138', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 20:59:05', '0');
INSERT INTO `message` VALUES ('241', '1', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 20:59:05', '0');
INSERT INTO `message` VALUES ('242', '1', '代理:12412423(4656726)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 21:00:13', '0');
INSERT INTO `message` VALUES ('243', '137', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:00:13', '0');
INSERT INTO `message` VALUES ('244', '138', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:00:13', '0');
INSERT INTO `message` VALUES ('245', '1', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:00:13', '0');
INSERT INTO `message` VALUES ('246', '1', '代理:12412423(4656726)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 21:05:25', '0');
INSERT INTO `message` VALUES ('247', '137', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:05:25', '0');
INSERT INTO `message` VALUES ('248', '138', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:05:25', '0');
INSERT INTO `message` VALUES ('249', '1', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:05:25', '0');
INSERT INTO `message` VALUES ('250', '1', '代理:12412423(4656726)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 21:08:27', '0');
INSERT INTO `message` VALUES ('251', '137', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:08:27', '0');
INSERT INTO `message` VALUES ('252', '138', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:08:27', '0');
INSERT INTO `message` VALUES ('253', '1', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:08:27', '0');
INSERT INTO `message` VALUES ('256', '1', '代理:12412423(4656726)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 21:21:06', '0');
INSERT INTO `message` VALUES ('257', '137', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:21:18', '0');
INSERT INTO `message` VALUES ('258', '138', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:21:18', '0');
INSERT INTO `message` VALUES ('259', '1', '代理:12412423(4656726)申单成功，您右区获得2200积分。', '2018-07-04 21:21:18', '0');
INSERT INTO `message` VALUES ('260', '1', '代理:12412423(4656726)申单成功，您获得500奖金，已经成功发放到您的帐户。', '2018-07-04 21:35:54', '0');
INSERT INTO `message` VALUES ('261', '137', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:35:54', '0');
INSERT INTO `message` VALUES ('262', '138', '代理:12412423(4656726)申单成功，您左区获得2200积分。', '2018-07-04 21:35:54', '0');
INSERT INTO `message` VALUES ('263', '1', '代理:12412423(4656726)申单成功，您右区获得2200积分。', '2018-07-04 21:35:54', '0');

-- ----------------------------
-- Table structure for pretreatment_agent
-- ----------------------------
DROP TABLE IF EXISTS `pretreatment_agent`;
CREATE TABLE `pretreatment_agent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `tree_parent_user_id` int(11) DEFAULT NULL,
  `position` varchar(30) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pretreatment_agent
-- ----------------------------
INSERT INTO `pretreatment_agent` VALUES ('42', '134', '1', 'left', '0');
INSERT INTO `pretreatment_agent` VALUES ('43', '135', '1', 'left', '0');
INSERT INTO `pretreatment_agent` VALUES ('44', '136', '1', 'right', '0');
INSERT INTO `pretreatment_agent` VALUES ('45', '137', '1', 'right', '0');
INSERT INTO `pretreatment_agent` VALUES ('46', '138', '1', 'right', '0');
INSERT INTO `pretreatment_agent` VALUES ('47', '139', '134', 'right', '0');
INSERT INTO `pretreatment_agent` VALUES ('48', '140', '1', 'right', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '/system/menu/index', '菜单管理', '0', 'null');
INSERT INTO `sys_menu` VALUES ('2', '/system/menu/add', '添加菜单', '1', null);
INSERT INTO `sys_menu` VALUES ('5', '/system/role/index', '角色管理', '0', 'null');
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
INSERT INTO `sys_menu` VALUES ('26', '/memberCenter/userList', '代理管理', '12', null);
INSERT INTO `sys_menu` VALUES ('27', '/moneyCenter/transferList', '转帐记录', '19', null);
INSERT INTO `sys_menu` VALUES ('28', '/memberCenter/teamList', '团队列表', '12', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
  `is_activate` int(4) DEFAULT NULL,
  `agent_type_id` int(11) DEFAULT NULL,
  `balance` int(11) DEFAULT '0' COMMENT '余额',
  `integral` int(11) DEFAULT NULL,
  `is_admin` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'developer', '何敏灿', '4297F44B13955235245B2497399D7A93', '2018-06-10 01:16:09', '1', '2018-07-04 21:21:00', '456755544333345', '13445677665', '23455@22.com', '10278723734', '广西南宁横县莲塘', '45644364653634534', '中国农业银行', null, '\'banace=0 --', '0', null, '1', '1', '6780', '28400', '1');
INSERT INTO `sys_user` VALUES ('2', 'admin', '管理员', '4297F44B13955235245B2497399D7A93', '2018-06-25 22:13:20', '1', '2018-07-03 16:50:27', null, null, null, null, null, null, null, null, null, '0', '666666', '1', '1', '0', '0', '1');
INSERT INTO `sys_user` VALUES ('3', 'superadmin', '超级管理员', '4297F44B13955235245B2497399D7A93', '2018-06-25 22:16:08', '1', '2018-06-27 19:41:43', null, null, null, null, null, null, null, null, null, '0', '666666', '1', '1', '0', '0', '1');
INSERT INTO `sys_user` VALUES ('34', 'heminken', '何敏垦', 'D964173DC44DA83EEAFA3AEBBEE9A1A0', '2018-06-21 22:03:33', '1', '2018-07-02 14:58:00', '45212219931130271X', '18344594442', null, '634901606', '广西南宁兴宁区民主路大官塘村23栋', '6217003370016560052', '中国建设银行', '何敏垦', '广西南宁民主路支行', '0', '666666', '1', '1', '0', '0', '1');
INSERT INTO `sys_user` VALUES ('134', '9783223', '324234', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-07-04 20:55:54', '1', '2018-07-04 20:55:54', '234', '234', null, '234', '234', null, null, '324234', null, '1', 'F379EAF3C831B04DE153469D1BEC345E', '1', '1', '0', '4400', '0');
INSERT INTO `sys_user` VALUES ('135', '6091022', '324234', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-07-04 20:55:58', '1', '2018-07-04 20:55:58', '234', '234', null, '234', '234', null, null, '324234', null, '1', 'F379EAF3C831B04DE153469D1BEC345E', '1', '1', '0', null, '0');
INSERT INTO `sys_user` VALUES ('136', '5116388', '324234', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-07-04 20:56:02', '1', '2018-07-04 20:56:02', '234', '234', null, '234', '234', null, null, '324234', null, '1', 'F379EAF3C831B04DE153469D1BEC345E', '1', '1', '0', null, '0');
INSERT INTO `sys_user` VALUES ('137', '9441029', '324234', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-07-04 20:56:06', '1', '2018-07-04 20:56:06', '234', '234', null, '234', '234', null, null, '324234', null, '1', 'F379EAF3C831B04DE153469D1BEC345E', '1', '1', '0', '13200', '0');
INSERT INTO `sys_user` VALUES ('138', '5128711', '324234', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-07-04 20:56:08', '1', '2018-07-04 20:56:08', '234', '234', null, '234', '234', null, null, '324234', null, '1', 'F379EAF3C831B04DE153469D1BEC345E', '1', '1', '0', '17600', '0');
INSERT INTO `sys_user` VALUES ('139', '8928550', '234', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-07-04 20:56:39', '1', '2018-07-04 20:56:39', '234', '234', null, '234', '234', null, null, '234', null, '1', 'F379EAF3C831B04DE153469D1BEC345E', '1', '1', '0', null, '0');
INSERT INTO `sys_user` VALUES ('140', '4656726', '12412423', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-07-04 20:58:57', '1', '2018-07-04 21:35:36', '4234', '234', null, '234', '234', null, null, '12412423', null, '1', 'F379EAF3C831B04DE153469D1BEC345E', '1', '1', '0', null, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('15', '1', '2');
INSERT INTO `sys_user_role` VALUES ('16', '1', '1');
INSERT INTO `sys_user_role` VALUES ('19', '2', '2');
INSERT INTO `sys_user_role` VALUES ('20', '3', '2');
INSERT INTO `sys_user_role` VALUES ('21', '3', '1');
INSERT INTO `sys_user_role` VALUES ('22', '34', '2');
INSERT INTO `sys_user_role` VALUES ('23', '34', '1');

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `transfer_date` datetime DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `from_to_user_id` int(11) DEFAULT NULL,
  `mark` varchar(200) DEFAULT NULL,
  `user_account` varchar(30) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `from_to_user_account` varchar(100) DEFAULT NULL,
  `from_to_user_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of transfer
-- ----------------------------

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
  `handle_date` datetime DEFAULT NULL,
  `handle_account` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of withdraw
-- ----------------------------
