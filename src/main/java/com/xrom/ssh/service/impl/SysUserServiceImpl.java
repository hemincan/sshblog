package com.xrom.ssh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.fabric.xmlrpc.base.Data;
import com.xrom.ssh.dto.user.UserInfoDTO;
import com.xrom.ssh.dto.user.UserTreeDTO;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.SysUserService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired(required = true)
	private SysUserRepository userRepository;

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
		return new Result<SysUser>("0", "登录成功！", sysUser);
	}

	@Override
	public Result register(String userName, String userPassword,
			String identityCard, String phone, Integer userSex,
			String qqNumber, String recommendAccount,Integer agentTypeId) {
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
		
		SysUser recommUser = userRepository.getByAccount(recommendAccount);
		user.setRecommendUserId(recommUser.getId());
		// 取一个7位数的用户号
		Long account = (long) ((Math.random() * 9 + 1) * 1000000);
		while (userRepository.getByAccount(account.toString()) != null) {
			account = (long) ((Math.random() * 9 + 1) * 1000000);
		}
		user.setAccountNumber(account.toString());
		userRepository.save(user);
		
		return new Result<>("0", "注册成功",user );
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
		if (!oldPassword.equals(user.getUserPassword())){
			return new Result<>("3", "旧密码不对", null);
		}
		user.setUserPassword(newPassword);
		userRepository.saveOrUpdate(user);
		return new Result<>("0", "修改成功", null);
	}
	@Override
	public Result getUserInfo(String account){
	
		SysUser user = userRepository.getByAccount(account);
		if(user == null) {
			return new Result<>("1","用户不存在",null);
		}
		UserInfoDTO dto = new UserInfoDTO();
		BeanUtils.copyProperties(user, dto);
		return new Result<UserInfoDTO>("0","获取成功",dto);
	}
	@Override
	public Result findPage(SysUser entity,int pageIndex,int pageSize,String orderBy){
		Page<SysUser> page = userRepository.findPage(entity, pageIndex, pageSize, orderBy);
		return new Result<>("0","获取成功",page);
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
	public Result recommendedStructure(){
		Subject currentUser = SecurityUtils.getSubject();
		String account = (String) currentUser.getSession().getAttribute("account");
		SysUser user = userRepository.getByAccount(account);
		List<SysUser> userList = userRepository.getUserByRecommendUser(user.getId());
		if(userList==null){
			userList = new ArrayList<>();
		}
		List<UserTreeDTO> result = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			UserTreeDTO dto = new UserTreeDTO();
			BeanUtils.copyProperties(userList.get(i), dto);
			result.add(dto);
		}
		UserTreeDTO dto = new UserTreeDTO(); 
		BeanUtils.copyProperties(user, dto);
		result.add(dto);
		return new Result<>("0","获取成功！",result);
		
	}
}
