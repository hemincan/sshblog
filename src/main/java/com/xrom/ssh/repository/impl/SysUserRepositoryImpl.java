package com.xrom.ssh.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.SysUserRepository;
@Repository
public class SysUserRepositoryImpl extends CommonRepositoryImpl<SysUser> implements SysUserRepository{
	
	@Override
	public SysUser getByAccount(Long account){
		SysUser sysUser = new SysUser();
		sysUser.setAccountNumber(account);
		List<SysUser> list = this.queryByEntity(sysUser);
		if (!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}
