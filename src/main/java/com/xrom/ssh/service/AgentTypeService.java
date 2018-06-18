package com.xrom.ssh.service;

import com.xrom.ssh.entity.AgentType;
import com.xrom.ssh.util.Result;

public interface AgentTypeService {

	Result delete(Integer id);

	Result get(Integer id);


	Result findPage(AgentType entity, int pageIndex, int pageSize,
			String orderBy);


	Result saveOrUpdate(Integer id, Integer ableCount, Integer integral,
			String name, Integer totalMoney, Double collisionPer,
			Integer firstRewardMoney, String remark, Integer topReward);

}
