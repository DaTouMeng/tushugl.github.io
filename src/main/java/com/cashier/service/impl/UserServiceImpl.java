package com.cashier.service.impl;

import static com.cashier.dao.JdbcUtils.beginTransaction;
import static com.cashier.dao.JdbcUtils.closeConnection;
import static com.cashier.dao.JdbcUtils.commitTransaction;
import static com.cashier.dao.JdbcUtils.getConnection;
import static com.cashier.dao.JdbcUtils.insert;
import static com.cashier.dao.JdbcUtils.rollBackTransaction;
import static com.cashier.dao.JdbcUtils.select;
import static com.cashier.dao.JdbcUtils.selectCount;

import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.cashier.dao.JdbcUtils;
import com.cashier.dao.RowMapper;
import com.cashier.main.MainGUI;
import com.cashier.model.Goods;
import com.cashier.model.User;
import com.cashier.model.UserWallet;
import com.cashier.service.UserService;
import com.cashier.utils.ExceptionPrompt;
import com.cashier.utils.MyException;
import com.cashier.utils.StringUtils;


public class UserServiceImpl implements UserService {

	private static UserService userService = new UserServiceImpl();

	
	@Override
	public int register(String mobile) {
		final String usql = "insert into user(username, mobile, create_time, deleted) values (?,?,?,?)";
		final String wsql = "insert into user_wallet(user_id, balance, integral, version) values (?,?,?,?)";
		Connection connection = null;
		try {
			String username = "member-" + System.currentTimeMillis();
			connection = getConnection();
			beginTransaction(connection); // 开启事务，保证User和UserWallet记录一起添加成功，方为成功。
			long id = (long) insert(connection, usql, username, mobile, new Date(), 0);
			if (id > 0) {
				if ((long) insert(connection, wsql, (int) id, 0, 0, 0) > 0) {
					commitTransaction(connection); // 插入成功则--提交事务
					return 1;
				}
				rollBackTransaction(connection);
				return 0;
			}
			return 0;
		} catch (SQLException e) {
			rollBackTransaction(connection);
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return 0;
	}

	@Override
	public boolean isExist(String mobile, String sql) {
		Connection connection = null;
		try {
			connection = getConnection(); // 通过索引或者列名来获得查询结果集中的某一列的值。
			return selectCount(connection, sql, mobile) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return false;
	}

	@Override
	public User getUser(String mobile) {
		Connection connection = null;
		try {
			connection = getConnection();
			String sql = "select t.*, w.id as wid,w.balance,w.integral,w.version "
					+ "from (select id,username,mobile,create_time,deleted "
					+ "from user t1 where t1.mobile=?) t left join user_wallet w ON t.id = w.user_id";
			return select(connection, sql, new Object[] { mobile }, new RowMapper() {
				@Override
				public Object mapperRow(ResultSet rs) throws SQLException {

					User user = new User();
					UserWallet wallet = new UserWallet();

					user.setId(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setMobile(rs.getString(3));
					user.setCreateTime(rs.getDate(4));
					user.setDeleted(rs.getBoolean(5));

					wallet.setId(rs.getInt(6));
					wallet.setUserId(user.getId());
					wallet.setBalance(rs.getDouble(7));
					wallet.setIntegral(rs.getInt(8));
					wallet.setVersion(rs.getInt(9));
					user.setWallet(wallet);
					return user;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return null;
	}

	@Override // //返回查询到的rs结果集
	public ResultSet CheckUser(String mobile, String sql) {
		Connection con = null;
		try {
			con = getConnection();
			return JdbcUtils.selectCount2(con, sql, mobile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet checkBalace(String mobile) { // 查询余额
		String sql = "select * from user_wallet " + "where user_id = (select id from user where mobile= ?)";
		return userService.CheckUser(mobile, sql);
	}

	@Override
	public int pay(String payBalance) { // 充值
		Connection con = null;
		String mobile = MainGUI.getMobile();
		String u_sql = "update  user_wallet set balance=? " + "where user_id = (select id from user where mobile= ?)";
		try {
			con = JdbcUtils.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = checkBalace(mobile);
		try {
			if (rs.next()) {
				String blc = rs.getString(4); // 会员余额
//				double payBalance2 = Double.parseDouble(payBalance);
//				double blc2 = Double.parseDouble(blc);
//				double balance = blc2 + payBalance2; // 充值后的余额
//				
				BigDecimal blc2 = new BigDecimal(blc);
				BigDecimal payBalance2 = new BigDecimal(payBalance);
				BigDecimal balance3 = blc2.add(payBalance2);
				double balance =  balance3.doubleValue();
				if(JdbcUtils.update(con, u_sql, (double) balance, mobile) > 0) {
					return 1;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override                   // 购买-减库-减额-添记录
	public int buy(String goodsID, String goodsName, String goodsCount, String goodsPrice, String buyCount)throws SQLException, MyException {

		String y_sql = "update  user_wallet set balance=? where user_id = (select id from user where mobile= ?)";
		String i_sql = "insert into transaction values(null,?,?,?,?,?,?,?)";
		String k_sql = "update add_goods set goodsID=?,goodsName=?,goodsCount=?,price=? where goodsID=?";
		
		try {
			    Connection con = null;
				beginTransaction(con);
				con = JdbcUtils.getConnection();
		
			String blc = null;
			String userId = null;

			double goodsCount2 = Double.parseDouble(goodsCount);
			double buyCount2 = Double.parseDouble(buyCount);
			double goodsPrice2 = Double.parseDouble(goodsPrice);

			double goodsCount3 = goodsCount2 - buyCount2;

			String mobile = MainGUI.getMobile(); // 获取手机号 -------------

			User user = userService.getUser(mobile);
			String username = user.getUsername(); // 获取用户名

			Boolean deleted = user.getDeleted(); // 获取deleted 类型

			SimpleDateFormat mFormatTimeOnly = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss", Locale.getDefault());
			Date dDate = new Date(); // ----消费时间
			String dealTime = mFormatTimeOnly.format(dDate);

			

			String disc = new DecimalFormat("0.00").format((goodsPrice2 * buyCount2 * 0.8)); // 8折后

			goodsCount = String.valueOf(goodsCount3); // 购买后的库存

			ResultSet rs = checkBalace(mobile);
			if (rs.next()) {
				blc = rs.getString(4); // 会员余额
				userId = rs.getString(2); // ----------user_id
			}
		
			if (Float.parseFloat(blc) > Float.parseFloat(disc) && goodsCount2 > buyCount2) {

				double balance = ((Double.parseDouble(blc) - Double.parseDouble(disc))); // 消费余额

				int payNum = JdbcUtils.update(con, y_sql, balance, mobile); // 修改余额
				if (payNum > 0) {
					try {
						int buyMun = JdbcUtils.update(con, i_sql, userId, username, mobile, goodsName, buyCount,
								dealTime, deleted);
						if (buyMun  > 0) {
							try {                      // 添加消费记录
								int addNum = JdbcUtils.update(con, k_sql, Integer.parseInt(goodsID), goodsName,
										goodsCount, Double.parseDouble(goodsPrice), Integer.parseInt(goodsID));
								if (addNum > 0) {
									commitTransaction(con);
									return 1;
								} else {
									rollBackTransaction(con);
									ExceptionPrompt.compare(3);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							rollBackTransaction(con);
							ExceptionPrompt.compare(4);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			} else {
				if (Float.parseFloat(blc) < Float.parseFloat(disc)) {
					rollBackTransaction(con);
					ExceptionPrompt.compare(1);   //通过异常抛出-捕捉-提示				
				}
				if (goodsCount2 < buyCount2) {
					rollBackTransaction(con);
					ExceptionPrompt.compare(2);
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override      //增加商品
	public int add_goods(String goodsID,String goodsName,String goodsCount,String price) {
		Connection con=null;
		String sql="insert into add_goods values(null,?,?,?,?)";
		try {
			con = JdbcUtils.getConnection();
			if(JdbcUtils.update(con, sql, goodsID,goodsName,goodsCount,price) > 0) {
				commitTransaction(con);
				return 1;
			}
			 rollBackTransaction(con);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return 0;
	}
	@Override      //修改商品
	public int modifi_goods(String goodsID,String goodsName,String goodsCount,String price,String goodsID2) {
		Connection con=null;
		String sql="update add_goods set goodsID=?,goodsName=?,goodsCount=?,price=? where goodsID=?";
		try {
			con = JdbcUtils.getConnection();
			if(JdbcUtils.update(con, sql, goodsID,goodsName,goodsCount,price,goodsID2) > 0) {
				commitTransaction(con);
				return 1;
			}
			 rollBackTransaction(con);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return 0;
	}
	
	@Override          // 删除商品
	public int delGoods(String goodsID) {    
		Connection con = null;
		final String d_sql = "delete from add_goods where goodsID=?";
	
		try {
			con = JdbcUtils.getConnection();
			if( JdbcUtils.delete(con, d_sql, goodsID) > 0) {
				commitTransaction(con);
				return 1;
			}
			 rollBackTransaction(con);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return 0;
	}

	
	@Override              //模糊搜索商品
	public ResultSet fillTable(Goods goods) {
		Connection con = null;
		try {
			con = getConnection();
			StringBuffer sb=new StringBuffer("select * from add_goods");
			 if(StringUtils.isNotEmpty(goods.getGoodsName())){
				sb.append(" where goodsName like '%"+goods.getGoodsName()+"%'");
			}
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
