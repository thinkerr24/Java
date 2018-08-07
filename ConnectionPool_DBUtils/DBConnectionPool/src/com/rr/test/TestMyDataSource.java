package com.rr.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.rr.jdbc.DataSource.MyDataSource;
import com.rr.jdbc.utils.JDBCUtils_V3;

public class TestMyDataSource {
	@Test
	public void testAddUser() {
	
		Connection conn = null;
		PreparedStatement  pstmt = null;
		
		MyDataSource ds = new MyDataSource();
		try {
			conn = ds.getConnection();
			String sql = "insert into user values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "张飞");
			pstmt.setString(2, "111111");
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
