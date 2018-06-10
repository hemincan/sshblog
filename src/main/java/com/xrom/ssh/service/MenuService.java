package com.xrom.ssh.service;

import com.xrom.ssh.util.Result;

public interface MenuService {

	Result getUserMenus();

	
	Result deleteMenu(Integer id);

	Result getMenu(Integer id);

	Result saveMenu(Integer id, String icon, String menuName, String parentId,
			String url);

}
