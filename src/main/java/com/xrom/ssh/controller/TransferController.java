package com.xrom.ssh.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.TransferService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/transfer")
public class TransferController {

	@Autowired(required = true)
	private TransferService transferService;
	
	

	@RequiresRoles(value={"admin"})
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return transferService.findPage(null, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/findPageUser")
	@ResponseBody
	public Result findPageUser(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return transferService.findPageUser(null, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		return transferService.get(id);
	}
	@RequestMapping("/save")
	@ResponseBody
	public Result get(Integer amount,String toUserAccount,String mark) {
		return transferService.save(amount, toUserAccount, mark);
	}
}
