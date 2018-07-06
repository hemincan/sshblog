package com.xrom.ssh.service;

import java.util.Set;

import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.util.Result;

public interface SysUserService {

	Result login(String account, String password);


	

	
	Set<String> findUserRoles(String account); 
	
	Set<String> findUserPermisstions(String account);

	Result findPage(SysUser entity, int pageIndex, int pageSize, String orderBy);




	Result getUserInfo();


	Result updateInfo(String phone, String qqNumber, String address,
			String email, String bankName, String bankCard, String bankAddress);


	Result findAdminUserPage(SysUser object, int pageIndex, int pageSize,
			String orderBy);


	Result alertPassword(String oldPassword, String newPassword);





	Result findNormalUserPage(SysUser object, int pageIndex, int pageSize,
			String orderBy);





	Result forbidOrUnforbid(Integer userId);





	SysUser getCurrentLoginUser();





	Result getUserName(String userAccount);





	Result register(String userName, String userPassword, String identityCard,
			String phone, Integer userSex, String qqNumber,
			String recommendAccount, Integer agentTypeId,
			String treeParentAccount, String position, String address,
			String userAccount);





	Result findTeamPage(SysUser object, int pageIndex, int pageSize,
			String orderBy);


}
