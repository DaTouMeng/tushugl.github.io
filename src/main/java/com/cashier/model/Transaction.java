package com.cashier.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3903292965209731020L;

	private Integer id;

	private Integer userId;

	private String username;

	private String mobile;
	
	private String goodsName;
	
	
	private String content;

	private String dealTime;

	private Boolean deleted;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", userId=" + userId + ", username=" + username + ", mobile=" + mobile
				+ ", content=" + content + ", dealTime=" + dealTime + ", deleted=" + deleted + "]";
	}

	public String getDealTime() {
		return dealTime;
	}

	public Transaction() {
		super();
	}



	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
