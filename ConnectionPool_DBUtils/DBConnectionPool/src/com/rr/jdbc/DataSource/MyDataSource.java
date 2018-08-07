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
	// 1.创建一个容器用于存储Connection对象
	private static LinkedList<Connection> pool = new LinkedList<>();
	// 2.创建五个连接到容器中
	static {
		for (int i = 0; i < 5; i++) {
			Connection conn = JDBCUtils_V3.getConnection();
			// 放入池子中的connection对象已经经过改造了
			MyConnection myconn = new MyConnection(conn, pool);
			pool.add(myconn);
		}
	}

	/**
	 * 重写获取连接的方法
	 */
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		// 3.判断池子是否为空
		if (pool.size() == 0) {
			// 4.为0创建五个
			for (int i = 0; i < 5; i++) {
				// 放入池子中的connection对象已经经过改造了
				MyConnection myconn = new MyConnection(conn, pool);
				pool.add(myconn);
			}
		}
		// 5.从池子里获取一个连接对象
		
		return  pool.removeFirst();
	}
	
	/**
	 *  归还连接对象到连接池
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
