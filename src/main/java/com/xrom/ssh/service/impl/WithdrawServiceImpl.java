package com.xrom.ssh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.dto.bonus.BonusInfo;
import com.xrom.ssh.dto.withdraw.WithdrawInfo;
import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.entity.Withdraw;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.repository.WithdrawRepository;
import com.xrom.ssh.service.WithdrawService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class WithdrawServiceImpl implements WithdrawService {
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private SysUserRepository sysUserRepository;

	@Override
	public Result findPage(Withdraw entity, int pageIndex, int pageSize,
			String orderBy) {

		Page<Withdraw> page = withdrawRepository.findPage(entity, pageIndex,
				pageSize, orderBy);
		List<WithdrawInfo> result = new ArrayList<>();
		for (int i = 0; i < page.getResult().size(); i++) {
			Integer userId =page.getResult().get(i).getUserId();
			SysUser user = sysUserRepository.get(userId);
			WithdrawInfo bonusInfo = new WithdrawInfo();
			Withdraw bonus = page.getResult().get(i);
			BeanUtils.copyProperties(bonus, bonusInfo);
			bonusInfo.setUserAccount(user.getAccountNumber());
			bonusInfo.setUserName(user.getUserName());
			result.add(bonusInfo);
		}
		Page<WithdrawInfo> page2 = new Page<>(page.getPageSize(), page.getTotalCount(), page.getPageNum()); 
		page2.setResult(result);
		return new Result<>("0", "获取成功", page2);
	}
	@Override
	public Result findPageUser(Withdraw entity, int pageIndex, int pageSize,
			String orderBy) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		Withdraw entity2 = new Withdraw();
		entity2.setUserId(userId);
		Page<Withdraw> page = withdrawRepository.findPage(entity2, pageIndex,
				pageSize, orderBy);
		return new Result<>("0", "获取成功", page);
	}
	@Override
	public Result get(Integer id) {
		return new Result<>("0", "获取成功", withdrawRepository.get(id));
	}

	@Override
	public Result save(Integer withdrawMoney, String bankName,
			String bankAddress, String bankCard,String bankUserName) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		SysUser user = sysUserRepository.get(userId);
		if (user.getBalance() < withdrawMoney) {
			return new Result<>("1", "余额不足，不能提现", null);
		}
		Withdraw withdraw = new Withdraw();
		withdraw.setApplicationTime(new Date());
		withdraw.setPoundage((int) Math.round(withdrawMoney * 0.01));// 手续费
		withdraw.setState(0);
		withdraw.setUserId(userId);
		withdraw.setWithdrawAmount(withdrawMoney);
		withdraw.setRealAmount(withdrawMoney - withdraw.getPoundage());
		withdraw.setBankAddress(bankAddress);
		withdraw.setBankCard(bankCard);
		withdraw.setBankName(bankName);
		withdraw.setBankUserNane(bankUserName);
		withdraw.setUserAccount(user.getAccountNumber());
		withdrawRepository.save(withdraw);
		return new Result<>("0", "提现申请已经提交，请等待！", null);
	}
}
