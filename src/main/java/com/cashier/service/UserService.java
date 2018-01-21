package com.cashier.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cashier.model.Goods;
import com.cashier.model.User;
import com.cashier.utils.MyException;

public interface UserService {

	int register(String mobile);

	boolean isExist(String mobile,String sql);

	User getUser(String mobile);
	
	ResultSet CheckUser(String mobile, String sql);
	
	int pay(String payBalance);
	
	ResultSet checkBalace( String mobile);
	
	int buy(String goodsID,String goodsName,String goodsCount,String goodsPrice,String buyCount)throws SQLException,MyException;
	
	int add_goods(String goodsID,String goodsName,String goodsCount,String price);
	
	int modifi_goods(String goodsID,String goodsName,String goodsCount,String price,String goodsID2);
	
	 int delGoods(String goodsID) ;
	 
	 ResultSet fillTable(Goods goods);


}
