package com.xrom.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.MessageService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return messageService.findPage(null, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		return messageService.get(id);
	}
}
