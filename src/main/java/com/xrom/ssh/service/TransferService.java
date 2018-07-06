package com.xrom.ssh.service;

import com.xrom.ssh.entity.Transfer;
import com.xrom.ssh.util.Result;


public interface TransferService {

	Result findPageUser(Transfer entity, int pageIndex, int pageSize,
			String orderBy);

	Result get(Integer id);

	Result findPage(Transfer entity, int pageIndex, int pageSize, String orderBy);

	Result save(Integer amount, String toUserAccount, String mark);

	

	
}
