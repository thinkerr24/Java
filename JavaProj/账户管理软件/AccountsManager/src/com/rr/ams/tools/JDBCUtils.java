package com.rr.ams.tools;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/*
 *  ��ȡ���ݿ����ӵ�������
 *  ʵ�����ӳأ�dbcp���ӳ�
 */
public class JDBCUtils {
	// ����BasicDataSource
	private static BasicDataSource datasource = new BasicDataSource();
	// ��̬����飬ʵ�ֱ�Ҫ��������
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
