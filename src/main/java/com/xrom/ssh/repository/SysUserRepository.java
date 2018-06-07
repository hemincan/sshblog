package com.xrom.ssh.repository;

import com.xrom.ssh.entity.SysUser;

public interface SysUserRepository extends DomainRepository<SysUser>{

	SysUser getByAccount(String account);
}
