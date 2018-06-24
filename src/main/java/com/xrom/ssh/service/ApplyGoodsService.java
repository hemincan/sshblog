package com.xrom.ssh.service;

import com.xrom.ssh.entity.ApplyGoods;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.util.Result;

public interface ApplyGoodsService {

	Result findPage(ApplyGoods entity, int pageIndex, int pageSize,
			String orderBy);

	Result get(Integer id);


	Result save(Integer agentTypeId, String receiverAddress,
			String receiverName, String receiverPhone);

	Result active(Integer id);

	Result findPageUser(ApplyGoods entity, int pageIndex, int pageSize,
			String orderBy);

	Result saveToUser(SysUser user, Integer agentTypeId,
			String receiverAddress, String receiverName, String receiverPhone);

}
