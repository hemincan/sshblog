package com.xrom.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.entity.SysMenu;
import com.xrom.ssh.repository.SysMenuRepository;
import com.xrom.ssh.service.MenuService;
import com.xrom.ssh.util.Result;
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired(required = true)
	private SysMenuRepository sysMenuRepository;
	
	@Override
	public Result getUserMenus() {
	
		 return new Result<List<SysMenu>>("0","获取成功！", sysMenuRepository.findAll());
	}
	@Override
	public Result saveMenu(Integer id,String icon,String menuName,String parentId,String url){
		SysMenu meun = new SysMenu();
		meun.setId(id);
		meun.setIcon(icon);
		meun.setMenuName(menuName);
		meun.setParentId(parentId);
		meun.setUrl(url);
		sysMenuRepository.saveOrUpdate(meun);
		return new Result<SysMenu>("0","操作成功！",meun);
	}
	@Override
	public Result deleteMenu(Integer id) {
		sysMenuRepository.delete(id);
		return new Result<>("0","删除成功！",null);
	}
	@Override
	public Result getMenu(Integer id){
		return new Result<>("0","获取成功",sysMenuRepository.get(id));
	}
	
 }
