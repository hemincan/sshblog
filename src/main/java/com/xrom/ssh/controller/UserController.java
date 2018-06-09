package com.xrom.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.UserService;
import com.xrom.ssh.util.Result;

@Controller
public class UserController {

	 @Autowired(required=true)
	 private UserService userService;
	 
	 @RequestMapping(value = "/user/login")
	 @ResponseBody
	 public Result login(String account, String password){
		 return userService.login(account, password);
	 }
	 @RequestMapping("/user/register")
	 @ResponseBody
	 public Result register(String userName, String userPassword,
			String identityCard, String phone){
		 return  userService.register(userName, userPassword, identityCard, phone);
	 }
}
