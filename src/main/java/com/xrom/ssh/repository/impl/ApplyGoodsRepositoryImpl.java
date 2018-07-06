package com.xrom.ssh.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Array;
import com.xrom.ssh.entity.ApplyGoods;
import com.xrom.ssh.repository.ApplyGoodsRepository;

@Repository
public class ApplyGoodsRepositoryImpl extends CommonRepositoryImpl<ApplyGoods> implements ApplyGoodsRepository{

	@Override
	public List<ApplyGoods> findAllMyAgentSGoods(List<Integer> ids){
		
		if(ids==null||ids.isEmpty()){
			return new ArrayList<>();
		}
		Session session = getCurrentSession();
		
		Query q =session.createQuery("from " + ApplyGoods.class.getName() + " as c  where c.userId in (:alist)");
		q.setParameterList("alist", ids); 
		return q.list();
	}
	
	@Override
	public Long  getUserApplyCount(int userId){
		Session session = getCurrentSession();
		return  (Long) session.createQuery("select count(*) from " + ApplyGoods.class.getName() + " as c where c.userId = "+userId + " and c.state = 1").uniqueResult();
	}
}