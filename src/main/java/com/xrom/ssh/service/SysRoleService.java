package com.xrom.ssh.service;

import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.util.Result;

public interface SysRoleService {

	
	Result deleteRole(Integer id);

	Result getRole(Integer id);

	Result<SysRole> findPage(SysRole sysRole, int pageIndex, int pageSize,
			String orderBy);

	Result saveOrUpdateRole(Integer id, String remark, String roleName,
			String sortOrder, String enName);


	Result addRoleToUser(Integer userId, String ids);

}
