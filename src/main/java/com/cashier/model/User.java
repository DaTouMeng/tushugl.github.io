package com.cashier.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2698008286471953017L;

	private Integer id;

	private String username;

	private String mobile;

	private Date createTime;

	private Boolean deleted;

	private UserWallet wallet; // 一对一关联

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public UserWallet getWallet() {
		return wallet;
	}

	public void setWallet(UserWallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", mobile=" + mobile + ", createTime=" + createTime
				+ ", deleted=" + deleted + "]";
	}

}
