package com.xrom.ssh.repository;

import com.xrom.ssh.entity.PretreatmentAgent;

public interface PretreatmentAgentRepository extends DomainRepository<PretreatmentAgent>{

	PretreatmentAgent getByUserId(Integer userId);
}
