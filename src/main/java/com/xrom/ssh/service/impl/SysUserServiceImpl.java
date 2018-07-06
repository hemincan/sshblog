package com.xrom.ssh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xrom.ssh.dto.user.AdminUserInfo;
import com.xrom.ssh.dto.user.UserInfoDTO;
import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.ApplyGoods;
import com.xrom.ssh.entity.PretreatmentAgent;
import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.entity.SysUserRole;
import com.xrom.ssh.repository.AgentTreeRepository;
import com.xrom.ssh.repository.AgentTypeRepository;
import com.xrom.ssh.repository.BonusRepository;
import com.xrom.ssh.repository.PretreatmentAgentRepository;
import com.xrom.ssh.repository.SysRoleRepository;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.repository.SysUserRoleRepository;
import com.xrom.ssh.service.AgentTreeService;
import com.xrom.ssh.service.ApplyGoodsService;
import com.xrom.ssh.service.SysUserService;
import com.xrom.ssh.util.MD5;
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
	@Autowired
	private SysUserRoleRepository sysUserRoleRepository;

	@Autowired
	private SysRoleRepository sysRoleRepository;
	@Autowired
	private PretreatmentAgentRepository pretreatmentAgentRepository;

	@Autowired
	private ApplyGoodsService applyGoodsService;

	@Autowired
	private AgentTreeService agentTreeService;

	@Override
	public Result<SysUser> login(String account, String password) {
		if (account == null || password == null) {
			return new Result<>("3", "请输入用户名与密码！", null);
		}
		SysUser sysUser = userRepository.getByAccount(account);
		if (sysUser == null) {
			return new Result<SysUser>("1", "用户不存在！", null);
		}
		if (sysUser.getIsActivate().equals(2)) {
			return new Result<SysUser>("5", "帐号已经被禁用", null);
		}
		if (sysUser.getIsActivate().equals(0)) {
			return new Result<SysUser>("4", "帐号没有激活，请联系激活", null);
		}
		password = MD5.getMD5Str(password);
		// System.out.println(password);
		if (!password.equals(sysUser.getUserPassword())) {
			// 密码不匹配
			return new Result<SysUser>("2", "密码不匹配！", null);
		}
		// 登录成功
		sysUser.setLastLoginTime(new Date());
		userRepository.saveOrUpdate(sysUser);
		return new Result<SysUser>("0", "登录成功！", sysUser);
	}

	@Transactional
	@Override
	public Result register(String userName, String userPassword,
			String identityCard, String phone, Integer userSex,
			String qqNumber, String recommendAccount, Integer agentTypeId,
			String treeParentAccount, String position, String address,
			String userAccount) {
		SysUser user = new SysUser();
		user.setUserName(userName);
		user.setUserPassword(MD5.getMD5Str(userPassword));
		user.setIdentityCard(identityCard);
		user.setPhone(phone);
		user.setUserSex(userSex);
		user.setQqNumber(qqNumber);
		user.setAgentTypeId(agentTypeId);
		user.setLastLoginTime(new Date());
		user.setRegisterTime(new Date());
		user.setRealName(userName);
		user.setIsActivate(0);// 未激活
		user.setSecondPassword(MD5.getMD5Str("666666"));
		user.setAddress(address);
		user.setBalance(0);
		user.setIsAdmin(false);
		SysUser recommUser = userRepository.getByAccount(recommendAccount);
		if (recommUser.getIsActivate().equals(0)) {
			return new Result<>("1", "对不起,你的帐号没有激活，请先完成激活再进行操作！", null);
		}
		user.setRecommendUserId(recommUser.getId());
		if(userAccount!=null){
			userAccount = userAccount.replaceAll(" ", "");
		}
		if (userAccount == null || userAccount.equals("")) {
			
			// 取一个7位数的用户号
			Long account = (long) ((Math.random() * 9 + 1) * 1000000);
			while (userRepository.getByAccount(account.toString()) != null) {
				account = (long) ((Math.random() * 9 + 1) * 1000000);
			}
			user.setAccountNumber(account.toString());
		}else{
			if(userAccount.length()<7){
				return new Result<>("3", "帐号长度需要大于7", null);
			}
			else if(userRepository.getByAccount(userAccount)==null){
				user.setAccountNumber(userAccount);
			}else{
				return new Result<>("2", "对不起,这个帐号已经被使用了", null);
			}
			
		}
		

		userRepository.save(user);

		// 提交代理时顺便把订单提上
		applyGoodsService.saveToUser(user, agentTypeId, address, userName,
				phone);
		// /

		// 保存至预受理用户
		PretreatmentAgent pretreatmentAgent = new PretreatmentAgent();
		if ("left".equals(position)) {
			pretreatmentAgent.setPosition("left");
		} else if ("right".equals(position)) {
			pretreatmentAgent.setPosition("right");
		} else {
			pretreatmentAgent.setPosition("none");
		}
		pretreatmentAgent.setState(0);
		if (treeParentAccount != null) {
			SysUser treeParentUser = userRepository
					.getByAccount(treeParentAccount);
			pretreatmentAgent.setTreeParentUserId(treeParentUser.getId());
		} else {
			pretreatmentAgent.setTreeParentUserId(recommUser.getId());
		}
		pretreatmentAgent.setUserId(user.getId());
		if ("none".equals(position)) {
			// 不放在任何人的左右边，自己形成一个根
			pretreatmentAgent.setTreeParentUserId(0);
		}
		pretreatmentAgentRepository.save(pretreatmentAgent);

		return new Result<>("0", "注册成功", user);
	}

	@Transactional
	@Override
	public Result alertPassword(String oldPassword, String newPassword) {
		if (oldPassword == null || newPassword == null) {
			return new Result<>("1", "你的输入不合法", null);
		}
		if (newPassword.length() < 6) {
			return new Result<>("2", "密码需要大于等于6位", null);
		}
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		SysUser user = userRepository.get(userId);
		oldPassword = MD5.getMD5Str(oldPassword);
		if (!oldPassword.equals(user.getUserPassword())) {
			return new Result<>("3", "旧密码不对", null);
		}
		user.setUserPassword(MD5.getMD5Str(newPassword));
		userRepository.saveOrUpdate(user);
		return new Result<>("0", "修改成功", null);
	}

	@Override
	public Result getUserName(String userAccount) {

		return new Result<>("0", "获取成功", userRepository.getByAccount(
				userAccount).getUserName());
	}

	@Override
	public Result getUserInfo() {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		SysUser user = userRepository.get(userId);
		if (user == null) {
			return new Result<>("1", "用户不存在", null);
		}
		UserInfoDTO dto = new UserInfoDTO();
		BeanUtils.copyProperties(user, dto);
		AgentTree agentTree = agentTreeRepository.getByUserId(userId);
		if (agentTree == null) {
			dto.setLeftPerformance(0);
			dto.setRightPerformance(0);
		} else {
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
		SysUserRole entity = new SysUserRole();
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		entity.setSysUserId(userId);
		List<SysUserRole> sysUserRoles = sysUserRoleRepository
				.queryByEntity(entity);
		List<Integer> roleIds = new ArrayList<>();
		for (int j = 0; j < sysUserRoles.size(); j++) {
			roleIds.add(sysUserRoles.get(j).getRoleId());
		}
		List<SysRole> roleList = sysRoleRepository.queryByIDs(roleIds);
		for (int i = 0; i < roleList.size(); i++) {
			roles.add(roleList.get(i).getEnName());
		}
		return roles;
	}

	@Override
	public Set<String> findUserPermisstions(String account) {
		Set<String> permisstions = new HashSet<>();
		permisstions.add("testper");
		return permisstions;
	}

	@Transactional
	@Override
	public Result updateInfo(String phone, String qqNumber, String address,
			String email, String bankName, String bankCard, String bankAddress) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		SysUser user = userRepository.get(userId);
		user.setPhone(phone);
		user.setQqNumber(qqNumber);
		user.setAddress(address);
		user.setEmail(email);
		user.setBankName(bankName);
		user.setBankAddress(bankAddress);
		user.setBankCard(bankCard);
		userRepository.saveOrUpdate(user);
		return new Result<>("0", "更新成功", null);
	}

	@Override
	public Result findAdminUserPage(SysUser object, int pageIndex,
			int pageSize, String orderBy) {
		SysUser user = new SysUser();
		user.setIsAdmin(true);
		Page<SysUser> page = userRepository.findPage(user, pageIndex, pageSize,
				"id desc");

		List<AdminUserInfo> result = new ArrayList<>();
		for (int i = 0; i < page.getResult().size(); i++) {

			AdminUserInfo bonusInfo = new AdminUserInfo();
			SysUser bonus = page.getResult().get(i);
			BeanUtils.copyProperties(bonus, bonusInfo);
			SysUserRole entity = new SysUserRole();
			entity.setSysUserId(bonus.getId());
			List<SysUserRole> sysUserRoles = sysUserRoleRepository
					.queryByEntity(entity);
			List<Integer> roleIds = new ArrayList<>();
			for (int j = 0; j < sysUserRoles.size(); j++) {
				roleIds.add(sysUserRoles.get(j).getRoleId());
			}
			List<SysRole> roleList = sysRoleRepository.queryByIDs(roleIds);
			// System.out.println(roleIds.get(0));
			// System.out.println(roleIds.get(1));
			bonusInfo.setRoleList(roleList);
			result.add(bonusInfo);
		}
		Page<AdminUserInfo> page2 = new Page<>(page.getPageSize(),
				page.getTotalCount(), page.getPageNum());
		page2.setResult(result);

		return new Result<>("0", "获取成功", page2);
	}

	@Override
	public Result findNormalUserPage(SysUser object, int pageIndex,
			int pageSize, String orderBy) {
		SysUser user = new SysUser();
		BeanUtils.copyProperties(object, user);
		user.setIsAdmin(false);
		Page<SysUser> page = userRepository.findPage(user, pageIndex, pageSize,
				"id desc");

		return new Result<>("0", "获取成功", page);
	}

	/**
	 * 从当前session中查出当前已经登录的用户信息
	 */
	@Override
	public SysUser getCurrentLoginUser() {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		return userRepository.get(userId);
	}

	/**
	 * 如果没有处理禁用状态就禁用，不然就解禁用
	 */
	@Transactional
	@Override
	public Result forbidOrUnforbid(Integer userId) {

		SysUser user = userRepository.get(userId);
		if (user.getIsActivate().equals(1) ) {
			user.setIsActivate(2);// 禁用
		} else if (user.getIsActivate().equals(2)) {
			user.setIsActivate(1);// 解除禁用
		}
		// 未激活状态下不支持禁用
		userRepository.saveOrUpdate(user);
		return new Result<>("0", "操作成功", null);

	}
	@Override
	public Result findTeamPage(SysUser object, int pageIndex,
			int pageSize, String orderBy){
		//这个方法没有实现分页的
		List<SysUser> list = agentTreeRepository.findTopUser(pageIndex, pageSize, orderBy);
		Page<SysUser> page = new Page<SysUser>(list.size(), (long)list.size(), 0);
		page.setResult(list);
		return new Result<>("0","获取成功",page);
	}
}
