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
@Table(name = "withdraw")
public class Withdraw  {

	@Id
    @GeneratedValue
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "withdraw_amount")
	private Integer withdrawAmount;
	
	@Column(name = "poundage")
	private Integer poundage;
	
	@Column(name = "real_amount")
	private Integer realAmount;
	
	@Column(name = "bank_card")
	private String bankCard;
	
	@Column(name = "bank_user_name")
	private String bankUserName;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_address")
	private String bankAddress;

	@Column(name = "state")
	private Integer state;
	
	@Column(name = "application_time")
	private Date applicationTime;
	
	@Column(name = "declaration_center")
	private String declarationCenter;
	
	@Column(name = "user_account")
	private String userAccount;
	
	@Column(name = "handle_account")
	private String handleAccount;
	
	@Column(name = "handle_date")
	private Date handleDate;
	
	public String getHandleAccount() {
		return handleAccount;
	}

	public void setHandleAccount(String handleAccount) {
		this.handleAccount = handleAccount;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getWithdrawAmount() {
		return this.withdrawAmount;
	}

	public void setWithdrawAmount(Integer withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Integer getPoundage() {
		return this.poundage;
	}

	public void setPoundage(Integer poundage) {
		this.poundage = poundage;
	}

	public Integer getRealAmount() {
		return this.realAmount;
	}

	public void setRealAmount(Integer realAmount) {
		this.realAmount = realAmount;
	}

	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankUserName() {
		return this.bankUserName;
	}

	public void setBankUserNane(String bankUserNane) {
		this.bankUserName = bankUserNane;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return this.bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getApplicationTime() {
		return this.applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getDeclarationCenter() {
		return this.declarationCenter;
	}

	public void setDeclarationCenter(String declarationCenter) {
		this.declarationCenter = declarationCenter;
	}

}