package com.xrom.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pretreatment_agent")
public class PretreatmentAgent {
	@Id
    @GeneratedValue
    private Integer id;
	
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "tree_parent_user_id")
	private Integer treeParentUserId;

	@Column(name = "position")
	private String position;
	
	@Column(name = "state")
	private Integer state;

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

	public Integer getTreeParentUserId() {
		return treeParentUserId;
	}

	public void setTreeParentUserId(Integer treeParentUserId) {
		this.treeParentUserId = treeParentUserId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
}
