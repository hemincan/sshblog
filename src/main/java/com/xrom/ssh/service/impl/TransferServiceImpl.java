package com.xrom.ssh.service.impl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.Message;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.entity.Transfer;
import com.xrom.ssh.repository.MessageRepository;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.repository.TransferRepository;
import com.xrom.ssh.service.TransferService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired(required = true)
	private SysUserRepository userRepository;
	@Autowired(required = true)
	private TransferRepository transferRepository;
	@Autowired(required = true)
	private MessageRepository messageRepository;
	
	@Override
	public Result findPage(Transfer entity, int pageIndex, int pageSize,
			String orderBy) {
		Page<Transfer> page = transferRepository.findPage(entity, pageIndex, pageSize, orderBy);
		return new Result<>("0", "获取成功", page);
	}
	@Override
	public Result findPageUser(Transfer entity, int pageIndex, int pageSize,
			String orderBy) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		Transfer entity2= new Transfer();
		entity2.setUserId(userId);
		Page<Transfer> page = transferRepository.findPage(entity2, pageIndex, pageSize, orderBy);
		return new Result<>("0", "获取成功", page);
	}
	@Override
	public Result get(Integer id) {
		return new Result<>("0", "获取成功", transferRepository.get(id));
	}
	@Transactional
	@Override
	public Result save(Integer amount,String toUserAccount,String mark){
		Integer userId = (Integer) SecurityUtils.getSubject().getSession()
				.getAttribute("userId");
		SysUser user = userRepository.get(userId);
		
		SysUser toUser = userRepository.getByAccount(toUserAccount);
		if(toUser==null){
			return new Result<>("1", "转帐失败，对方帐户不存在", null);
		}
		if(user.getId().equals(toUser.getId())){
			return new Result<>("2", "不要给自己转帐好吗？", null);
		}
		if(user.getBalance()<amount){
			return new Result<>("3", "余额不足", null);
		}
		user.setBalance(user.getBalance()-amount);
		userRepository.saveOrUpdate(user);//更新余额
		
		toUser.setBalance(toUser.getBalance()+amount);
		userRepository.saveOrUpdate(toUser);//更新touser的余额
		
		Transfer entity = new Transfer();
		entity.setAmount(-amount);
		entity.setMark(mark);
		entity.setFromToUserId(toUser.getId());
		entity.setTransferDate(new Date());
		entity.setUserAccount(user.getAccountNumber());
		entity.setUserId(user.getId());
		entity.setType(1);//转出
		entity.setFromToUserAccount(toUser.getAccountNumber());
		entity.setFromToUserName(toUser.getUserName());
		transferRepository.save(entity);
		
		Transfer receiveEntity = new Transfer();
		receiveEntity.setAmount(amount);
		receiveEntity.setMark(mark);
		receiveEntity.setFromToUserId(user.getId());
		receiveEntity.setTransferDate(new Date());
		receiveEntity.setUserAccount(toUser.getAccountNumber());
		receiveEntity.setUserId(toUser.getId());
		receiveEntity.setType(2);//收到
		receiveEntity.setFromToUserAccount(user.getAccountNumber());
		receiveEntity.setFromToUserName(user.getUserName());
		transferRepository.save(receiveEntity);
		//////
		Message message = new Message();
		message.setReaded(false);
		message.setReceiveDate(new Date());
		message.setUserId(toUser.getId());
		String content = "你收到来自用户"+user.getUserName()+
				"("+user.getAccountNumber()+")"+"的"+amount+"元转帐。你可以通过转帐记录查询。备注："+mark;
		message.setContent(content);
		messageRepository.save(message);
		
		/////
		return new Result<>("0", "转帐成功", null);
	}

}
