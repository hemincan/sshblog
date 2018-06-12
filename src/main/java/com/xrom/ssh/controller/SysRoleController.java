package com.xrom.ssh.controller;

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
	 @Autowired(required=true)
	 private SysRoleService roleService;
	 
	 @RequestMapping("/findPage")
	 @ResponseBody
	 public Result findPage(@RequestParam(defaultValue = "0")int pageIndex,@RequestParam(defaultValue = "15")int pageSize,String orderBy){
		 return roleService.findPage(null, pageIndex, pageSize, orderBy);
	 }
	 @RequestMapping("/saveOrUpdate")
	 @ResponseBody
	 public Result addRole(Integer id,String remark,String roleName,String sortOrder){
		 
		 return roleService.saveOrUpdateRole(id, remark, roleName, sortOrder);
	 }
	 @RequestMapping("/delete")
	 @ResponseBody
	 public Result deleteRole(Integer id){
		 
		 return roleService.deleteRole(id);
	 }
	 @RequestMapping("/get")
	 @ResponseBody
	 public Result getRole(Integer id){
		 return roleService.getRole(id);
	 }
}