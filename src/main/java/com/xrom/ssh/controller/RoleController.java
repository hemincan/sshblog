package com.xrom.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.service.RoleService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/role")
public class RoleController {
	 @Autowired(required=true)
	 private RoleService roleService;
	 
	 @RequestMapping("/findPage")
	 @ResponseBody
	 public Result findPage(@RequestBody SysRole entity,int pageIndex,int pageSize,String orderBy){
		 return roleService.findPage(entity, pageIndex, pageSize, orderBy);
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
