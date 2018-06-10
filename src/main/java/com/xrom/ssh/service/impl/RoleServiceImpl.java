package com.xrom.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.repository.SysRoleRepository;
import com.xrom.ssh.service.RoleService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired(required = true)
	private SysRoleRepository sysRoleRepository;
	
	@Override
	public Result findPage(SysRole entity,int pageIndex,int pageSize,String orderBy){
		
		Page<SysRole> page = sysRoleRepository.findPage(entity, pageIndex, pageSize, orderBy);
		return new Result<>("0","获取成功",page);
	}
	
	@Override
	public Result saveOrUpdateRole(Integer id,String remark,String roleName,String sortOrder){
		SysRole role = new SysRole();
		role.setId(id);
		role.setRemark(remark);
		role.setRoleName(roleName);
		role.setSortOrder(sortOrder);
		sysRoleRepository.saveOrUpdate(role);
		return new Result<SysRole>("0","操作成功！",role);
	}
	@Override
	public Result deleteRole(Integer id) {
		sysRoleRepository.delete(id);
		return new Result<>("0","删除成功！",null);
	}
	@Override
	public Result getRole(Integer id){
		return new Result<>("0","获取成功",sysRoleRepository.get(id));
	}
}
