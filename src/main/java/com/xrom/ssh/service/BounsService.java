package com.xrom.ssh.service;

import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.Integral;
import com.xrom.ssh.util.Result;

public interface BounsService{

	Result get(Integer id);

	Result findPage(Bonus entity, int pageIndex, int pageSize, String orderBy);

	Result findIntegralPage(Integral entity, int pageIndex, int pageSize,
			String orderBy);

	Result getIntegral(Integer id);

	Result findPageUser(Bonus entity, int pageIndex, int pageSize,
			String orderBy);

	Result findIntegralPageUser(Integral entity, int pageIndex, int pageSize,
			String orderBy);


	

}
