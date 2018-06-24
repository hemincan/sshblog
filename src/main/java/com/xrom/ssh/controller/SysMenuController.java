package com.xrom.ssh.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.SysMenuService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/menu")
public class SysMenuController {

	 @Autowired(required=true)
	 private SysMenuService menuService;
	 
	 @RequiresRoles(value={"admin"})
	 @RequestMapping("/findUserMenus")
	 @ResponseBody
	 public Result findUserMenus(){
		 return menuService.getUserMenus();
	 }
	 @RequiresRoles(value={"superadmin"})
	 @RequestMapping("/saveOrUpdateMenu")
	 @ResponseBody
	 public Result addMenu(Integer id,String icon,String menuName,String parentId,String url){
		 
		 return menuService.saveMenu( id,icon, menuName, parentId, url);
	 }
	 @RequiresRoles(value={"superadmin"})
	 @RequestMapping("/deleteMenu")
	 @ResponseBody
	 public Result deleteMenu(Integer id){
		 
		 return menuService.deleteMenu(id);
	 }
	 @RequiresRoles(value={"superadmin"})
	 @RequestMapping("/getMenu")
	 @ResponseBody
	 public Result getMenu(Integer id){
		 return menuService.getMenu(id);
	 }
}
