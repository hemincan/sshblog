package com.xrom.ssh.service;

import java.util.Set;

import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.util.Result;

public interface SysUserService {

	Result login(String account, String password);


	Result alertPassword(String account, String oldPassword, String newPassword);

	Result getUserInfo(String account);
	
	Set<String> findUserRoles(String account); 
	
	Set<String> findUserPermisstions(String account);

	Result findPage(SysUser entity, int pageIndex, int pageSize, String orderBy);


	Result register(String userName, String userPassword, String identityCard,
			String phone, Integer userSex, String qqNumber,
			String recommendAccount, Integer agentTypeId);


	Result recommendedStructure(); 
}
