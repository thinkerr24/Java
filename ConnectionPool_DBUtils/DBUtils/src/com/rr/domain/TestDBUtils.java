package com.rr.domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.rr.jdbc.utils.C3P0Utils;

public class TestDBUtils {
	// ��ѯ����
	@Test
	public void testQueryAllUser() {
		try {
		// 1.��ȡ������QueryRunner
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		// 2.��дsql���
		String sql = "select * from user";
		// 3.ִ�в�ѯ����
		
			List<User> users = qr.query(sql, new BeanListHandler<User>(User.class));
			
		// 4.�Խ�������б���
			for (User user:users) {
				System.out.println(user.getUsername() + ":" + user.getPassword());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ����username��ѯ
	@Test
	public void testQueryUserByusername() {
		try {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where username = ?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), "����");
		System.out.println(user.getUsername() + ":" + user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ��ѯ�ܸ���
	@Test
	public void testQueryCount() {
		try {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from user";
		Long count = (Long) qr.query(sql, new ScalarHandler<>());
		System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ��Map��ʽ��ѯ����
	@Test
	public void testQueryAllUser2() {
		try {
		// 1.��ȡ������QueryRunner
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		// 2.��дsql���
		String sql = "select * from user";
		// 3.ִ�в�ѯ����
		
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
			
		// 4.�Խ�������б���
		for (Map<String, Object> map : list)
			System.out.println(map);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ���
	@Test
	public void testAddUser() {
	  
	  try {
		// 1.����������QueryRunner
		 QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	    // 2.��дSQL���
		 String sql = "insert into user values(?, ?)";
		// 3.Ϊռλ������ֵ
		 Object[] params = {"����", "55555"};
		int rows  =  qr.update(sql, params);
		if (rows > 0) {
			System.out.println("��ӳɹ�");
		} else {
			System.out.println("���ʧ��");
		}
	  	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	// ����
	@Test 
	public void testUpdateUserByusername() {
		 try {
				// 1.����������QueryRunner
				 QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			    // 2.��дSQL���
				 String sql = "update user set password = ? where username = ?";
				// 3.Ϊռλ������ֵ
				 Object[] params = {"55556", "����"};
				int rows  =  qr.update(sql, params);
				if (rows > 0) {
					System.out.println("���³ɹ�");
				} else {
					System.out.println("����ʧ��");
				}
			  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	// ɾ��
	@Test 
	public void testDeleteUserByusername() {
		 try {
				// 1.����������QueryRunner
				 QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			    // 2.��дSQL���
				 String sql = "delete from user where username = ?";
				// 3.Ϊռλ������ֵ
				
				int rows  =  qr.update(sql, "����");
				if (rows > 0) {
					System.out.println("ɾ���ɹ�");
				} else {
					System.out.println("ɾ��ʧ��");
				}
			  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}
