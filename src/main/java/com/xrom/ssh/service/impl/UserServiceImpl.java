package com.xrom.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.UserService;
import com.xrom.ssh.util.Result;
@Service
public class UserServiceImpl implements UserService {

	@Autowired(required = true)
	private SysUserRepository userRepository;
	
	@Override
	public Result login(String account, String password){
		Long accountLong = Long.parseLong(account);
		SysUser sysUser = userRepository.getByAccount(accountLong);
		if(sysUser == null) {
			return new Result("1", "用户不存在！", null);
		}
		if(!password.equals(sysUser.getUserPassword())) {
			//密码不匹配
			return new Result("2", "密码不匹配！", null);
		}
		// 登录成功
		return new Result("0", "登录成功！", sysUser);
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
		Long account = (long) ((Math.random()*9+1)*1000000);
		while(userRepository.getByAccount(account) != null) {
			account = (long) ((Math.random()*9+1)*1000000);
		}
		user.setAccountNumber(account);
		userRepository.save(user);
		return new Result("0", "注册成功", "你的帐呈为:" + account);
	}
}
