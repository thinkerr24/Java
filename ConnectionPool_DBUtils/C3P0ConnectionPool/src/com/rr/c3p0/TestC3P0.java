package com.rr.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.rr.jdbc.utils.C3P0Utils;
import com.rr.jdbc.utils.JDBCUtils_V3;



public class TestC3P0 {
	@Test
	public void testAddUser1() {
	
		Connection conn = null;
		PreparedStatement  pstmt = null;
		
		// 加载默认配置
		ComboPooledDataSource datasource = new ComboPooledDataSource();
		// 加载有名称的配置
		//ComboPooledDataSource  ds = new  ComboPooledDataSource("oracle");
		try {
			conn = datasource.getConnection();
			String sql = "insert into user values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "关羽");
			pstmt.setString(2, "22222");
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("添加成功");
			}
			else
				System.out.println("添加失败");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils_V3.release(conn, pstmt, null);
		}
	}
	
	@Test
	public void testAddUser2() {
	
		Connection conn = null;
		PreparedStatement  pstmt = null;
		
		
		try {
			conn = C3P0Utils.getConnection();
			String sql = "insert into user values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "赵云");
			pstmt.setString(2, "333333");
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("添加成功");
			}
			else
				System.out.println("添加失败");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils_V3.release(conn, pstmt, null);
		}
		
	}
}
