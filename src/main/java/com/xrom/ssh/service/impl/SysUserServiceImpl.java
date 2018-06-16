package com.xrom.ssh.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.dto.user.UserInfoDTO;
import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.AgentType;
import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.AgentTreeRepository;
import com.xrom.ssh.repository.AgentTypeRepository;
import com.xrom.ssh.repository.BonusRepository;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.SysUserService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired(required = true)
	private SysUserRepository userRepository;

	@Autowired(required = true)
	private AgentTreeRepository agentTreeRepository;

	@Autowired
	private AgentTypeRepository agentTypeRepository;
	@Autowired
	private BonusRepository bonusRepository;
	@Override
	public Result<SysUser> login(String account, String password) {
		if (account == null || password == null) {
			return new Result<>("3", "请输入用户名与密码！", null);
		}
		SysUser sysUser = userRepository.getByAccount(account);
		if (sysUser == null) {
			return new Result<SysUser>("1", "用户不存在！", null);
		}
		if (!password.equals(sysUser.getUserPassword())) {
			// 密码不匹配
			return new Result<SysUser>("2", "密码不匹配！", null);
		}
		// 登录成功
		sysUser.setLastLoginTime(new Date());
		userRepository.saveOrUpdate(sysUser);
		return new Result<SysUser>("0", "登录成功！", sysUser);
	}

	@Override
	public Result register(String userName, String userPassword,
			String identityCard, String phone, Integer userSex,
			String qqNumber, String recommendAccount, Integer agentTypeId,
			String treeParentAccount, String position,String address) {
		SysUser user = new SysUser();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setIdentityCard(identityCard);
		user.setPhone(phone);
		user.setUserSex(userSex);
		user.setQqNumber(qqNumber);
		user.setAgentTypeId(agentTypeId);
		user.setLastLoginTime(new Date());
		user.setRegisterTime(new Date());
		user.setRealName(userName);
		user.setIsActivate(true);
		user.setSecondPassword("666666");
		user.setAddress(address);
		user.setBalance(0);
		user.setIsAdmin(false);
		SysUser recommUser = userRepository.getByAccount(recommendAccount);
		user.setRecommendUserId(recommUser.getId());
		// 取一个7位数的用户号
		Long account = (long) ((Math.random() * 9 + 1) * 1000000);
		while (userRepository.getByAccount(account.toString()) != null) {
			account = (long) ((Math.random() * 9 + 1) * 1000000);
		}
		user.setAccountNumber(account.toString());
		userRepository.save(user);

		if (treeParentAccount != null) {
			// 保存至用户数
			SysUser treeParentUser = userRepository
					.getByAccount(treeParentAccount);
			AgentTree agentTree = new AgentTree();
			agentTree.setLeftPerformance(0);
			agentTree.setRightPerformance(0);
			agentTree.setParentUserId(treeParentUser.getId());
			agentTree.setUserId(user.getId());
			agentTreeRepository.save(agentTree);
			// 保存至的treeParentAccount的孩子
			AgentTree parentTree = agentTreeRepository
					.getByUserId(treeParentUser.getId());
			if (parentTree == null) {
				parentTree = new AgentTree();
				parentTree.setUserId(treeParentUser.getId());
			}
			if ("left".equals(position)) {
				parentTree.setLeftUserId(user.getId());
			} else {
				parentTree.setRightUserId(user.getId());
			}
			agentTreeRepository.saveOrUpdate(parentTree);

		} else {
			// 如果父亲节点是空，就只能在指定的方向找一个空的结点来添加代理
			AgentTree agentTree = agentTreeRepository.getByUserId(recommUser
					.getId());// 获取推荐人的树的数据
			if (agentTree == null) {
				// 新建一个树
				agentTree = new AgentTree();
				agentTree.setLeftPerformance(0);
				agentTree.setRightPerformance(0);
				agentTree.setUserId(recommUser.getId());
				agentTree.setParentUserId(0);
				agentTreeRepository.save(agentTree);// 保存之后agentTree也会被保存有ID等属性值
			}
			if ("left".equals(position)) {
				if (agentTree.getLeftUserId() == null) {
					agentTree.setLeftUserId(user.getId());
					agentTreeRepository.saveOrUpdate(agentTree);
				} else {
					// 随便找个左边的树的空位置放进去
					saveInEmptyNode(agentTree.getLeftUserId(),user.getId());
				}
			} else {
				if (agentTree.getRightUserId() == null) {
					agentTree.setRightUserId(user.getId());
					agentTreeRepository.saveOrUpdate(agentTree);
				} else {
					// 随便找个右边的树的空位置放进去
					saveInEmptyNode(agentTree.getRightUserId(),user.getId());
				}
			}
		}
		
		
		return new Result<>("0", "注册成功", user);
	}

	private void saveInEmptyNode(Integer userId,Integer putUserId){
		AgentTree agentTree = agentTreeRepository.getByUserId(userId);
		// 新建一个树
		agentTree = new AgentTree();
		agentTree.setLeftPerformance(0);
		agentTree.setRightPerformance(0);
		agentTree.setUserId(userId);
		agentTreeRepository.save(agentTree);// 保存之后agentTree也会被保存有ID等属性值
		Boolean stop = false;
		while(stop==false){
			if(agentTree.getLeftUserId()==null){
				agentTree.setLeftUserId(putUserId);
				agentTreeRepository.saveOrUpdate(agentTree);
				stop=true;
			}else if(agentTree.getRightUserId()==null){
				agentTree.setRightUserId(putUserId);
				agentTreeRepository.saveOrUpdate(agentTree);
				stop=true;
			}
			// 一直放在左边
			agentTree = agentTreeRepository.getByUserId(agentTree.getLeftUserId());
		}
	}
	@Override
	public Result alertPassword(String account, String oldPassword,
			String newPassword) {
		if (oldPassword == null || newPassword == null || account == null) {
			return new Result<>("1", "你的输入不合法", null);
		}
		if (newPassword.length() < 6) {
			return new Result<>("2", "密码需要大于等于6位", null);
		}
		SysUser user = userRepository.getByAccount(account);
		if (!oldPassword.equals(user.getUserPassword())) {
			return new Result<>("3", "旧密码不对", null);
		}
		user.setUserPassword(newPassword);
		userRepository.saveOrUpdate(user);
		return new Result<>("0", "修改成功", null);
	}

	@Override
	public Result getUserInfo() {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
		SysUser user = userRepository.get(userId);
		if (user == null) {
			return new Result<>("1", "用户不存在", null);
		}
		UserInfoDTO dto = new UserInfoDTO();
		BeanUtils.copyProperties(user, dto);
		AgentTree agentTree = agentTreeRepository.getByUserId(userId);
		if(agentTree==null){
			dto.setLeftPerformance(0);
			dto.setRightPerformance(0);
		}else{
			dto.setLeftPerformance(agentTree.getLeftPerformance());
			dto.setRightPerformance(agentTree.getRightPerformance());
		}
		
		return new Result<UserInfoDTO>("0", "获取成功", dto);
	}

	@Override
	public Result findPage(SysUser entity, int pageIndex, int pageSize,
			String orderBy) {
		Page<SysUser> page = userRepository.findPage(entity, pageIndex,
				pageSize, orderBy);
		return new Result<>("0", "获取成功", page);
	}

	@Override
	public Set<String> findUserRoles(String account) {
		Set<String> roles = new HashSet<>();
		roles.add("test");
		return roles;
	}

	@Override
	public Set<String> findUserPermisstions(String account) {
		Set<String> permisstions = new HashSet<>();
		permisstions.add("testper");
		return permisstions;
	}
	@Override
	public Result updateInfo(String phone, String qqNumber, String address,
			String email, String bankName, String bankCard, String bankAddress){
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
		SysUser user = userRepository.get(userId);
		user.setPhone(phone);
		user.setQqNumber(qqNumber);
		user.setAddress(address);
		user.setEmail(email);
		user.setBankName(bankName);
		user.setBankAddress(bankAddress);
		user.setBankCard(bankCard);
		userRepository.saveOrUpdate(user);
		return new Result<>("0","更新成功",null);
	}

	@Override
	public Result findAdminUserPage(SysUser object, int pageIndex, int pageSize,
			String orderBy) {
		SysUser user = new SysUser();
		user.setIsAdmin(true);
		Page<SysUser> page = userRepository.findPage(user, pageIndex, pageSize, "id desc");
		return new Result<>("0", "获取成功", page);
	}
	

}
