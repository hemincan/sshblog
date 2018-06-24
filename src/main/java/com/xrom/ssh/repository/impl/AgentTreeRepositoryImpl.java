package com.xrom.ssh.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xrom.ssh.entity.AgentTree;
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

}
