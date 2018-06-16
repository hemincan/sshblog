package com.xrom.ssh.dto.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentTreeDTO {

		public AgentTreeDTO(){
			this.children=new ArrayList<>();
		}
		private String accountNumber;
		
		private String userName;
		
		private String agentTypeName;
		
		private String state;
		
		private Date registerTime;
		
		private Integer rightMoney;
		
		private Integer leftMoney;
		
		private List<AgentTreeDTO> children;
		
		private Integer level;


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

		public String getAgentTypeName() {
			return agentTypeName;
		}

		public void setAgentTypeName(String agentTypeName) {
			this.agentTypeName = agentTypeName;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public Date getRegisterTime() {
			return registerTime;
		}

		public void setRegisterTime(Date registerTime) {
			this.registerTime = registerTime;
		}

		public Integer getRightMoney() {
			return rightMoney;
		}

		public void setRightMoney(Integer rightMoney) {
			this.rightMoney = rightMoney;
		}

		public Integer getLeftMoney() {
			return leftMoney;
		}

		public void setLeftMoney(Integer leftMoney) {
			this.leftMoney = leftMoney;
		}


		public List<AgentTreeDTO> getChildren() {
			return children;
		}

		public void setChildren(List<AgentTreeDTO> children) {
			this.children = children;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}
		
		
}
