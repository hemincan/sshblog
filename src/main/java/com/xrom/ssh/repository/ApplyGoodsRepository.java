package com.xrom.ssh.repository;

import java.util.List;

import com.xrom.ssh.entity.ApplyGoods;

public interface ApplyGoodsRepository extends DomainRepository<ApplyGoods>{

	

	List<ApplyGoods> findAllMyAgentSGoods(List<Integer> ids);

	
	Long getUserApplyCount(int userId);

}
