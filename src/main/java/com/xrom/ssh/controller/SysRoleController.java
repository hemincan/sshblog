package com.xrom.ssh.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.service.SysRoleService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/role")
public class SysRoleController {
	@Autowired(required = true)
	private SysRoleService roleService;
	 @RequiresRoles(value={"superadmin"})
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return roleService.findPage(null, pageIndex, pageSize, orderBy);
	}
	 @RequiresRoles(value={"superadmin"})
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public Result addRole(Integer id, String remark, String roleName,
			String sortOrder, String enName) {

		return roleService.saveOrUpdateRole(id, remark, roleName, sortOrder,
				enName);
	}
	 @RequiresRoles(value={"superadmin"})
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteRole(Integer id) {

		return roleService.deleteRole(id);
	}
	 @RequiresRoles(value={"superadmin"})
	@RequestMapping("/get")
	@ResponseBody
	public Result getRole(Integer id) {
		return roleService.getRole(id);
	}
	 @RequiresRoles(value={"superadmin"})
	@RequestMapping("/addRoleToUser")
	@ResponseBody
	public Result addRoleToUser(Integer userId, String roleIds) {
		// roleIds 以，隔开的数字
		return roleService.addRoleToUser(userId, roleIds);
	}
}
