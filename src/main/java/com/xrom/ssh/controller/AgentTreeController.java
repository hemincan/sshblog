package com.xrom.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.AgentTreeService;
import com.xrom.ssh.util.Result;
/**
 * 代理的结构
 * @author Can
 *
 */
@Controller
@RequestMapping("/agentTree")
public class AgentTreeController {

	@Autowired(required = true)
	private AgentTreeService agentTreeService;
	
	
	//找出用户与直接推荐代理之间的关系
	@RequestMapping("/recommendedStructure")
	@ResponseBody
	public Result recommendedStructure() {
		
		return agentTreeService.recommendedStructure();
	}
	
	//找出用户与直接推荐代理之间的关系
	@RequestMapping("/treeStructure")
	@ResponseBody
	public Result treeStructure(String account) {
		
		return agentTreeService.treeStructure(account);
	}
}
