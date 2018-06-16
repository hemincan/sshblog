package com.xrom.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "agent_tree")
public class AgentTree {
	@Id
    @GeneratedValue
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "left_user_id")
	private Integer leftUserId;
	
	@Column(name = "right_user_id")
	private Integer rightUserId;
	
	@Column(name = "parent_user_id")
	private Integer parentUserId;

	@Column(name = "left_performance")
	private Integer leftPerformance;
	
	@Column(name = "right_performance")
	private Integer rightPerformance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLeftUserId() {
		return leftUserId;
	}

	public void setLeftUserId(Integer leftUserId) {
		this.leftUserId = leftUserId;
	}

	public Integer getRightUserId() {
		return rightUserId;
	}

	public void setRightUserId(Integer rightUserId) {
		this.rightUserId = rightUserId;
	}

	

	public Integer getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Integer parentUserId) {
		this.parentUserId = parentUserId;
	}

	public Integer getLeftPerformance() {
		return leftPerformance;
	}

	public void setLeftPerformance(Integer leftPerformance) {
		this.leftPerformance = leftPerformance;
	}

	public Integer getRightPerformance() {
		return rightPerformance;
	}

	public void setRightPerformance(Integer rightPerformance) {
		this.rightPerformance = rightPerformance;
	}
	
	
}
