package com.xrom.ssh.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.dto.user.UserInfoDTO;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.UserService;
import com.xrom.ssh.util.Result;

@Service
public class UserServiceImpl implements UserService {

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
			String identityCard, String phone) {
		SysUser user = new SysUser();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setIdentityCard(identityCard);
		user.setPhone(phone);
		// 取一个7位数的用户号
		Long account = (long) ((Math.random() * 9 + 1) * 1000000);
		while (userRepository.getByAccount(account.toString()) != null) {
			account = (long) ((Math.random() * 9 + 1) * 1000000);
		}
		user.setAccountNumber(account.toString());
		userRepository.save(user);
		return new Result<>("0", "注册成功", "你的帐呈为:" + account);
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
}
