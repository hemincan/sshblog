package com.xrom.ssh.dto.withdraw;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class WithdrawInfo {
	@Id
	@GeneratedValue
	private Integer id;

	private Integer userId;

	private Integer withdrawAmount;

	private Integer poundage;

	private Integer realAmount;

	private String bankCard;

	private String bankUserName;

	private String bankName;

	private String bankAddress;

	private Integer state;

	private Date applicationTime;

	private String declarationCenter;

	private String userName;

	private String userAccount;

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

	public Integer getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(Integer withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Integer getPoundage() {
		return poundage;
	}

	public void setPoundage(Integer poundage) {
		this.poundage = poundage;
	}

	public Integer getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Integer realAmount) {
		this.realAmount = realAmount;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getDeclarationCenter() {
		return declarationCenter;
	}

	public void setDeclarationCenter(String declarationCenter) {
		this.declarationCenter = declarationCenter;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
}
