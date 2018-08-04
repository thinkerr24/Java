package com.rr.ams.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.rr.ams.domain.Account;
import com.rr.ams.tools.JDBCUtils;

/*
 *  实习对数据表ams_rr的增删改查操作
 *  DBUtils工具类完成，类成员创建QueryRunner对象，指定数据源
 */
public class AccountDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	// 添加账务
	public void addAccount(Account ac) {
		String sql = "INSERT INTO ams_rr(flname, money, zhanghu, createtime, description)" +
				"VALUES(?, ?, ?, ?, ?)";
		Object[] params = {ac.getFlname(), ac.getMoney(), ac.getZhanghu(), ac.getCreatetime(), ac.getDescription()};
		try {
		qr.update(sql, params);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("账务添加失败");
		}
	}
	
	// 编辑账务
	public void editAccount(Account ac) {
		// 更新数据的SQL
		String sql = "UPDATE ams_rr SET flname = ?, money = ?, zhanghu = ?, createtime = ?, "
				+ "description = ? WHERE zwid = ?";
		Object[] params = {ac.getFlname(), ac.getMoney(), ac.getZhanghu(), ac.getCreatetime(), 
					ac.getDescription(), ac.getZwid()};
		try {
		qr.update(sql, params);
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println("账务编辑失败");
		}
		
	}
	
	// 删除账务
	public void deleteAccount(int zwid) {
		String sql = "DELETE FROM ams_rr WHERE zwid=?";
		try {
		qr.update(sql, zwid);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("删除账务失败!");
		}
	}
	/*
	 *  查询数据库，获取所有的账务数据
	 *  由业务层调用
	 */
	public List<Account> selectAll() {
		try {
			// 查询所有账务数据的sql语句
			String sql = "SELECT * FROM ams_rr";
			// 调用qr对象的query方法，结果集BeanListHandler
			List<Account> list = qr.query(sql, new BeanListHandler<>(Account.class));
			return list;
		} catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("查询所有账务数据失败");
		}
	}
	
	/*
	 *  查询数据库，获取指定时期内的bean集合
	 */
	public List<Account> select(String startDate, String endDate) {
		// 拼写条件查询的SQL语句
		String sql = "SELECT * FROM ams_rr WHERE Createtime BETWEEN ? AND ?";
		// 定义对象数组，存储?占位符
		Object[] params = {startDate, endDate};
		try {
			// 调用qr对象的query方法，获取结果集
			return qr.query(sql, new BeanListHandler<>(Account.class), params);
		} catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("条件查询失败");
		}
		
	}
}
