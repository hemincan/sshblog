package com.xrom.ssh.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.AgentTypeService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/agentType")
public class AgentTypeController {
	@Autowired(required = true)
	private AgentTypeService agentTypeService;

	@RequiresRoles(value={"admin"})
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return agentTypeService.findPage(null, pageIndex, pageSize, orderBy);
	}
	@RequiresRoles(value={"admin"})
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public Result add(Integer id, Integer ableCount, Integer integral,
			String name, Integer totalMoney, Double collisionPer,
			Integer firstRewardMoney, String remark, Integer topReward) {

		return agentTypeService.saveOrUpdate(id, ableCount, integral, name,
				totalMoney, collisionPer, firstRewardMoney, remark, topReward);
	}

	@RequestMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		return agentTypeService.get(id);
	}
}
