package com.cashier.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.cashier.utils.PropertiesUtils;
import com.zaxxer.hikari.HikariDataSource;

public final class JdbcUtils {

	private static final HikariDataSource DATA_SOURCE = new HikariDataSource();

	private static final Map<Class<?>, Integer> SQL_TYPE = new HashMap<Class<?>, Integer>();

	static {
		DATA_SOURCE.setDriverClassName(PropertiesUtils.getProperty("jdbc.driver"));
		DATA_SOURCE.setJdbcUrl(PropertiesUtils.getProperty("jdbc.url"));
		DATA_SOURCE.setUsername(PropertiesUtils.getProperty("jdbc.username"));
		DATA_SOURCE.setPassword(PropertiesUtils.getProperty("jdbc.password"));
		DATA_SOURCE.setConnectionTimeout(PropertiesUtils.getPropertyAsLong("jdbc.connectionTimeout"));
		DATA_SOURCE.setIdleTimeout(PropertiesUtils.getPropertyAsLong("jdbc.idleTimeout"));
		DATA_SOURCE.setMinimumIdle(PropertiesUtils.getPropertyAsInt("jdbc.minimumIdle"));
		DATA_SOURCE.setMaximumPoolSize(PropertiesUtils.getPropertyAsInt("jdbc.maximumPoolSize"));
		DATA_SOURCE.setMaxLifetime(PropertiesUtils.getPropertyAsLong("jdbc.maxLifetime"));
		DATA_SOURCE.setConnectionInitSql(PropertiesUtils.getProperty("jdbc.testQuerySql"));

		SQL_TYPE.put(int.class, Types.INTEGER);
		SQL_TYPE.put(long.class, Types.BIGINT);
		SQL_TYPE.put(boolean.class, Types.BOOLEAN);
		SQL_TYPE.put(Short.class, Types.SMALLINT);
		SQL_TYPE.put(Integer.class, Types.INTEGER);
		SQL_TYPE.put(Long.class, Types.BIGINT);
		SQL_TYPE.put(Double.class, Types.DOUBLE);
		SQL_TYPE.put(Float.class, Types.FLOAT);
		SQL_TYPE.put(String.class, Types.VARCHAR);
		SQL_TYPE.put(Boolean.class, Types.BOOLEAN);
		SQL_TYPE.put(Date.class, Types.TIMESTAMP);
	}

	public static void destory() {
		DATA_SOURCE.close();
	}

	public static final Connection getConnection() throws SQLException {
		return DATA_SOURCE.getConnection();    //连接数据库
	}

	public static void beginTransaction(Connection connection) throws SQLException {
		if (connection != null) {
			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}
		}
	}

	public static void commitTransaction(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.getAutoCommit()) {
					connection.commit();    //提交事务
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void rollBackTransaction(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.getAutoCommit()) {
					connection.rollback();   //回滚事务
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection assertOpenConnection(Connection connection) throws SQLException {
		if (connection == null || connection.isClosed()) {   //判断是否为空/或关闭状态
			throw new SQLException("Invalid connection.");
		}
		return connection;
	}

	public static Serializable insert(Connection connection, String sql, Object... args) throws SQLException {
		PreparedStatement statement = assertOpenConnection(connection).prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		try {
			for (int i = 1; i <= args.length; i++) {
				Object value = args[i - 1];
				statement.setObject(i, value, SQL_TYPE.get(value.getClass()));
			}
			if (statement.executeUpdate() > 0) {
				ResultSet rs = statement.getGeneratedKeys();   //获取插入主键值
				if (rs.next()) {
					return (Serializable) rs.getObject(1);
				}
				return -1;
			}
			return -1;
		} finally {
			statement.close();
		}

	}

	public static int update(Connection connection, String sql, Object... args) throws SQLException {
		PreparedStatement statement = assertOpenConnection(connection).prepareStatement(sql);
		
		try {
			for (int i = 1; i <= args.length; i++) {
				Object value = args[i - 1];
				statement.setObject(i, value, SQL_TYPE.get(value.getClass()));
				
			}
			return statement.executeUpdate();
		} finally {
			statement.close();
		}
	}

	public static int delete(Connection connection, String sql, Object... args) throws SQLException {
		return update(connection, sql, args);    
	}

	@SuppressWarnings("unchecked")
	public static <E> E select(Connection connection, String sql, Object[] args, RowMapper rowMapper)
			throws SQLException {
		PreparedStatement statement = assertOpenConnection(connection).prepareStatement(sql);
		try {
			for (int i = 1; i <= args.length; i++) {
				Object value = args[i - 1];
				statement.setObject(i, value, SQL_TYPE.get(value.getClass()));
			}
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return (E) rowMapper.mapperRow(rs);
			}
			
			return null;
		} finally {
//			statement.close();
		}
	}
 
	public static int selectCount(Connection connection, String sql, Object... args) throws SQLException {
		PreparedStatement statement = assertOpenConnection(connection).prepareStatement(sql);
		
		try {
			for (int i = 1; i <= args.length; i++) {
				Object value = args[i - 1];
				statement.setObject(i, value, SQL_TYPE.get(value.getClass()));
			}
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);  //通过索引或者列名来获得查询结果集中的某一列的值。  //或判断是否存在
			}
		
		} finally {
			
		}
		return 0;
		
	}
	
	public static ResultSet selectCount2(Connection connection, String sql, Object... args) throws SQLException {
		PreparedStatement statement = assertOpenConnection(connection).prepareStatement(sql);
		try {
			if(args != null) {
				for (int i = 1; i <= args.length; i++) {
					Object value = args[i - 1];
					statement.setObject(i, value, SQL_TYPE.get(value.getClass()));
				}
			}
				return statement.executeQuery();     //返回查询到的rs结果集
			
		} finally {
			
		}
	}
	

	

}
