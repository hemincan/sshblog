package com.xrom.ssh.repository;

import java.util.List;

import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.SysUser;

public interface AgentTreeRepository extends DomainRepository<AgentTree> {
	
	public AgentTree getByUserId(Integer userId);
	
	public Integer getMyParentId(Integer myId);

	List<SysUser> findTopUser(int pageNum, int pageSize, String orderBy);
}
