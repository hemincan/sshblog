package com.xrom.ssh.service;

import com.xrom.ssh.util.Result;

public interface AgentTreeService {

	Result treeStructure(String account);

	Result recommendedStructure();

	
}
