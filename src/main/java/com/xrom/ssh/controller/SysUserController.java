package com.xrom.ssh.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.BounsService;
import com.xrom.ssh.service.SysUserService;
import com.xrom.ssh.util.Result;

@Controller
public class SysUserController {

	@Autowired(required = true)
	private SysUserService userService;
	@Autowired
	private BounsService bounsService;
	@RequestMapping(value = "/user/login")
	@ResponseBody
	public Result login(HttpServletRequest request,
			@RequestParam(required = true) String account,
			@RequestParam(required = true) String password) {
		/**********************/
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(account,
				password);
		try {
			currentUser.login(token);
			token.setRememberMe(true);
			
			currentUser.getSession().setAttribute("account", account);
		} catch (Exception e) {
			// 返回登录时的错误信息
			return (Result) currentUser.getSession().getAttribute("logininfo");
		}
		// if (currentUser.isPermitted("testper")) {
		// // 有权限
		// System.out.println("有权限");
		// } else {
		// // 无权限
		// System.out.println("无权限");
		// }
		/*********************/
		return (Result) currentUser.getSession().getAttribute("logininfo");
	}

	// 代理注册代理帐号
	@RequestMapping("/user/register")
	@ResponseBody
	public Result register(String userName, String userPassword,
			String identityCard, String phone, Integer userSex,
			String qqNumber, String recommendAccount, Integer agentTypeId,
			String treeParentAccount, String position, String address) {
		return userService.register(userName, userPassword, identityCard,
				phone, userSex, qqNumber, recommendAccount, agentTypeId,
				treeParentAccount, position, address);
	}

	@RequestMapping("/user/updateInfo")
	@ResponseBody
	public Result updateInfo(String phone, String qqNumber, String address,
			String email, String bankName, String bankCard, String bankAddress) {
			return userService.updateInfo(phone, qqNumber, address, email, bankName, bankCard, bankAddress);
	}

	@RequestMapping("/user/alertPassword")
	@ResponseBody
	public Result alertPassword(String account, String oldPassword,
			String newPassword) {
		return userService.alertPassword(account, oldPassword, newPassword);
	}

	@RequestMapping("/user/getUserInfo")
	@ResponseBody
	public Result getUserInfo() {
		return userService.getUserInfo();
	}

	@RequiresRoles({"superadmin"})
	@RequestMapping("/user/findAdminUserPage")
	@ResponseBody
	public Result findAdminUserPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return userService.findAdminUserPage(null, pageIndex, pageSize, orderBy);
	}

	/**
	 * shiro没有登录时使用这个链接来返回错误。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/authorityerror")
	@ResponseBody
	public Result loginerror(HttpServletRequest request) {
		return new Result<>("1000", "你没有登录，或者没有权限访问。", null);
	}
}
