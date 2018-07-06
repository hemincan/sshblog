package com.xrom.ssh.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "bonus")
public class Bonus {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "obtain_date")
	private Date obtainDate;

	@Column(name = "bonus_type")
	private String bonusType;

	@Column(name = "mark")
	private String mark;

	@Column(name = "money")
	private Integer money;
	
	@Column(name = "agent_account")
	private String agentAccount;
	
	@Column(name = "agent_name")
	private String agentName;
	
	@Column(name = "state")
	private Integer state;
	
	@Column(name = "collision_integral")
	private Integer collisionIntegral;
	
	@Column(name = "collision_ratio")
	private Double collisionRatio;
	
	@Column(name = "collision_top")
	private Integer collisionTop;
	
	@Column(name = "left_performance")
	private Integer leftPerformance;
	
	@Column(name = "right_performance")
	private Integer rightPerformance;
	
	
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

	public Integer getCollisionIntegral() {
		return collisionIntegral;
	}

	public void setCollisionIntegral(Integer collisionIntegral) {
		this.collisionIntegral = collisionIntegral;
	}

	public Double getCollisionRatio() {
		return collisionRatio;
	}

	public void setCollisionRatio(Double collisionRatio) {
		this.collisionRatio = collisionRatio;
	}

	public Integer getCollisionTop() {
		return collisionTop;
	}

	public void setCollisionTop(Integer collisionTop) {
		this.collisionTop = collisionTop;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getAgentAccount() {
		return agentAccount;
	}

	public void setAgentAccount(String agentAccount) {
		this.agentAccount = agentAccount;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getObtainDate() {
		return obtainDate;
	}

	public void setObtainDate(Date obtainDate) {
		this.obtainDate = obtainDate;
	}

	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}


}
