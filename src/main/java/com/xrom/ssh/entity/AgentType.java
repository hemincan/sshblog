package com.xrom.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "agent_type")
public class AgentType {

	@Id
    @GeneratedValue
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "able_count")
	private Integer ableCount;
	
	@Column(name = "total_money")
	private Integer totalMoney;
	
	@Column(name = "integral")
	private Integer integral;

	@Column(name = "remark")
	private String remark;
	
	@Column(name = "display_order")
	private Integer displayOrder;
	
	
	@Column(name = "first_reward_money")
	private Integer firstRewardMoney;
	
	@Column(name = "collision_per")
	private Double collisionPer;
	
	@Column(name = "top_reward")
	private Integer topReward;
	


	public Double getCollisionPer() {
		return collisionPer;
	}

	public void setCollisionPer(Double collisionPer) {
		this.collisionPer = collisionPer;
	}

	public Integer getTopReward() {
		return topReward;
	}

	public void setTopReward(Integer topReward) {
		this.topReward = topReward;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getFirstRewardMoney() {
		return firstRewardMoney;
	}

	public void setFirstRewardMoney(Integer firstRewardMoney) {
		this.firstRewardMoney = firstRewardMoney;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAbleCount() {
		return this.ableCount;
	}

	public void setAbleCount(Integer ableCount) {
		this.ableCount = ableCount;
	}

	public Integer getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getIntegral() {
		return this.integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}