package com.rr.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * �ṩ��ȡ���Ӻ��ͷ���Դ�� ����
 * 
 * @author Never Say Never
 * @date 2016��7��29��
 * @version V1.0
 */
public class JDBCUtils_V3 {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	/**
	 * ��̬�������������ļ���Ϣ
	 */
	static {
		try {
			// 1.ͨ����ǰ���ȡ�������
			ClassLoader classLoader = JDBCUtils_V3.class.getClassLoader();
			// 2.ͨ����������ķ������һ��������
			InputStream is = classLoader.getResourceAsStream("db.properties");
			// 3.����һ��properties����
			Properties props = new Properties();
			// 4.����������
			props.load(is);
			// 5.��ȡ��ز�����ֵ
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ȡ���ӷ���
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
