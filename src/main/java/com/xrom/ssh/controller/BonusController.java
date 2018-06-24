package com.xrom.ssh.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.BounsService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/bonus")
public class BonusController {
	
	@Autowired
	private BounsService bounsService;
	
	@RequiresRoles(value={"admin"})
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return bounsService.findPage(null, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/findPageUser")
	@ResponseBody
	public Result findPageUser(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return bounsService.findPageUser(null, pageIndex, pageSize, orderBy);
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		return bounsService.get(id);
	}
	@RequiresRoles(value={"admin"})
	@RequestMapping("/findIntegralPage")
	@ResponseBody
	public Result findIntegralPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return bounsService.findIntegralPage(null, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/findIntegralPageUser")
	@ResponseBody
	public Result findIntegralPageUser(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return bounsService.findIntegralPageUser(null, pageIndex, pageSize, orderBy);
	}

	@RequestMapping("/getIntegral")
	@ResponseBody
	public Result getIntegral(Integer id) {
		return bounsService.getIntegral(id);
	}
	
	
}
