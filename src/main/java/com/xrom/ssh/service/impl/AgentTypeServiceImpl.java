package com.xrom.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.entity.AgentType;
import com.xrom.ssh.repository.AgentTypeRepository;
import com.xrom.ssh.service.AgentTypeService;
import com.xrom.ssh.util.Page;
import com.xrom.ssh.util.Result;

@Service
public class AgentTypeServiceImpl implements AgentTypeService {
	@Autowired(required = true)
	private AgentTypeRepository agentTypeRepository;
	
	@Override
	public Result findPage(AgentType entity,int pageIndex,int pageSize,String orderBy){
		
		Page<AgentType> page = agentTypeRepository.findPage(entity, pageIndex, pageSize, orderBy);
		return new Result<>("0","获取成功",page);
	}
	
	@Override
	public Result saveOrUpdateRole(Integer id,Integer ableCount,Integer integral,String name,Integer totalMoney){
		AgentType entity = new AgentType();
		entity.setId(id);
		entity.setAbleCount(ableCount);;
		entity.setIntegral(integral);
		entity.setName(name);
		entity.setTotalMoney(totalMoney);
		agentTypeRepository.saveOrUpdate(entity);
		return new Result<AgentType>("0","操作成功！",entity);
	}
	@Override
	public Result delete(Integer id) {
		agentTypeRepository.delete(id);
		return new Result<>("0","删除成功！",null);
	}
	@Override
	public Result get(Integer id){
		return new Result<>("0","获取成功",agentTypeRepository.get(id));
	}
	
}
