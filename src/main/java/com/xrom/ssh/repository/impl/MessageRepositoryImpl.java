package com.xrom.ssh.repository.impl;

import org.springframework.stereotype.Repository;

import com.xrom.ssh.entity.Message;
import com.xrom.ssh.repository.MessageRepository;
@Repository
public class MessageRepositoryImpl extends CommonRepositoryImpl<Message> implements MessageRepository{

}
