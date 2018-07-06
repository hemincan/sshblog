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
@Table(name = "transfer")
public class Transfer {
	@Id
    @GeneratedValue
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "transfer_date")
	private Date transferDate;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "from_to_user_id")
	private Integer fromToUserId;
	
	@Column(name = "mark")
	private String mark;

	@Column(name = "user_account")
	private String userAccount;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "from_to_user_account")
	private String fromToUserAccount;
	
	@Column(name = "from_to_user_name")
	private String fromToUserName;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

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

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	
	public Integer getFromToUserId() {
		return fromToUserId;
	}

	public void setFromToUserId(Integer fromToUserId) {
		this.fromToUserId = fromToUserId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getFromToUserAccount() {
		return fromToUserAccount;
	}

	public void setFromToUserAccount(String fromToUserAccount) {
		this.fromToUserAccount = fromToUserAccount;
	}

	public String getFromToUserName() {
		return fromToUserName;
	}

	public void setFromToUserName(String fromToUserName) {
		this.fromToUserName = fromToUserName;
	}
	
	
}
