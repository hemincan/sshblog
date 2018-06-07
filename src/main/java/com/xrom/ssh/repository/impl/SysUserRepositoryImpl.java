package com.xrom.ssh.repository.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.SysUserRepository;
@Repository
public class SysUserRepositoryImpl extends CommonRepositoryImpl<SysUser> implements SysUserRepository{
	
	
	
	@Override
	public SysUser getByAccount(String account){
		Session session = this.getCurrentSession();
		StringBuffer sb = new StringBuffer();
//		sb = "from " + SysUser.class.getName() + " as user where user. " 
		Query query = session.createQuery("");
		return null;
	}
}
