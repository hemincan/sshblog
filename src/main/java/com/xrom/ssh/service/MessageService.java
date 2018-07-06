package com.xrom.ssh.service;

import com.xrom.ssh.entity.Message;
import com.xrom.ssh.util.Result;

public interface MessageService {

	Result findPage(Message entity, int pageIndex, int pageSize, String orderBy);

	Result get(Integer id);

}
