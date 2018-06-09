package com.xrom.ssh.service;

import com.xrom.ssh.util.Result;

public interface UserService {

	Result login(String account, String password);

	Result register(String userName, String userPassword, String identityCard,
			String phone);

	Result alertPassword(String account, String oldPassword, String newPassword);

}
