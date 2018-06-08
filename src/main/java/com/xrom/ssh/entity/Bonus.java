package com.xrom.ssh.entity;

import java.util.Date;

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
	private Integer user_id;

	@Column(name = "obtain_date")
	private Date obtain_date;

	@Column(name = "bonus_type")
	private String bonus_type;

	@Column(name = "mark")
	private String mark;

	@Column(name = "money")
	private Integer money;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getObtain_date() {
		return obtain_date;
	}

	public void setObtain_date(Date obtain_date) {
		this.obtain_date = obtain_date;
	}

	public String getBonus_type() {
		return bonus_type;
	}

	public void setBonus_type(String bonus_type) {
		this.bonus_type = bonus_type;
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
