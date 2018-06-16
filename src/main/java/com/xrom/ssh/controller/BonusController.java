package com.xrom.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return bounsService.findPage(null, pageIndex, pageSize, orderBy);
	}


	@RequestMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		return bounsService.get(id);
	}
	
	@RequestMapping("/findIntegralPage")
	@ResponseBody
	public Result findIntegralPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return bounsService.findIntegralPage(null, pageIndex, pageSize, orderBy);
	}


	@RequestMapping("/getIntegral")
	@ResponseBody
	public Result getIntegral(Integer id) {
		return bounsService.getIntegral(id);
	}
}
