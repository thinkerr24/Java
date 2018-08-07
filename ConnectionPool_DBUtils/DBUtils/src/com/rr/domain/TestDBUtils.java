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
	// 查询所有
	@Test
	public void testQueryAllUser() {
		try {
		// 1.获取核心类QueryRunner
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		// 2.编写sql语句
		String sql = "select * from user";
		// 3.执行查询操作
		
			List<User> users = qr.query(sql, new BeanListHandler<User>(User.class));
			
		// 4.对结果集进行遍历
			for (User user:users) {
				System.out.println(user.getUsername() + ":" + user.getPassword());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 根据username查询
	@Test
	public void testQueryUserByusername() {
		try {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where username = ?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), "赵云");
		System.out.println(user.getUsername() + ":" + user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 查询总个数
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
	
	// 以Map形式查询所有
	@Test
	public void testQueryAllUser2() {
		try {
		// 1.获取核心类QueryRunner
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		// 2.编写sql语句
		String sql = "select * from user";
		// 3.执行查询操作
		
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
			
		// 4.对结果集进行遍历
		for (Map<String, Object> map : list)
			System.out.println(map);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 添加
	@Test
	public void testAddUser() {
	  
	  try {
		// 1.创建核心类QueryRunner
		 QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	    // 2.编写SQL语句
		 String sql = "insert into user values(?, ?)";
		// 3.为占位符设置值
		 Object[] params = {"刘备", "55555"};
		int rows  =  qr.update(sql, params);
		if (rows > 0) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}
	  	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	// 更新
	@Test 
	public void testUpdateUserByusername() {
		 try {
				// 1.创建核心类QueryRunner
				 QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			    // 2.编写SQL语句
				 String sql = "update user set password = ? where username = ?";
				// 3.为占位符设置值
				 Object[] params = {"55556", "刘备"};
				int rows  =  qr.update(sql, params);
				if (rows > 0) {
					System.out.println("更新成功");
				} else {
					System.out.println("更新失败");
				}
			  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	// 删除
	@Test 
	public void testDeleteUserByusername() {
		 try {
				// 1.创建核心类QueryRunner
				 QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			    // 2.编写SQL语句
				 String sql = "delete from user where username = ?";
				// 3.为占位符设置值
				
				int rows  =  qr.update(sql, "刘备");
				if (rows > 0) {
					System.out.println("删除成功");
				} else {
					System.out.println("删除失败");
				}
			  	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}
