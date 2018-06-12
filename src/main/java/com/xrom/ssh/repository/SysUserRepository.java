package com.xrom.ssh.repository;

import java.util.List;

import com.xrom.ssh.entity.SysUser;

public interface SysUserRepository extends DomainRepository<SysUser>{

	SysUser getByAccount(String account);

	List<SysUser> getUserByRecommendUser(Integer userId);
}
