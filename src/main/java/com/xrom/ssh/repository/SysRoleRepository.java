package com.xrom.ssh.repository;

import java.util.List;

import com.xrom.ssh.entity.SysRole;

public interface SysRoleRepository extends DomainRepository<SysRole>{

	List<SysRole> queryByIDs(List<Integer> ids);
}
