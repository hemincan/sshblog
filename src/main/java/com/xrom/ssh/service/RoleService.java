package com.xrom.ssh.service;

import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.util.Result;

public interface RoleService {

	Result saveOrUpdateRole(Integer id, String remark, String roleName,
			String sortOrder);

	Result deleteRole(Integer id);

	Result getRole(Integer id);

	Result<SysRole> findPage(SysRole sysRole, int pageIndex, int pageSize,
			String orderBy);

}
