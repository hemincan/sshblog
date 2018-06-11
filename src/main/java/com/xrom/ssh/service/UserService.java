package com.xrom.ssh.service;

import java.util.Set;

import com.xrom.ssh.util.Result;

public interface UserService {

	Result login(String account, String password);

	Result register(String userName, String userPassword, String identityCard,
			String phone);

	Result alertPassword(String account, String oldPassword, String newPassword);

	Result getUserInfo(String account);
	
	Set<String> findUserRoles(String account); 
	
	Set<String> findUserPermisstions(String account); 
}
