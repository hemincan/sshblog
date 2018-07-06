package com.xrom.ssh.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.entity.Message;
import com.xrom.ssh.repository.MessageRepository;
import com.xrom.ssh.service.MessageService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageRepository messageRepository;
	@Override
	public Result findPage(Message entity, int pageIndex, int pageSize,
			String orderBy) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		entity=new Message();
		entity.setUserId(userId);
		Page<Message> page = messageRepository.findPage(entity, pageIndex,
				pageSize, orderBy);
		return new Result<>("0", "获取成功", page);
	}
	@Override
	public Result get(Integer id){
		Message message = messageRepository.get(id);
		message.setReaded(true);
		messageRepository.saveOrUpdate(message);
		return new Result<>("0", "获取成功",message );
		
	}
}
