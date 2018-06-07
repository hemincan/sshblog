package com.xrom.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired(required = true)
	private SysUserRepository userRepository;
	
	public void login(String account, String password){
		userRepository.delete(id)
	}
}
