package com.xrom.ssh.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.SysUserRepository;
@Repository
public class SysUserRepositoryImpl extends CommonRepositoryImpl<SysUser> implements SysUserRepository{
	
	
	public SysUser getByAccount(String account){
		if(account==null){
			return null;
		}
		SysUser sysUser = new SysUser();
		sysUser.setAccountNumber(account);
		List<SysUser> list = this.queryByEntity(sysUser);
		if (!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<SysUser> getUserByRecommendUser(Integer userId) {
		if(userId==null){
			return null;
		}
		SysUser sysUser = new SysUser();
		sysUser.setRecommendUserId(userId);
		List<SysUser> list = this.queryByEntity(sysUser);
		if (!list.isEmpty()){
			return list;
		}
		return null;
	}
}
