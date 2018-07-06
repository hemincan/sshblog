package com.xrom.ssh.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.ApplyGoods;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.AgentTreeRepository;

@Repository
public class AgentTreeRepositoryImpl extends CommonRepositoryImpl<AgentTree>
		implements AgentTreeRepository {

	@Override
	public AgentTree getByUserId(Integer userId) {
		AgentTree agentTree = new AgentTree();
		agentTree.setUserId(userId);
		List<AgentTree> list = this.queryByEntity(agentTree);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer getMyParentId(Integer myId) {
		AgentTree agentTree = new AgentTree();
		agentTree.setLeftUserId(myId);
		List<AgentTree> list = this.queryByEntity(agentTree);
		if (list != null && !list.isEmpty()) {
			return list.get(0).getUserId();
		}
		
		agentTree = new AgentTree();
		agentTree.setRightUserId(myId);
		list = this.queryByEntity(agentTree);
		if (list != null && !list.isEmpty()) {
			return list.get(0).getUserId();
		}
		return 0;
	}
	@Override
	public List<SysUser> findTopUser(int pageNum, int pageSize, String orderBy){
		if(orderBy!=null&&orderBy.replaceAll(" ", "").equals("")){
			orderBy = " order by " + orderBy;
		}else {
			orderBy = " order by id desc";
		}
		Query q = getCurrentSession().createQuery("from "+ AgentTree.class.getName()+" as c where c.parentUserId=0 " + orderBy);

		List<AgentTree> agentTrees = q.list();
		
		List<Integer> userIDs = new ArrayList<>();
		for (int i = 0; i < agentTrees.size(); i++) {
			userIDs.add(agentTrees.get(i).getUserId());
			
		}
		Query q2 =getCurrentSession().createQuery("from " + SysUser.class.getName() + " as c  where c.id in (:alist)");
		q2.setParameterList("alist", userIDs); 
		return q2.list();
	}

}
