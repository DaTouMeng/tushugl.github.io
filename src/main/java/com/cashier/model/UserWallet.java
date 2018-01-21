package com.cashier.model;

import java.io.Serializable;

public class UserWallet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6035281818848590663L;

	private Integer id;

	private Integer userId;

	private Double balance; // 单位：分

	private Integer integral;

	private Integer version;

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

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
