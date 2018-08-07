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
			pstmt.setString(1, "�ŷ�");
			pstmt.setString(2, "111111");
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("��ӳɹ�");
			}
			else
				System.out.println("���ʧ��");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils_V3.release(conn, pstmt, null);
		}
	}
	

}
