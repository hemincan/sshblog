package com.xrom.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.service.MenuService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/menu")
public class MenuController {

	 @Autowired(required=true)
	 private MenuService menuService;
	 
	 @RequestMapping("/findUserMenus")
	 @ResponseBody
	 public Result findUserMenus(){
		 return menuService.getUserMenus();
	 }
	 @RequestMapping("/saveOrUpdateMenu")
	 @ResponseBody
	 public Result addMenu(Integer id,String icon,String menuName,String parentId,String url){
		 
		 return menuService.saveMenu( id,icon, menuName, parentId, url);
	 }
	 @RequestMapping("/deleteMenu")
	 @ResponseBody
	 public Result deleteMenu(Integer id){
		 
		 return menuService.deleteMenu(id);
	 }
	 @RequestMapping("/getMenu")
	 @ResponseBody
	 public Result getMenu(Integer id){
		 return menuService.getMenu(id);
	 }
}
