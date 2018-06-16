package com.xrom.ssh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.dto.bonus.BonusInfo;
import com.xrom.ssh.dto.integral.IntegralInfo;
import com.xrom.ssh.dto.withdraw.WithdrawInfo;
import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.AgentType;
import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.Integral;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.entity.Withdraw;
import com.xrom.ssh.repository.AgentTreeRepository;
import com.xrom.ssh.repository.AgentTypeRepository;
import com.xrom.ssh.repository.BonusRepository;
import com.xrom.ssh.repository.IntegralRepository;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.BounsService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class BounsServiceImpl implements BounsService {
	@Autowired
	private BonusRepository bonusRepository;
	@Autowired
	private IntegralRepository integralRepository;
	@Autowired
	private SysUserRepository userRepository;
	@Autowired
	private AgentTreeRepository agentTreeRepository;
	@Autowired
	private AgentTypeRepository agentTypeRepository;
	
	@Override
	public Result findPage(Bonus entity, int pageIndex, int pageSize,
			String orderBy) {

		Page<Bonus> page = bonusRepository.findPage(entity, pageIndex,
				pageSize, orderBy);
		List<BonusInfo> result = new ArrayList<>();
		for (int i = 0; i < page.getResult().size(); i++) {
			Integer userId = page.getResult().get(i).getUserId();
			SysUser user = userRepository.get(userId);
			BonusInfo bonusInfo = new BonusInfo();
			Bonus bonus = page.getResult().get(i);
			BeanUtils.copyProperties(bonus, bonusInfo);
			bonusInfo.setUserAccount(user.getAccountNumber());
			bonusInfo.setUserName(user.getUserName());
			result.add(bonusInfo);
		}
		Page<BonusInfo> page2 = new Page<>(page.getPageSize(),
				page.getTotalCount(), page.getPageNum());
		page2.setResult(result);
		return new Result<>("0", "获取成功", page2);
	}

	@Override
	public Result get(Integer id) {
		return new Result<>("0", "获取成功", bonusRepository.get(id));
	}

	@Override
	public Result findIntegralPage(Integral entity, int pageIndex,
			int pageSize, String orderBy) {
		Page<Integral> page = integralRepository.findPage(entity, pageIndex,
				pageSize, orderBy);
		List<IntegralInfo> result = new ArrayList<>();
		for (int i = 0; i < page.getResult().size(); i++) {
			Integer userId = page.getResult().get(i).getUserId();
			SysUser user = userRepository.get(userId);
			IntegralInfo bonusInfo = new IntegralInfo();
			Integral bonus = page.getResult().get(i);
			BeanUtils.copyProperties(bonus, bonusInfo);
			bonusInfo.setUserAccount(user.getAccountNumber());
			bonusInfo.setUserName(user.getUserName());
			result.add(bonusInfo);
		}
		Page<IntegralInfo> page2 = new Page<>(page.getPageSize(),
				page.getTotalCount(), page.getPageNum());
		page2.setResult(result);
		return new Result<>("0", "获取成功", page2);
	}

	@Override
	public Result getIntegral(Integer id) {
		return new Result<>("0", "获取成功", integralRepository.get(id));
	}

	/*
	 * 计算对碰奖
	 */
	@Override
	public void caculateCollision() {
		Calendar now = Calendar.getInstance();
		// System.out.println("年: " + now.get(Calendar.YEAR));
		// System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
		// System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
		// System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
		// System.out.println("分: " + now.get(Calendar.MINUTE));
		// System.out.println("秒: " + now.get(Calendar.SECOND));
		Integer day = now.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
//		if (day == 8 || day == 18 || day == 28) {
		if(true){
			//计算对碰
			Integer userId = (Integer) SecurityUtils.getSubject().getSession()
					.getAttribute("userId");
			SysUser user = userRepository.get(userId);
			AgentTree agentTree = agentTreeRepository.getByUserId(userId);
			Integer left = agentTree.getLeftPerformance();
			Integer right = agentTree.getRightPerformance();
			left = left==null?0:left;
			right = right==null?0:right;
			Integer money = 0;
			if(left<right){
				agentTree.setLeftPerformance(0);
				agentTree.setRightPerformance(right-left);
				money=left;
			}else {
				agentTree.setLeftPerformance(left-right);
				agentTree.setRightPerformance(0);
				money=right;
			}
			
			
			agentTreeRepository.saveOrUpdate(agentTree);
			
			//money就是可以用来计算的钱
			
			AgentType agentType = agentTypeRepository.get(user.getAgentTypeId());
			
			money = (int) Math.round(money *agentType.getCollisionPer());
			if(money>agentType.getTopReward()){
				money = agentType.getTopReward();
			}
			// 发奖金
			Bonus bonus = new Bonus();
			bonus.setBonusType("对碰奖金");
			bonus.setObtainDate(new Date());
			bonus.setUserId(userId);
	
			bonus.setMoney(money);
//			bonus.setAgentAccount(user.getAccountNumber());
//			bonus.setAgentName(user.getUserName());
			bonus.setState(1);// 这个代理金未经过审核，并不是真的获得了代理金
			bonusRepository.saveOrUpdate(bonus);
			
			if(user.getBalance()==null){
				user.setBalance(0);
			}
			user.setBalance(user.getBalance()+money);
			userRepository.save(user);
			
		}
	}

}
