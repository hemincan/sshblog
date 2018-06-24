package com.xrom.ssh.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Array;
import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.repository.SysRoleRepository;
@Repository
public class SysRoleRepositoryImpl extends CommonRepositoryImpl<SysRole> implements SysRoleRepository{
	
	@Override
	public List<SysRole> queryByIDs(List<Integer> ids){
		Session session = getCurrentSession();
		if(ids.isEmpty()){
			return new ArrayList<>();
		}
		Query q = session.createQuery("from " + SysRole.class.getName() +" where id in (:list)");
		q.setParameterList("list", ids);
		return q.list();
	}
}
