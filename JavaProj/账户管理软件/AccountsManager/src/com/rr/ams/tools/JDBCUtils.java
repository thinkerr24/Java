package com.rr.ams.tools;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/*
 *  获取数据库连接的数据类
 *  实现连接池，dbcp连接池
 */
public class JDBCUtils {
	// 创建BasicDataSource
	private static BasicDataSource datasource = new BasicDataSource();
	// 静态代码块，实现必要参数设置
	static {
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/ams");
		datasource.setUsername("root");
		datasource.setPassword("root");
		datasource.setMaxActive(10);
		datasource.setMaxIdle(5);
		datasource.setMinIdle(2);
		datasource.setInitialSize(10);
	}
	
	public static DataSource getDataSource() {
		return datasource;
	}
}
