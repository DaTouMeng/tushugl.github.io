package com.cashier.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {

	Object mapperRow(ResultSet rs) throws SQLException;    //JDBC查询的结果 

}
