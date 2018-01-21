package com.cashier.model;

import java.io.Serializable;

public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1845468501446591082L;
	
	private int id; 
	private Integer goodsID; // 编号
	private String goodsName; 
	private String goodsCount; 
	private Double price; 
	
	public Goods() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Goods(Integer goodsID,String goodsName) {
		super();
		this.goodsID = goodsID;
		this.goodsName = goodsName;

	}

	
	public Goods(int id, Integer goodsID, String goodsName, String goodsCount, Double price) {
		super();
		this.id = id;
		this.goodsID = goodsID;
		this.goodsName = goodsName;
		this.goodsCount = goodsCount;
		this.price = price;
	}
	
	public Goods(Integer goodsID, String goodsName, String goodsCount, Double price) {
		super();
		this.goodsID = goodsID;
		this.goodsName = goodsName;
		this.goodsCount = goodsCount;
		this.price = price;
	}
	public Goods( String goodsName) {
		super();
		this.goodsName = goodsName;
	}
	public Goods( Integer goodsID) {
		super();
		this.goodsID = goodsID;
	}
	
	@Override
	public String toString() {
		return "Goods [goodsID=" + goodsID + ", goodsName=" + goodsName + ", goodsCount=" + goodsCount + ", price="
				+ price + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	
   
}
