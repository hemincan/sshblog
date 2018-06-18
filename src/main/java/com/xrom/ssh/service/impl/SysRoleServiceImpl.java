package com.xrom.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.entity.SysUserRole;
import com.xrom.ssh.repository.SysRoleRepository;
import com.xrom.ssh.repository.SysUserRoleRepository;
import com.xrom.ssh.service.SysRoleService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired(required = true)
	private SysRoleRepository sysRoleRepository;

	@Autowired
	private SysUserRoleRepository sysUserRoleRepository;
	
	@Override
	public Result findPage(SysRole entity, int pageIndex, int pageSize,
			String orderBy) {

		Page<SysRole> page = sysRoleRepository.findPage(entity, pageIndex,
				pageSize, orderBy);
		return new Result<>("0", "获取成功", page);
	}

	@Override
	public Result saveOrUpdateRole(Integer id, String remark, String roleName,
			String sortOrder, String enName) {
		SysRole role = new SysRole();
		role.setId(id);
		role.setRemark(remark);
		role.setRoleName(roleName);
		role.setSortOrder(sortOrder);
		role.setEnName(enName);
		sysRoleRepository.saveOrUpdate(role);
		return new Result<SysRole>("0", "操作成功！", role);
	}

	@Override
	public Result deleteRole(Integer id) {
		sysRoleRepository.delete(id);
		return new Result<>("0", "删除成功！", null);
	}

	@Override
	public Result getRole(Integer id) {
		return new Result<>("0", "获取成功", sysRoleRepository.get(id));
	}

	@Override
	public Result addRoleToUser(Integer userId,String ids) {
		String []idlist = ids.split(",");
		SysUserRole entity = new SysUserRole();
		entity.setSysUserId(userId);
		sysUserRoleRepository.delete(entity);//全删了
		if(idlist!=null){
			
			for (int i = 0; i < idlist.length; i++) {
				if("".equals(idlist[i])){
					continue;
				}
				Integer id = Integer.parseInt(idlist[i]);
			    entity = new SysUserRole();
			    entity.setRoleId(id);
			    entity.setSysUserId(userId);
			    sysUserRoleRepository.save(entity);
			}
		}
		return new Result<>("0", "保存成功", null);
	}
}
