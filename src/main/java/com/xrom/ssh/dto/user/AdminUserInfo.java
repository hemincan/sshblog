package com.xrom.ssh.dto.user;

import java.util.Date;
import java.util.List;

import com.xrom.ssh.entity.SysRole;
import com.xrom.ssh.entity.SysUserRole;

public class AdminUserInfo {
	private Integer id;

	private String accountNumber;

	private String userName;

	private Integer userSex;

	private String identityCard;

	private String phone;

	private String email;

	private String qqNumber;

	private String address;

	private String bankCard;

	private String bankName;

	private String realName;

	private String bankAddress;

	private Integer recommendUserId;

	private Integer agentTypeId;
	private Date lastLoginTime;

	private List<SysRole> roleList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Integer getRecommendUserId() {
		return recommendUserId;
	}

	public void setRecommendUserId(Integer recommendUserId) {
		this.recommendUserId = recommendUserId;
	}

	public Integer getAgentTypeId() {
		return agentTypeId;
	}

	public void setAgentTypeId(Integer agentTypeId) {
		this.agentTypeId = agentTypeId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	
}
