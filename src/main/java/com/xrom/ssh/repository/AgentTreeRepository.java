package com.xrom.ssh.repository;

import com.xrom.ssh.entity.AgentTree;

public interface AgentTreeRepository extends DomainRepository<AgentTree> {
	
	public AgentTree getByUserId(Integer userId);
	
	public Integer getMyParentId(Integer myId);
}
