package com.xrom.ssh.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.entity.Withdraw;
import com.xrom.ssh.service.BounsService;
import com.xrom.ssh.service.WithdrawService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController {
	@Autowired
	private WithdrawService withdrawService;

	 @RequiresRoles(value={"admin"})
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(Withdraw withdraw,@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return withdrawService.findPage(withdraw, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/findPageUser")
	@ResponseBody
	public Result findPageUser(Withdraw withdraw,@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return withdrawService.findPageUser(withdraw, pageIndex, pageSize, orderBy);
	}

	@RequestMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		return withdrawService.get(id);
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result get(Integer withdrawMoney, String bankName,
			String bankAddress, String bankCard, String bankUserName) {
		return withdrawService.save(withdrawMoney, bankName, bankAddress,
				bankCard, bankUserName);
	}
}
