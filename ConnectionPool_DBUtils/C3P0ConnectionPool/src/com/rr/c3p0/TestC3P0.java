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
		
		// ����Ĭ������
		ComboPooledDataSource datasource = new ComboPooledDataSource();
		// ���������Ƶ�����
		//ComboPooledDataSource  ds = new  ComboPooledDataSource("oracle");
		try {
			conn = datasource.getConnection();
			String sql = "insert into user values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "����");
			pstmt.setString(2, "22222");
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
	
	@Test
	public void testAddUser2() {
	
		Connection conn = null;
		PreparedStatement  pstmt = null;
		
		
		try {
			conn = C3P0Utils.getConnection();
			String sql = "insert into user values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "����");
			pstmt.setString(2, "333333");
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
