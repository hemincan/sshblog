package com.xrom.ssh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.dto.applygoods.ApplyGoodsInfo;
import com.xrom.ssh.dto.withdraw.WithdrawInfo;
import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.AgentType;
import com.xrom.ssh.entity.ApplyGoods;
import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.Integral;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.entity.Withdraw;
import com.xrom.ssh.repository.AgentTreeRepository;
import com.xrom.ssh.repository.AgentTypeRepository;
import com.xrom.ssh.repository.ApplyGoodsRepository;
import com.xrom.ssh.repository.BonusRepository;
import com.xrom.ssh.repository.IntegralRepository;
import com.xrom.ssh.repository.SysUserRepository;
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
			if(user!=null){
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
	public Result save(Integer agentTypeId, String receiverAddress,
			String receiverName, String receiverPhone) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		SysUser user = sysUserRepository.get(userId);
		ApplyGoods entity = new ApplyGoods();
		entity.setUserId(userId);
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

		applyGoodsRepository.saveOrUpdate(entity);

		return new Result<ApplyGoods>("0", "操作成功！", entity);
	}

	@Override
	public Result get(Integer id) {
		return new Result<>("0", "获取成功", applyGoodsRepository.get(id));
	}

	@Override
	public Result active(Integer id) {
		ApplyGoods entity = applyGoodsRepository.get(id);
		entity.setState(1);// 已经确认付钱
		applyGoodsRepository.saveOrUpdate(entity);

		// /////////每添加一个代理，对于直接代理来说，将获得奖金//////////////////
		// ////////////////////////////////////////////////////////
		Integer userId = entity.getUserId();
		SysUser user = sysUserRepository.get(userId);
		Integer agentTypeId = user.getAgentTypeId();
		AgentType agentType = agentTypeRepository.get(agentTypeId);
		if(user.getRecommendUserId()!=null&&user.getRecommendUserId()!=0){

			Bonus bonus = new Bonus();
			bonus.setBonusType("推荐代理奖金");
			bonus.setObtainDate(new Date());
			bonus.setUserId(user.getRecommendUserId());// 直接推荐人的
			
			bonus.setMoney(agentType.getFirstRewardMoney());
			bonus.setAgentAccount(user.getAccountNumber());
			bonus.setAgentName(user.getUserName());
			bonus.setState(1);// 这个代理金未经过审核，并不是真的获得了代理金
			bonusRepository.saveOrUpdate(bonus);
			
		}
		// ////////////////////////////////////////////////////////
		// ////// //////////

		// ///////////////////对于所有的上层，都将获得积分////////////////////////
		if (agentType.getIntegral() != 0) {
			// 找到树的所有父亲，连跟拔起全给它奖历
			AgentTree agentTree = agentTreeRepository.getByUserId(userId);
			if (agentTree == null) {
				agentTree = new AgentTree();
				agentTree.setUserId(userId);
				agentTree.setLeftPerformance(0);
				agentTree.setRightPerformance(0);
				agentTreeRepository.save(agentTree);
			}
			List<SysUser> userList = new ArrayList<>();
			// userList.add(user);//他自己有积分
			Integer parentUserId = agentTree.getParentUserId();
			while (true) {
				if (parentUserId == null) {
					break;
				}
				SysUser parentUser = sysUserRepository.get(parentUserId);
				if (parentUser == null) {
					break;
				}
				userList.add(parentUser);
				Integer childrenUserId = agentTree.getUserId();
				agentTree = agentTreeRepository.getByUserId(parentUserId);
				if (childrenUserId == agentTree.getLeftUserId()) {
					// 加到左区绩效
					Integer leftP = agentTree.getLeftPerformance();
					leftP = leftP == null ? 0 : leftP;
					agentTree.setLeftPerformance(leftP
							+ agentType.getIntegral());
				} else if (childrenUserId == agentTree.getRightUserId()) {
					// 加到右区绩效
					Integer rightP = agentTree.getRightPerformance();
					rightP = rightP == null ? 0 : rightP;
					agentTree.setRightPerformance(rightP
							+ agentType.getIntegral());
				}
				agentTreeRepository.save(agentTree);
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
				integralRepository.save(integral);
				Integer userIntegral = userList.get(i).getIntegral();
				userIntegral = userIntegral == null ? 0 : userIntegral;
				userIntegral += agentType.getIntegral();
				userList.get(i).setIntegral(userIntegral);
				sysUserRepository.saveOrUpdate(userList.get(i));
			}

		}

		// //////////////////////////////////////////

		return new Result<>("0", "操作成功！", null);
	}
}
