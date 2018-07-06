package com.xrom.ssh.service;

import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.util.Result;

public interface AgentTreeService {

	Result treeStructure(String account);

	Result recommendedStructure();


	void saveInAgentTree(String position, SysUser parentUser, SysUser user);

	Result statistics();

	
}
