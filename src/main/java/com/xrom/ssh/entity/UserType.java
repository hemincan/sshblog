package com.xrom.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_type")
public class UserType {

	@Id
    @GeneratedValue
	private Integer id;
	
	@Column(name = "level_name")
	private String levelName;
	
	@Column(name = "able_count")
	private Integer ableCount;
	
	@Column(name = "total_money")
	private Integer totalMoney;
	
	@Column(name = "integral")
	private Integer integral;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
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

}