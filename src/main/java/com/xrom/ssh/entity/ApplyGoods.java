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
@Table(name = "apply_goods")
public class ApplyGoods {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "apply_date")
	private Date applyDate;

	@Column(name = "goods_type")
	private String goodsType;

	@Column(name = "goods_count")
	private Integer goodsCount;

	@Column(name = "total_money")
	private Integer totalMoney;

	@Column(name = "receiver_name")
	private String receiverName;
	
	@Column(name = "receiver_phone")
	private String receiverPhone;
	
	@Column(name = "receiver_address")
	private String receiverAddress;

	@Column(name = "state")
	private Integer state;

	@Column(name = "remark")
	private String remark;
	
	@Column(name = "user_account")
	private String userAccount;
	
	@Column(name = "agent_type_id")
	private Integer agentTypeId;
	
	
	public Integer getAgentTypeId() {
		return agentTypeId;
	}

	public void setAgentTypeId(Integer agentTypeId) {
		this.agentTypeId = agentTypeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setapplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
	
	
	
}
