package com.rr.jdbc.DataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.rr.jdbc.utils.JDBCUtils_V3;

public class MyDataSource implements DataSource{
	// 1.����һ���������ڴ洢Connection����
	private static LinkedList<Connection> pool = new LinkedList<>();
	// 2.����������ӵ�������
	static {
		for (int i = 0; i < 5; i++) {
			Connection conn = JDBCUtils_V3.getConnection();
			// ��������е�connection�����Ѿ�����������
			MyConnection myconn = new MyConnection(conn, pool);
			pool.add(myconn);
		}
	}

	/**
	 * ��д��ȡ���ӵķ���
	 */
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		// 3.�жϳ����Ƿ�Ϊ��
		if (pool.size() == 0) {
			// 4.Ϊ0�������
			for (int i = 0; i < 5; i++) {
				// ��������е�connection�����Ѿ�����������
				MyConnection myconn = new MyConnection(conn, pool);
				pool.add(myconn);
			}
		}
		// 5.�ӳ������ȡһ�����Ӷ���
		
		return  pool.removeFirst();
	}
	
	/**
	 *  �黹���Ӷ������ӳ�
	 */
	public void backConnection(Connection conn) {
		pool.add(conn);
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
