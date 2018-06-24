package com.xrom.ssh.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xrom.ssh.entity.PretreatmentAgent;
import com.xrom.ssh.repository.PretreatmentAgentRepository;
@Repository
public class PretreatmentAgentRepositoryImpl extends CommonRepositoryImpl<PretreatmentAgent> implements PretreatmentAgentRepository{

	@Override
	public PretreatmentAgent getByUserId(Integer userId){
		PretreatmentAgent entity = new PretreatmentAgent();
		entity.setUserId(userId);
		List<PretreatmentAgent> list = queryByEntity(entity);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
		
	}
}