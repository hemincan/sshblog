package com.xrom.ssh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrom.ssh.dto.user.AgentTreeDTO;
import com.xrom.ssh.dto.user.UserTreeDTO;
import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.SysUser;
import com.xrom.ssh.repository.AgentTreeRepository;
import com.xrom.ssh.repository.AgentTypeRepository;
import com.xrom.ssh.repository.SysUserRepository;
import com.xrom.ssh.service.AgentTreeService;
import com.xrom.ssh.util.Result;

@Service
public class AgentTreeServiceImpl implements AgentTreeService {

	@Autowired(required = true)
	private SysUserRepository userRepository;
	@Autowired(required = true)
	private AgentTreeRepository agentTreeRepository;
	@Autowired
	private AgentTypeRepository agentTypeRepository;
	@Override
	public Result recommendedStructure() {
		Subject currentUser = SecurityUtils.getSubject();
		String account = (String) currentUser.getSession().getAttribute(
				"account");
		SysUser user = userRepository.getByAccount(account);
		List<SysUser> userList = userRepository.getUserByRecommendUser(user
				.getId());
		if (userList == null) {
			userList = new ArrayList<>();
		}
		List<UserTreeDTO> result = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			UserTreeDTO dto = new UserTreeDTO();
			BeanUtils.copyProperties(userList.get(i), dto);
			result.add(dto);
		}
		UserTreeDTO dto = new UserTreeDTO();
		BeanUtils.copyProperties(user, dto);
		result.add(dto);
		return new Result<>("0", "获取成功！", result);

	}

	@Override
	public Result treeStructure(String account) {
		SysUser user = userRepository.getByAccount(account);
		if (user == null) {
			return new Result<>("1", "用户不存在", null);
		}
		AgentTree agentTree = agentTreeRepository.getByUserId(user.getId());
		AgentTreeDTO dto = getAgentTreeDto(user,1);

		getChildren(dto, agentTree, 2);

		return new Result<>("0", "获取成功", dto);
	}

	private void getChildren(AgentTreeDTO dto, AgentTree agentTree,
			Integer level) {
		if (agentTree == null) {
			return;
		}
		SysUser lefSysUser = userRepository
				.get(agentTree.getLeftUserId() == null ? 0 : agentTree
						.getLeftUserId());
		SysUser rightSysUser = userRepository
				.get(agentTree.getRightUserId() == null ? 0 : agentTree
						.getRightUserId());
		AgentTreeDTO leftDto = getAgentTreeDto(lefSysUser,level);
		AgentTreeDTO rightDto = getAgentTreeDto(rightSysUser,level);
		if (dto.getChildren() == null) {
			dto.setChildren(new ArrayList<AgentTreeDTO>());
		}
		if (leftDto != null) {
			dto.getChildren().add(leftDto);
		} else {
			dto.getChildren().add(new AgentTreeDTO());
		}
		if (rightDto != null) {
			dto.getChildren().add(rightDto);
		} else {
			dto.getChildren().add(new AgentTreeDTO());
		}

		if (level == 3)
			return;
		if (lefSysUser != null) {
			AgentTree agentTree2 = agentTreeRepository.getByUserId(lefSysUser
					.getId());
			getChildren(leftDto, agentTree2, 3);
		}

		if (rightSysUser != null) {
			AgentTree agentTree2 = agentTreeRepository.getByUserId(rightSysUser.getId());
			getChildren(rightDto, agentTree2, 3);
		}

	}

	private AgentTreeDTO getAgentTreeDto(SysUser user,Integer level) {
		if (user == null) {
			return null;
		}
		AgentTreeDTO dto = new AgentTreeDTO();
		dto.setAccountNumber(user.getAccountNumber());
		dto.setRegisterTime(user.getRegisterTime());
		dto.setUserName(user.getUserName());
		dto.setLevel(level);
		dto.setAgentTypeName(agentTypeRepository.get(user.getAgentTypeId()).getName());
		if (user.getIsActivate() != null) {
			dto.setState("已激活");
		} else {
			dto.setState("未激活");
		}
		AgentTree agentTree = agentTreeRepository.getByUserId(user.getId());
		dto.setLeftMoney(0);
		dto.setRightMoney(0);
		if(agentTree!=null){
			dto.setLeftMoney(agentTree.getLeftPerformance());
			dto.setRightMoney(agentTree.getRightPerformance());
		}
		
		
		return dto;
	}
	/**
	 * 保存至用户树的结构，之后就可以进行对碰奖金的统计
	 */
	@Override
	public void saveInAgentTree(String position,SysUser parentUser,SysUser user){
		//放在parentUser的Position这边

//		if (treeParentAccount != null) {
//			// 保存至用户数
//			SysUser treeParentUser = userRepository
//					.getByAccount(treeParentAccount);
//			AgentTree agentTree = new AgentTree();
//			agentTree.setLeftPerformance(0);
//			agentTree.setRightPerformance(0);
//			agentTree.setParentUserId(treeParentUser.getId());
//			agentTree.setUserId(user.getId());
//			agentTreeRepository.save(agentTree);
//			// 保存至的treeParentAccount的孩子
//			AgentTree parentTree = agentTreeRepository
//					.getByUserId(treeParentUser.getId());
//			if (parentTree == null) {
//				parentTree = new AgentTree();
//				parentTree.setUserId(treeParentUser.getId());
//			}
//			if ("left".equals(position)) {
//				parentTree.setLeftUserId(user.getId());
//			} else {
//				parentTree.setRightUserId(user.getId());
//			}
//			agentTreeRepository.saveOrUpdate(parentTree);
//
//		} else {
			// 如果父亲节点是空，就只能在指定的方向找一个空的结点来添加代理
			AgentTree agentTree = agentTreeRepository.getByUserId(parentUser
					.getId());// 获取推荐人的树的数据
			if (agentTree == null) {
				// 新建一个树
				agentTree = new AgentTree();
				agentTree.setLeftPerformance(0);
				agentTree.setRightPerformance(0);
				agentTree.setUserId(parentUser.getId());
				agentTree.setParentUserId(0);
				agentTreeRepository.save(agentTree);// 保存之后agentTree也会被保存有ID等属性值
			}
			if ("left".equals(position)) {
				if (agentTree.getLeftUserId() == null) {
					agentTree.setLeftUserId(user.getId());
					agentTreeRepository.saveOrUpdate(agentTree);
				} else {
					// 随便找个左边的树的空位置放进去
					saveInEmptyNode(agentTree.getLeftUserId(), user.getId());
				}
			} else {
				if (agentTree.getRightUserId() == null) {
					agentTree.setRightUserId(user.getId());
					agentTreeRepository.saveOrUpdate(agentTree);
				} else {
					// 随便找个右边的树的空位置放进去
					saveInEmptyNode(agentTree.getRightUserId(), user.getId());
				}
			}
//		}
	}
	
	private void saveInEmptyNode(Integer userId, Integer putUserId) {
		AgentTree agentTree = agentTreeRepository.getByUserId(userId);
		if(agentTree==null){
			// 新建一个树
			agentTree = new AgentTree();
			agentTree.setLeftPerformance(0);
			agentTree.setRightPerformance(0);
			agentTree.setUserId(userId);
			agentTree.setParentUserId(agentTreeRepository.getMyParentId(userId));
			agentTreeRepository.save(agentTree);// 保存之后agentTree也会被保存有ID等属性值
		}
		Boolean stop = false;
		Integer treeUserId = 0;
		while (stop == false) {
			if (agentTree.getLeftUserId() == null) {
				agentTree.setLeftUserId(putUserId);
				agentTreeRepository.saveOrUpdate(agentTree);
				stop = true;
			} else if (agentTree.getRightUserId() == null) {
				agentTree.setRightUserId(putUserId);
				agentTreeRepository.saveOrUpdate(agentTree);
				stop = true;
			}else {
				// 一直放在左边
			    treeUserId = agentTree.getLeftUserId();
				Integer parentUserId=agentTree.getUserId();
				agentTree = agentTreeRepository.getByUserId(agentTree
						.getLeftUserId());
				if(agentTree==null){
					// 新建一个树
					agentTree = new AgentTree();
					agentTree.setLeftPerformance(0);
					agentTree.setRightPerformance(0);
					agentTree.setUserId(treeUserId);
					agentTree.setLeftUserId(putUserId);
					agentTree.setParentUserId(parentUserId);
					agentTreeRepository.save(agentTree);// 保存之后agentTree也会被保存有ID等属性值
					break;
				}
			}
		}
		
		
	}
}
