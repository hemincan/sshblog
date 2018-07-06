package com.xrom.ssh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.jmx.Agent;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xrom.ssh.dto.applygoods.ApplyGoodsInfo;
import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.AgentType;
import com.xrom.ssh.entity.ApplyGoods;
import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.Integral;
import com.xrom.ssh.entity.Message;
import com.xrom.ssh.entity.PretreatmentAgent;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.AgentTreeRepository;
import com.xrom.ssh.repository.AgentTypeRepository;
import com.xrom.ssh.repository.ApplyGoodsRepository;
import com.xrom.ssh.repository.BonusRepository;
import com.xrom.ssh.repository.IntegralRepository;
import com.xrom.ssh.repository.MessageRepository;
import com.xrom.ssh.repository.PretreatmentAgentRepository;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.AgentTreeService;
import com.xrom.ssh.service.ApplyGoodsService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class ApplyGoodsServiceImpl implements ApplyGoodsService {
	@Autowired
	private ApplyGoodsRepository applyGoodsRepository;
	@Autowired
	private AgentTypeRepository agentTypeRepository;
	@Autowired
	private SysUserRepository sysUserRepository;
	@Autowired
	private BonusRepository bonusRepository;
	@Autowired
	private IntegralRepository integralRepository;
	@Autowired
	private AgentTreeRepository agentTreeRepository;
	@Autowired
	private PretreatmentAgentRepository pretreatmentAgentRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private AgentTreeService agentTreeService;

	@Override
	public Result findPage(ApplyGoods entity, int pageIndex, int pageSize,
			String orderBy) {

		Page<ApplyGoods> page = applyGoodsRepository.findPage(entity,
				pageIndex, pageSize, orderBy);
		List<ApplyGoodsInfo> result = new ArrayList<>();
		for (int i = 0; i < page.getResult().size(); i++) {
			Integer userId = page.getResult().get(i).getUserId();
			SysUser user = sysUserRepository.get(userId);
			ApplyGoodsInfo bonusInfo = new ApplyGoodsInfo();
			ApplyGoods bonus = page.getResult().get(i);
			BeanUtils.copyProperties(bonus, bonusInfo);
			if (user != null) {
				bonusInfo.setUserAccount(user.getAccountNumber());
				bonusInfo.setUserName(user.getUserName());
			}

			result.add(bonusInfo);
		}
		Page<ApplyGoodsInfo> page2 = new Page<>(page.getPageSize(),
				page.getTotalCount(), page.getPageNum());
		page2.setResult(result);
		return new Result<>("0", "获取成功", page2);
	}
	
	@Override
	public Result findPageUser(ApplyGoods entity, int pageIndex, int pageSize,
			String orderBy) {

		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		ApplyGoods entity2 = new ApplyGoods();
		entity2.setUserId(userId);
		Page<ApplyGoods> page = applyGoodsRepository.findPage(entity2,
				pageIndex, pageSize, orderBy);

		return new Result<>("0", "获取成功", page);
	}

	@Transactional
	@Override
	public Result save(Integer agentTypeId, String receiverAddress,
			String receiverName, String receiverPhone) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		SysUser user = sysUserRepository.get(userId);
		if(user.getIsActivate().equals(0)){
			return new Result<>("1", "对不起,你的帐号没有激活，请先完成激活再进行报单！", null);
		}
		AgentType type = agentTypeRepository.get(agentTypeId);

		AgentType userType = agentTypeRepository.get(user.getAgentTypeId());
		if (type.getAbleCount() < userType.getAbleCount()) {
			// 代理不能购买比他等级低的货品
			return new Result<>("1", "对不起，你的代理等级不能购买这件商品！", null);
		}
		ApplyGoods entity = new ApplyGoods();
		entity.setUserId(userId);
		entity.setapplyDate(new Date());
		entity.setReceiverAddress(receiverAddress);
		entity.setReceiverName(receiverName);
		entity.setReceiverPhone(receiverPhone);
		entity.setState(0);
		entity.setUserAccount(user.getAccountNumber());

		entity.setGoodsCount(type.getAbleCount());
		entity.setGoodsType(type.getName());
		entity.setTotalMoney(type.getTotalMoney());
		entity.setRemark(type.getRemark());
		entity.setAgentTypeId(agentTypeId);

		applyGoodsRepository.saveOrUpdate(entity);

		return new Result<ApplyGoods>("0", "操作成功！", entity);
	}
	@Transactional
	@Override
	public Result saveToUser(SysUser user, Integer agentTypeId,
			String receiverAddress, String receiverName, String receiverPhone) {

		ApplyGoods entity = new ApplyGoods();
		entity.setUserId(user.getId());
		entity.setapplyDate(new Date());
		entity.setReceiverAddress(receiverAddress);
		entity.setReceiverName(receiverName);
		entity.setReceiverPhone(receiverPhone);
		entity.setState(0);
		entity.setUserAccount(user.getAccountNumber());
		AgentType type = agentTypeRepository.get(agentTypeId);
		entity.setGoodsCount(type.getAbleCount());
		entity.setGoodsType(type.getName());
		entity.setTotalMoney(type.getTotalMoney());
		entity.setRemark(type.getRemark());
		entity.setAgentTypeId(agentTypeId);

		applyGoodsRepository.saveOrUpdate(entity);

		return new Result<ApplyGoods>("0", "操作成功！", entity);
	}
	
	@Override
	public Result get(Integer id) {
		return new Result<>("0", "获取成功", applyGoodsRepository.get(id));
	}

	@Transactional
	@Override
	public Result active(Integer id) {
		ApplyGoods entity = applyGoodsRepository.get(id);
		if (entity.getState() != 0) {
			// 已经确认过了
			return new Result<>();
		}
		entity.setState(1);// 已经确认付钱
		applyGoodsRepository.saveOrUpdate(entity);

		Integer userId = entity.getUserId();
		SysUser user = sysUserRepository.get(userId);
		if (user.getIsActivate().equals(0)) {
			// 报单确认后再把用户放进用户树
			PretreatmentAgent pretreatmentAgent = pretreatmentAgentRepository
					.getByUserId(userId);
			Integer parentUserIdsss = pretreatmentAgent.getTreeParentUserId();
			if (parentUserIdsss!=0) {
				SysUser parentUsersss = sysUserRepository.get(parentUserIdsss);
				agentTreeService.saveInAgentTree(pretreatmentAgent.getPosition(),
						parentUsersss, user);
			}else {
				// 新建一个独立的树，父ID为零
				AgentTree agentTree = new AgentTree();
				agentTree.setLeftPerformance(0);
				agentTree.setRightPerformance(0);
				agentTree.setUserId(userId);
				agentTree.setParentUserId(0);
				agentTreeRepository.save(agentTree);// 保存之后agentTree也会被保存有ID等属性值
			}
			user.setIsActivate(1);// 已经激活
			sysUserRepository.save(user);
			
			////////////////
			String ssss = "放置在你的右区。";
			if(pretreatmentAgent.getPosition().equals("left")){
				ssss = "放置在你的左区。";
			}else if(pretreatmentAgent.getPosition().equals("right")){
				ssss = "放置在你的左区。";
			}else {
				ssss = "自己做为一个独立的团队，不放置在左区右区。";
			}
			Message message = new Message();
			message.setReaded(false);
			message.setReceiveDate(new Date());
			message.setUserId(user.getRecommendUserId());
			String content = "代理:" + user.getUserName() + "("
					+ user.getAccountNumber() + ")" + "通过验证成功，正式成为你的代理。" + ssss;
			message.setContent(content);
			messageRepository.save(message);
			
			
			//////////////
		}

		// /////////每添加一个代理，对于直接代理来说，将获得奖金//////////////////
		// ////////////////////////////////////////////////////////

		AgentType agentType = agentTypeRepository.get(entity.getAgentTypeId());
		
		if (user.getRecommendUserId() != null && user.getRecommendUserId() != 0) {

			Bonus bonus = new Bonus();
			bonus.setBonusType("代理申单奖金");
			bonus.setObtainDate(new Date());
			bonus.setUserId(user.getRecommendUserId());// 直接推荐人的

			bonus.setMoney(agentType.getFirstRewardMoney());
			bonus.setAgentAccount(user.getAccountNumber());
			bonus.setAgentName(user.getUserName());
			bonus.setState(1);
			bonusRepository.saveOrUpdate(bonus);
			
			// 保存到用户的余额
			SysUser recommendUser = sysUserRepository.get(user
					.getRecommendUserId());
			Integer banance = recommendUser.getBalance();
			banance = banance == null ? 0 : banance;
			banance += agentType.getFirstRewardMoney();
			recommendUser.setBalance(banance);
			sysUserRepository.saveOrUpdate(recommendUser);
			
			// //////////////////
			// 发一条消息告诉用户它获得了这个奖金
			Message message = new Message();
			message.setReaded(false);
			message.setReceiveDate(new Date());
			message.setUserId(recommendUser.getId());
			String content = "代理:" + user.getUserName() + "("
					+ user.getAccountNumber() + ")" + "申单成功，您获得"
					+ agentType.getFirstRewardMoney() + "奖金，已经成功发放到您的帐户。";
			message.setContent(content);
			messageRepository.save(message);
			// //////////////////
		}
		// ////////////////////////////////////////////////////////
		// ////// //////////

		// ///////////////////对于所有的上层，都将获得积分,耗时操作。。。////////////////////////
		if (agentType.getIntegral() != 0) {
			// 找到树的所有父亲，连跟拔起全给它奖历
			AgentTree agentTree = agentTreeRepository.getByUserId(userId);
			if (agentTree == null) {
				agentTree = new AgentTree();
				agentTree.setUserId(userId);
				agentTree.setLeftPerformance(0);
				agentTree.setRightPerformance(0);
				agentTree.setParentUserId(agentTreeRepository
						.getMyParentId(userId));
				agentTreeRepository.save(agentTree);

			}
			
			// 保存用户的积分是加在了左边还是右边
			Map<Integer, String> userIdLeftOrRight = new HashMap<Integer, String>();
			List<SysUser> userList = new ArrayList<>();
			// userList.add(user);// 他自己有积分
			Integer parentUserId = agentTree.getParentUserId();
			while (true) {
				if (parentUserId == null || parentUserId.equals(0)) {
					break;
				}
				SysUser parentUser = sysUserRepository.get(parentUserId);
				if (parentUser == null) {
					break;
				}
				userList.add(parentUser);
				Integer childrenUserId = agentTree.getUserId();
				agentTree = agentTreeRepository.getByUserId(parentUserId);
//				System.out.println(childrenUserId+ "   "+ agentTree.getLeftUserId()+ "  " + agentTree.getRightUserId());
//				System.out.println(childrenUserId == agentTree.getLeftUserId());
				if (childrenUserId.equals(agentTree.getLeftUserId())) {
					// 加到左区绩效
					userIdLeftOrRight.put(parentUserId, "left");
					Integer leftP = agentTree.getLeftPerformance();
					leftP = leftP == null ? 0 : leftP;
					agentTree.setLeftPerformance(leftP
							+ agentType.getIntegral());
				} else if (childrenUserId.equals(agentTree.getRightUserId())) {
					// 加到右区绩效
					userIdLeftOrRight.put(parentUserId, "right");
					Integer rightP = agentTree.getRightPerformance();
					rightP = rightP == null ? 0 : rightP;
					agentTree.setRightPerformance(rightP
							+ agentType.getIntegral());
				}
				agentTreeRepository.saveOrUpdate(agentTree);
				parentUserId = agentTree.getParentUserId();

			}
			// 更新用户的总积分和积分记录
			for (int i = 0; i < userList.size(); i++) {
				Integral integral = new Integral();
				integral.setAmount(agentType.getIntegral());
				integral.setReceiveDate(new Date());
				integral.setState(1);
				integral.setType("代理申单奖励");
				integral.setUserId(userList.get(i).getId());
				integral.setPosition(userIdLeftOrRight.get(userList.get(i).getId()));
				integralRepository.save(integral);
				Integer userIntegral = userList.get(i).getIntegral();
				userIntegral = userIntegral == null ? 0 : userIntegral;
				userIntegral += agentType.getIntegral();
				userList.get(i).setIntegral(userIntegral);
				sysUserRepository.saveOrUpdate(userList.get(i));
				// //////////////////
				// 发一条消息告诉用户它获得了这个积分
				String ssssss = "左区";
				if("right".equals(integral.getPosition())){
					ssssss = "右区";
				}
				Message message = new Message();
				message.setReaded(false);
				message.setReceiveDate(new Date());
				message.setUserId(userList.get(i).getId());
				String content = "代理:" + user.getUserName() + "("
						+ user.getAccountNumber() + ")" + "申单成功，您"+ssssss+"获得"
						+ agentType.getIntegral() + "积分。";
				message.setContent(content);
				messageRepository.save(message);
				// //////////////////
			}

		}

		// //////////////////////////////////////////
		
		//会员升级功能，如果是会员的第二次申单，而且
		////////////////////////////////////////////
		
		
		Long count = applyGoodsRepository.getUserApplyCount(user.getId());
		if(count.equals(2)) { //第二次有效的申单
			AgentType a = agentTypeRepository.get(user.getAgentTypeId());
			AgentType b = agentTypeRepository.get(entity.getAgentTypeId());
			if(a.getAbleCount()<b.getAbleCount()){
				user.setAgentTypeId(entity.getAgentTypeId());//变成这个的代理
				sysUserRepository.saveOrUpdate(user);
			}
			
		}
		
		
		
		
		return new Result<>("0", "操作成功！", null);
	}
}
