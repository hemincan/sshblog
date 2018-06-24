package com.xrom.ssh.service;

import com.xrom.ssh.entity.Withdraw;
import com.xrom.ssh.util.Result;

public interface WithdrawService {

	Result findPage(Withdraw entity, int pageIndex, int pageSize, String orderBy);

	Result get(Integer id);

	Result save(Integer withdrawMoney, String bankName, String bankAddress,
			String bankCard, String bankUserName);

	Result findPageUser(Withdraw entity, int pageIndex, int pageSize,
			String orderBy);

}
