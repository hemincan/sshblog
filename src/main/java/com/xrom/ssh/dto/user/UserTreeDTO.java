package com.xrom.ssh.dto.user;

public class UserTreeDTO {

	private Integer id;

	private String accountNumber;

	private String userName;
	
	private Integer recommendUserId;

	private Integer agentTypeId;
	
	private String agentTypeName;
	
	public Integer getAgentTypeId() {
		return agentTypeId;
	}

	public void setAgentTypeId(Integer agentTypeId) {
		this.agentTypeId = agentTypeId;
	}

	public String getAgentTypeName() {
		return agentTypeName;
	}

	public void setAgentTypeName(String agentTypeName) {
		this.agentTypeName = agentTypeName;
	}

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

	public Integer getRecommendUserId() {
		return recommendUserId;
	}

	public void setRecommendUserId(Integer recommendUserId) {
		this.recommendUserId = recommendUserId;
	}
	
	
}
